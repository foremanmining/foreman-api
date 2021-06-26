package mn.foreman.api.endpoints.actions;

import mn.foreman.api.endpoints.StatusSubmit;
import mn.foreman.api.model.Network;
import mn.foreman.api.model.Pool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

/**
 * An {@link Actions} provides a handler for interacting with the
 * <code>/api/actions</code> Foreman API endpoint.
 */
public interface Actions {

    /**
     * Changes the network for the provided miner.
     *
     * @param minerId The miner ID.
     * @param network The new network configuration.
     *
     * @return The response, if present.
     */
    Optional<Response> changeNetwork(
            int minerId,
            Network network);

    /**
     * Changes the pools for the provided miner.
     *
     * @param minerId The miner ID.
     * @param pools   The new pools.
     *
     * @return The response, if present.
     */
    Optional<Response> changePools(
            int minerId,
            List<Pool> pools);

    /**
     * Changes the power mode for the provided miner.
     *
     * @param minerId The miner ID.
     * @param mode    The power mode.
     *
     * @return The response, if present.
     */
    Optional<Response> changePowerMode(
            int minerId,
            PowerMode mode);

    /**
     * Gets the status of the provided command.
     *
     * @param command The command.
     *
     * @return The status, if present.
     */
    Optional<StatusRunning> status(
            int command);

    /** All of the known power modes. */
    enum PowerMode {

        /** Sleeping. */
        SLEEPING,

        /** Low power mode. */
        LOW,

        /** Normal power mode. */
        NORMAL,

        /** High power mode. */
        HIGH;
    }

    /** An action response. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Response {

        /** The new command ID. */
        @JsonProperty("command")
        public int command;

        /** The status. */
        @JsonProperty("status")
        public StatusSubmit status;
    }
}