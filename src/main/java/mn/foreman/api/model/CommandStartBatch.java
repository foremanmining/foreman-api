package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * A {@link CommandStartBatch} represents command to be performed against
 * miners.
 */
@Data
@Builder
public class CommandStartBatch {

    /** The starts. */
    @JsonProperty("commands")
    @Singular
    private final List<BatchedStart> commands;

    /** The started command. */
    @Data
    @Builder
    public static class BatchedStart {

        /** The command id. */
        @JsonProperty("commandId")
        private final String commandId;

        /** The command. */
        @JsonProperty("start")
        private final CommandStart start;
    }

    /** The response. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        /** The number of successful starts. */
        @JsonProperty("success")
        public int success;
    }
}
