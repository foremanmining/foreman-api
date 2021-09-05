package mn.foreman.api.endpoints.claymore;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/** A {@link Claymore} implementation. */
public class ClaymoreImpl
        implements Claymore {

    /** The mapper. */
    private final ObjectMapper objectMapper;

    /** The web utilities. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param objectMapper The mapper.
     * @param webUtil      The web utilities.
     */
    public ClaymoreImpl(
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Map<String, BigDecimal> mappings() {
        return this.webUtil.get(
                "/api/claymore")
                .flatMap(
                        s -> JsonUtils.fromJson(
                                s,
                                this.objectMapper,
                                new TypeReference<Map<String, BigDecimal>>() {
                                }))
                .orElse(Collections.emptyMap());
    }
}
