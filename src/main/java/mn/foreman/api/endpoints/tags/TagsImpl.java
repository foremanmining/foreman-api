package mn.foreman.api.endpoints.tags;

import mn.foreman.api.WebUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** A simple {@link Tags} implementation. */
public class TagsImpl
        implements Tags {

    /** The logger for this class. */
    private static final Logger LOG =
            LoggerFactory.getLogger(TagsImpl.class);

    /** The mapper. */
    private final ObjectMapper objectMapper;

    /** The web utils. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param objectMapper The mapper.
     * @param webUtil      The web utils.
     */
    public TagsImpl(
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.webUtil = webUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean tag(
            final int minerId,
            final List<String> tags) {
        try {
            return this.webUtil
                    .post(
                            String.format(
                                    "/api/tags/%d",
                                    minerId),
                            this.objectMapper.writeValueAsString(tags))
                    .map(s -> s.toLowerCase().contains("okay"))
                    .orElse(false);
        } catch (final Exception e) {
            LOG.warn("Exception occurred while creating tags", e);
            return false;
        }
    }
}
