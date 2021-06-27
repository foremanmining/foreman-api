package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * A {@link CommandDoneBatch} represents all of the information related to
 * Pickaxe commands finishing.
 */
@Data
@Builder
public class CommandDoneBatch {

    /** The completion. */
    @JsonProperty("commands")
    @Singular
    private final List<BatchedDone> commands;

    /** The completed command. */
    @Data
    @Builder
    public static class BatchedDone {

        /** The command id. */
        @JsonProperty("commandId")
        private final String commandId;

        /** The command. */
        @JsonProperty("done")
        private final CommandDone done;
    }

    /** The response. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        /** The number of successful completions. */
        @JsonProperty("success")
        public int success;
    }
}