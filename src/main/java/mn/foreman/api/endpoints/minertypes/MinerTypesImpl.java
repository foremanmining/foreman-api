package mn.foreman.api.endpoints.minertypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;

import java.util.Collections;
import java.util.List;

/**
 * Miner types endpoint
 */
@RequiredArgsConstructor
public class MinerTypesImpl implements MinerTypes {

    /** The mapper. */
    private final ObjectMapper objectMapper;

    /** The web util. */
    private final WebUtil webUtil;

    @Override
    public List<MinerType> all() {
        return this.webUtil
                .get("/api/v2/pickaxe/miner-types", false)
                .flatMap(
                        s -> JsonUtils.fromJson(
                                s,
                                this.objectMapper,
                                new TypeReference<MinerTypeResponse>() {
                                }))
                .map(MinerTypeResponse::getTypes)
                .orElse(Collections.emptyList());
    }

    /**
     * The expected response
     */
    @Getter
    private static class MinerTypeResponse {

        /** Miner types */
        private final List<MinerType> types;

        /**
         * Constructor
         * @param types The miner types
         */
        @JsonCreator
        private MinerTypeResponse(@JsonProperty("types") List<MinerType> types) {
            this.types = types;
        }
    }

}