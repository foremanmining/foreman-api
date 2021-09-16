package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/** The commands to run. */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commands {

    /** The commands to run. */
    @JsonProperty("commands")
    public List<CommandStart> commands;

    /** How long to wait before polling for commands again. */
    @JsonProperty("delaySeconds")
    public int delaySeconds;
}