package mn.foreman.api.endpoints.notifications;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A simple {@link Notifications} implementation. */
public class NotificationsImpl
        implements Notifications {

    /** The client ID. */
    private final String clientId;

    /** The mapper. */
    private final ObjectMapper objectMapper;

    /** The web utils. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param clientId     The client ID.
     * @param objectMapper The mapper.
     * @param webUtil      The web utils.
     */
    public NotificationsImpl(
            final String clientId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.clientId = clientId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public List<Notification> discord(
            final int sinceId,
            final Instant sinceTimestamp) {
        return getNotifications(
                sinceId,
                sinceTimestamp,
                "discord");
    }

    @Override
    public List<Notification> googleChat(
            final int sinceId,
            final Instant sinceTimestamp) {
        return getNotifications(
                sinceId,
                sinceTimestamp,
                "google-chat");
    }

    @Override
    public List<Notification> slack(
            final int sinceId,
            final Instant sinceTimestamp) {
        return getNotifications(
                sinceId,
                sinceTimestamp,
                "slack");
    }

    @Override
    public List<Notification> telegram(
            final int sinceId,
            final Instant sinceTimestamp) {
        return getNotifications(
                sinceId,
                sinceTimestamp,
                "telegram");
    }

    /**
     * Gets the notifications.
     *
     * @param sinceId        The cutoff ID.
     * @param sinceTimestamp The cutoff timestamp.
     * @param destination    The notification destination.
     *
     * @return The notifications.
     */
    private List<Notification> getNotifications(
            final int sinceId,
            final Instant sinceTimestamp,
            final String destination) {
        final Map<String, String> params = new HashMap<>();
        if (sinceId >= 0) {
            params.put(
                    "sinceId",
                    Integer.toString(sinceId));
        }
        if (sinceTimestamp != null) {
            params.put(
                    "sinceTimestamp",
                    Long.toString(sinceTimestamp.toEpochMilli()));
        }
        return this.webUtil.get(
                String.format(
                        "/api/notifications/%s/%s",
                        destination,
                        this.clientId),
                params)
                .flatMap(
                        response ->
                                JsonUtils.fromJson(
                                        response,
                                        this.objectMapper,
                                        new TypeReference<List<Notification>>() {
                                        }))
                .orElse(Collections.emptyList());
    }
}