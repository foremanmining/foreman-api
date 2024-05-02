package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * A {@link CommandDone} represents all of the information related to a Pickaxe
 * command finishing.
 */
@Data
@Builder
public class CommandDone
        implements Command {

    /** The command. */
    @JsonProperty("command")
    private final String command;

    /** The result. */
    @JsonProperty("result")
    private final Map<String, Object> result;

    /** The status code. */
    @JsonProperty("status")
    private final Status status;

    /** The response. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        /** The command status. */
        @JsonProperty("status")
        public String status;
    }

    /** The status. */
    @Data
    @Builder
    public static class Status {

        /** Max length of message. */
        private static final int MESSAGE_MAX_LENGTH = 255;

        /** A detailed message. */
        @JsonProperty("details")
        public String details;

        /** The message. */
        @JsonProperty("message")
        public String message;

        /** The type. */
        @JsonProperty("type")
        public DoneStatus type;

        /**
         * Sets the message with max size MESSAGE_MAX_LENGTH.
         *
         * @param message The message.
         */
        public void setMessage(final String message) {
            this.message = StringUtils.truncate(message, MESSAGE_MAX_LENGTH);
        }

        /**
         * Builder object for {@link Status}.
         */
        public static class StatusBuilder {

            /**
             * Sets message with a max length of MESSAGE_MAX_LENGTH.
             *
             * @param message The message to set.
             *
             * @return The builder.
             */
            public StatusBuilder message(final String message) {
                this.message = StringUtils.truncate(message, MESSAGE_MAX_LENGTH);
                return this;
            }
        }
    }
}