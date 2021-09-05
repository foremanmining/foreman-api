package mn.foreman.api.endpoints.nicehash;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;
import mn.foreman.api.model.ApiType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

/** A {@link Nicehash} implementation. */
public class NicehashImpl
        implements Nicehash {

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
    public NicehashImpl(
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public List<ApiType> mappings() {
        return this.webUtil.get(
                "/api/nicehashv2")
                .flatMap(
                        s -> JsonUtils.fromJson(
                                s,
                                this.objectMapper,
                                new TypeReference<List<ApiType>>() {
                                }))
                .orElse(Collections.emptyList());
    }
}
