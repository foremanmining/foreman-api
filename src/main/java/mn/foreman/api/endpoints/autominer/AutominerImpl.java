package mn.foreman.api.endpoints.autominer;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;
import mn.foreman.api.model.ApiType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;

/** A {@link Autominer} implementation. */
public class AutominerImpl
        implements Autominer {

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
    public AutominerImpl(
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Map<String, ApiType> mappings() {
        return this.webUtil.get(
                "/api/autominer")
                .flatMap(
                        s -> JsonUtils.fromJson(
                                s,
                                this.objectMapper,
                                new TypeReference<Map<String, ApiType>>() {
                                }))
                .orElse(Collections.emptyMap());
    }
}
