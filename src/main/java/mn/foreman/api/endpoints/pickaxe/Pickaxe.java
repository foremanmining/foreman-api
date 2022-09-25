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
     * Cancels all of the pending or in-progress commands on this Pickaxe.
     *
     * @return The cancelled command IDs.
     */
    Optional<List<Integer>> cancelCommands();

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
    Optional<List<MinerConfig>> minerConfigs(
            String version,
            String hostname,
            String hostIp);

    /**
     * Informs Foreman that the Pickaxe has started.
     *
     * @return Whether successful.
     */
    boolean started();

    /**
     * Sets the MACs for the provided miners.
     *
     * @param newMacs The new MACs.
     *
     * @return Whether the command was successful.
     */
    boolean updateMacs(Map<Miners.Miner, String> newMacs);

    /** All of the cancelled commands. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class CancelledCommands {

        /** The cancelled IDs. */
        @JsonProperty("cancelledIds")
        public List<Integer> cancelledIds;
    }

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

        /** The collect delay. */
        @JsonProperty("collectDelay")
        public int collectDelay;

        /** The collect delay (units). */
        @JsonProperty("collectDelayUnits")
        public String collectDelayUnits;

        /** The command completion batch size. */
        @JsonProperty("commandCompletionBatchSize")
        public int commandCompletionBatchSize;

        /** The command query delay. */
        @JsonProperty("commandQueryDelay")
        public int commandQueryDelay;

        /** The command query delay (units). */
        @JsonProperty("commandQueryDelayUnits")
        public String commandQueryDelayUnits;

        /** The number of threads to use for processing commands. */
        @JsonProperty("commandThreadsOverride")
        public Integer commandThreadsOverride;

        /** Whether or not metrics should be compressed. */
        @JsonProperty("compressMetrics")
        public boolean compressMetrics;

        /** Whether the CPower PLC integration is enabled. */
        @JsonProperty("cpowerPlcEnabled")
        public boolean cpowerPlcEnabled;

        /** The CPower PLC IP address. */
        @JsonProperty("cpowerPlcIp")
        public String cpowerPlcIp;

        /** The CPower PLC password. */
        @JsonProperty("cpowerPlcPassword")
        public int cpowerPlcPassword;

        /** The CPower PLC port. */
        @JsonProperty("cpowerPlcPort")
        public int cpowerPlcPort;

        /** The CPower PLC username. */
        @JsonProperty("cpowerPlcUsername")
        public int cpowerPlcUsername;

        /** The JVM arguments. */
        @JsonProperty("jvmArguments")
        public String jvmArguments;

        /** The size of each metrics batch. */
        @JsonProperty("metricsBatchSize")
        public Integer metricsBatchSize;

        /** The metrics push delay. */
        @JsonProperty("metricsPushDelay")
        public int metricsPushDelay;

        /** The metrics push delay (units). */
        @JsonProperty("metricsPushDelayUnits")
        public String metricsPushDelayUnits;

        /** The number of IPs to scan per second. */
        @JsonProperty("metricsRateLimit")
        public Integer metricsRateLimit;

        /** The number of threads to use for metrics sending. */
        @JsonProperty("metricsThreadsOverride")
        public Integer metricsThreadsOverride;

        /** Whether priority power integration is enabled. */
        @JsonProperty("priorityPowerRtacEnabled")
        public boolean priorityEnabled;

        /** The priority power RTAC client address. */
        @JsonProperty("priorityPowerRtacLocalAddress")
        public int priorityRtacClientAddress;

        /** The priority power RTAC IP address. */
        @JsonProperty("priorityPowerRtacIp")
        public String priorityRtacIp;

        /** The priority power RTAC port. */
        @JsonProperty("priorityPowerRtacPort")
        public int priorityRtacPort;

        /** The priority power RTAC server address. */
        @JsonProperty("priorityPowerRtacRemoteAddress")
        public int priorityRtacServerAddress;

        /** The number of IPs to scan per second. */
        @JsonProperty("rangesRateLimit")
        public Integer rangesRateLimit;

        /** The number of threads to use for scanning (ranges). */
        @JsonProperty("rangesScannerThreadsOverride")
        public Integer rangesScannerThreadsOverride;

        /** The number of IPs to scan per second. */
        @JsonProperty("rangesTargetedRateLimit")
        public Integer rangesTargetedRateLimit;

        /** The read socket timeout. */
        @JsonProperty("readSocketTimeout")
        public int readSocketTimeout;

        /** The read socket timeout (units). */
        @JsonProperty("readSocketTimeoutUnits")
        public String readSocketTimeoutUnits;

        /** The number of IPs to scan per second. */
        @JsonProperty("startStopRateLimit")
        public Integer startStopRateLimit;

        /** The number of threads to use for scanning (start/stop). */
        @JsonProperty("startStopScannerThreadsOverride")
        public Integer startStopScannerThreadsOverride;

        /** The number of IPs to scan per second. */
        @JsonProperty("startStopTargetedRateLimit")
        public Integer startStopTargetedRateLimit;

        /** The number of threads to use for stats querying. */
        @JsonProperty("statsThreadsOverride")
        public Integer statsThreadsOverride;

        /** The number of threads to use for scanning (targeted, ranges). */
        @JsonProperty("targetedRangesScannerThreadsOverride")
        public Integer targetedRangesScannerThreadsOverride;

        /** The number of threads to use for scanning (targeted, start/stop). */
        @JsonProperty("targetedStartStopScannerThreadsOverride")
        public Integer targetedStartStopScannerThreadsOverride;

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