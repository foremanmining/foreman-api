package mn.foreman.api.endpoints.minertypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Provides a handler for interacting with the
 * <code>/api/v2/pickaxe/miner-types</code> Foreman API endpoint
 * to retrieve supported miner types.
 */
public interface MinerTypes {

    List<MinerType> all();

    /**
     * The miner type object to retrieve
     */
    @Getter
    class MinerType {

        /** Manufacturer */
        private final String manufacturer;

        /** Slug */
        private final String slug;

        /** Category */
        private final String category;

        /** Hash rates */
        private final List<String> hashRates;

        /** Additional attributes */
        private final Map<String, String> additionalAttributes;

        /** Sub types **/
        private final List<MinerType> subTypes;

        /**
         * Constructor
         * @param manufacturer         The manufacturer
         * @param slug                 The slug
         * @param category             The category
         * @param hashRates            The hash rates
         * @param additionalAttributes The additional attributes
         * @param subTypes             The sub types
         */
        @JsonCreator
        public MinerType(@JsonProperty("manufacturer") String manufacturer,
                         @JsonProperty("slug") String slug,
                         @JsonProperty("category") String category,
                         @JsonProperty("hashRates") List<String> hashRates,
                         @JsonProperty("additionalAttributes") Map<String, String> additionalAttributes,
                         @JsonProperty("subTypes") List<MinerType> subTypes) {
            this.manufacturer = manufacturer;
            this.slug = slug;
            this.category = category;
            this.hashRates = hashRates;
            this.additionalAttributes = additionalAttributes;
            this.subTypes = subTypes;
        }
    }
}
