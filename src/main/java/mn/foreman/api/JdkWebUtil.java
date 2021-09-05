package mn.foreman.api;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/** A {@link WebUtil} implementation that uses native JDK libraries. */
public class JdkWebUtil
        implements WebUtil {

    /** The logger for this class. */
    private static final Logger LOG =
            LoggerFactory.getLogger(JdkWebUtil.class);

    /** No request content. */
    private static final String NO_CONTENT = "";

    /** The API token. */
    private final String apiToken;

    /** The configuration. */
    private final String foremanUrl;

    /** The socket timeout. */
    private final int socketTimeoutMillis;

    /**
     * Constructor.
     *
     * @param foremanUrl         The Foreman API url.
     * @param apiToken           The API token.
     * @param socketTimeout      The socket timeout.
     * @param socketTimeoutUnits The socket timeout (units).
     */
    public JdkWebUtil(
            final String foremanUrl,
            final String apiToken,
            final int socketTimeout,
            final TimeUnit socketTimeoutUnits) {
        this.foremanUrl = foremanUrl;
        this.apiToken = apiToken;
        this.socketTimeoutMillis = (int) socketTimeoutUnits.toMillis(socketTimeout);
    }

    @Override
    public Optional<String> get(final String uri) {
        return get(
                uri,
                Collections.emptyMap());
    }

    @Override
    public Optional<String> get(
            final String uri,
            final boolean auth) {
        return readOp(
                uri,
                auth,
                Collections.emptyMap());
    }

    @Override
    public Optional<String> get(
            final String uri,
            final Map<String, String> params) {
        return readOp(
                uri,
                true,
                params);
    }

    @Override
    public Optional<String> post(final String uri) {
        return post(
                uri,
                NO_CONTENT);
    }

    @Override
    public Optional<String> post(
            final String uri,
            final String body) {
        final HttpPost httpPost =
                new HttpPost(
                        String.format(
                                "%s%s",
                                this.foremanUrl,
                                uri));
        return writeableOp(
                uri,
                body,
                httpPost);
    }

    @Override
    public Optional<String> put(
            final String uri,
            final String body) {
        final HttpPut httpPut =
                new HttpPut(
                        String.format(
                                "%s%s",
                                this.foremanUrl,
                                uri));
        return writeableOp(
                uri,
                body,
                httpPut);
    }

    /**
     * Runs the provided entity request.
     *
     * @param uri    The URI.
     * @param auth   Whether or not auth is required.
     * @param params The params.
     *
     * @return The response content.
     */
    private Optional<String> readOp(
            final String uri,
            final boolean auth,
            final Map<String, String> params) {
        String response = null;

        final RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectTimeout(this.socketTimeoutMillis)
                        .setConnectionRequestTimeout(this.socketTimeoutMillis)
                        .setSocketTimeout(this.socketTimeoutMillis)
                        .build();

        try (final CloseableHttpClient httpClient =
                     HttpClients.custom()
                             .setDefaultRequestConfig(requestConfig)
                             .disableAutomaticRetries()
                             .build()) {

            final HttpGet httpGet =
                    new HttpGet(
                            String.format(
                                    "%s%s",
                                    this.foremanUrl,
                                    uri));

            final URIBuilder uriBuilder =
                    new URIBuilder(httpGet.getURI());
            if (!params.isEmpty()) {
                params.forEach(uriBuilder::addParameter);
            }
            httpGet.setURI(uriBuilder.build());

            LOG.debug("Querying {}{}",
                    this.foremanUrl,
                    uri);

            if (auth) {
                httpGet.setHeader(
                        "Authorization",
                        "Token " + this.apiToken);
            }

            try (final CloseableHttpResponse httpResponse =
                         httpClient.execute(httpGet)) {
                final int statusCode =
                        httpResponse
                                .getStatusLine()
                                .getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    response =
                            EntityUtils.toString(
                                    httpResponse.getEntity(),
                                    StandardCharsets.UTF_8);
                    LOG.debug("Obtained response from Foreman: {}", response);
                }
            } catch (final IOException ioe) {
                LOG.warn("Exception occurred while GETing", ioe);
            }
        } catch (final IOException | URISyntaxException ioe) {
            LOG.warn("Exception occurred while GETing", ioe);
        }

        return Optional.ofNullable(response);
    }

    /**
     * Runs the provided entity request.
     *
     * @param uri         The URI.
     * @param body        The body.
     * @param requestBase The request base.
     *
     * @return The response content.
     */
    private Optional<String> writeableOp(
            final String uri,
            final String body,
            final HttpEntityEnclosingRequestBase requestBase) {
        String response = null;

        final RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectTimeout(this.socketTimeoutMillis)
                        .setConnectionRequestTimeout(this.socketTimeoutMillis)
                        .setSocketTimeout(this.socketTimeoutMillis)
                        .build();

        try (final CloseableHttpClient httpClient =
                     HttpClients.custom()
                             .setDefaultRequestConfig(requestConfig)
                             .disableAutomaticRetries()
                             .build()) {

            final StringEntity stringEntity =
                    new StringEntity(body);

            LOG.debug("Querying {}{} with {}",
                    this.foremanUrl,
                    uri,
                    body);

            requestBase.setEntity(stringEntity);
            requestBase.setHeader(
                    "Content-Type",
                    "application/json");
            requestBase.setHeader(
                    "Authorization",
                    "Token " + this.apiToken);

            try (final CloseableHttpResponse httpResponse =
                         httpClient.execute(requestBase)) {
                final int statusCode =
                        httpResponse
                                .getStatusLine()
                                .getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    response =
                            EntityUtils.toString(
                                    httpResponse.getEntity(),
                                    StandardCharsets.UTF_8);
                    LOG.debug("Obtained response from Foreman: {}", response);
                }
            } catch (final IOException ioe) {
                LOG.warn("Exception occurred while posting", ioe);
            }
        } catch (final IOException ioe) {
            LOG.warn("Exception occurred while posting", ioe);
        }

        return Optional.ofNullable(response);
    }
}