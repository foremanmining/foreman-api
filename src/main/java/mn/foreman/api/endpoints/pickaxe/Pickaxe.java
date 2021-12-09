package mn.foreman.api.endpoints.pickaxe;

import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.model.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
     * Returns the miner configurations.
     *
     * @param version  The version.
     * @param hostname The hostname.
     * @param hostIp   The host IP.
     *
     * @return The miner configurations.
     */
    List<MinerConfig> minerConfigs(
            String version,
            String hostname,
            String hostIp);

    /**
     * Sets the MACs for the provided miners.
     *
     * @param newMacs The new MACs.
     *
     * @return Whether the command was successful.
     */
    boolean updateMacs(Map<Miners.Miner, String> newMacs);

    /**
     * Informs Foreman that the Pickaxe has started.
     *
     * @return Whether successful.
     */
    boolean started();

    /** A miner configuration. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class MinerConfig {

        /** The api ip. */
        @JsonProperty("apiIp")
        public String apiIp;

        /** The api port. */
        @JsonProperty("apiPort")
        public int apiPort;

        /** The API type. */
        @JsonProperty("apiType")
        public ApiType apiType;

        /** The chisel configuration. */
        @JsonProperty("chisel")
        public ChiselConfig chisel;

        /** The parameters. */
        @JsonProperty("params")
        public List<Param> params;

        /** A chisel configuration. */
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ChiselConfig {

            /** The port where chisel is listening. */
            @JsonProperty("apiPort")
            public int apiPort;

            @Override
            public String toString() {
                return String.format("%s [ apiPort=%d ]",
                        getClass().getSimpleName(),
                        this.apiPort);
            }
        }

        /** A miner configuration parameter. */
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Param {

            /** The key. */
            @JsonProperty("key")
            public String key;

            /** The value. */
            @JsonProperty("value")
            public Object value;

            @Override
            public String toString() {
                return String.format("%s [ key=%s, value=%s ]",
                        getClass().getSimpleName(),
                        this.key,
                        this.value);
            }
        }
    }

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