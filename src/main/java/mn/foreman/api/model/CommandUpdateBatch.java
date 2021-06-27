package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * A {@link CommandUpdateBatch} represents updates to the Foreman dashboard from
 * commands being ran.
 */
@Data
@Builder
public class CommandUpdateBatch {

    /** The updates. */
    @JsonProperty("commands")
    @Singular
    private final List<BatchedUpdate> commands;

    /** The started command. */
    @Data
    @Builder
    public static class BatchedUpdate {

        /** The command id. */
        @JsonProperty("commandId")
        private final String commandId;

        /** The command. */
        @JsonProperty("update")
        private final CommandUpdate update;
    }

    /** The response. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        /** The number of successful updates. */
        @JsonProperty("success")
        public int success;
    }
}
