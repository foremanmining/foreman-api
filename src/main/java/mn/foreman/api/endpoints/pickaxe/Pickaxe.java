package mn.foreman.api.endpoints.pickaxe;

import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.model.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link Pickaxe} provides a handler for interacting with the
 * <code>/api/pickaxe</code> Foreman API endpoint.
 */
public interface Pickaxe {

    /**
     * Returns all of the pickaxes.
     *
     * @return All of the pickaxes.
     */
    List<PickaxeInstance> all();

    /**
     * Sends a command completion to the Foreman API.
     *
     * @param done      The completed command.
     * @param commandId The referencing command ID.
     *
     * @return The command response.
     */
    Optional<CommandDone.Response> commandDone(
            CommandDone done,
            String commandId);

    /**
     * Sends command completions to the Foreman API.
     *
     * @param done The completed commands.
     *
     * @return The command response.
     */
    Optional<CommandDoneBatch.Response> commandDoneBatch(
            CommandDoneBatch done);

    /**
     * Sends a command started to the Foreman API.
     *
     * @param start The started command.
     *
     * @return The command response.
     */
    Optional<CommandStart.Response> commandStarted(
            CommandStart start);

    /**
     * Sends command starts to the Foreman API.
     *
     * @param start The started commands.
     *
     * @return The command response.
     */
    Optional<CommandStartBatch.Response> commandStartedBatch(
            CommandStartBatch start);

    /**
     * Sends a command update to the Foreman API.
     *
     * @param update    The updated command.
     * @param commandId The referencing command ID.
     *
     * @return The command response.
     */
    Optional<CommandUpdate.Response> commandUpdate(
            CommandUpdate update,
            String commandId);

    /**
     * Sends command updates to the Foreman API.
     *
     * @param update The updates.
     *
     * @return The command response.
     */
    Optional<CommandUpdateBatch.Response> commandUpdateBatch(
            CommandUpdateBatch update);

    /**
     * Obtains the configuration.
     *
     * @return The configuration.
     */
    Optional<PickaxeConfiguration> config();

    /**
     * Queries for pending commands to be executed.
     *
     * @return The commands to execute, if any.
     */
    Optional<Commands> getCommands();

    /**
     * Sets the MACs for the provided miners..
     *
     * @param newMacs The new MACs.
     *
     * @return Whether or not the command was successful.
     */
    boolean updateMacs(Map<Miners.Miner, String> newMacs);

    /** The pickaxe configuration. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class PickaxeConfiguration {

        /** The command completion batch size. */
        @JsonProperty("commandCompletionBatchSize")
        public int commandCompletionBatchSize;

        /** Whether or not metrics should be compressed. */
        @JsonProperty("compressMetrics")
        public boolean compressMetrics;

        /** The read socket timeout. */
        @JsonProperty("readSocketTimeout")
        public int readSocketTimeout;

        /** The read socket timeout (units). */
        @JsonProperty("readSocketTimeoutUnits")
        public String readSocketTimeoutUnits;

        /** The write socket timeout. */
        @JsonProperty("writeSocketTimeout")
        public int writeSocketTimeout;

        /** The write socket timeout (units). */
        @JsonProperty("writeSocketTimeoutUnits")
        public String writeSocketTimeoutUnits;
    }

    /** A pickaxe instance. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class PickaxeInstance {

        /** The id. */
        @JsonProperty("id")
        public int id;

        /** The key. */
        @JsonProperty("key")
        public String key;

        /** The label. */
        @JsonProperty("label")
        public String label;

        /** The version. */
        @JsonProperty("version")
        public String version;
    }
}
