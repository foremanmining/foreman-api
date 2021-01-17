package mn.foreman.api.endpoints.pickaxe;

import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.model.CommandDone;
import mn.foreman.api.model.CommandStart;
import mn.foreman.api.model.CommandUpdate;
import mn.foreman.api.model.Commands;

import java.util.Map;
import java.util.Optional;

/**
 * A {@link Pickaxe} provides a handler for interacting with the
 * <code>/api/pickaxe</code> Foreman API endpoint.
 */
public interface Pickaxe {

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
     * Sends a command started to the Foreman API.
     *
     * @param start The started command.
     *
     * @return The command response.
     */
    Optional<CommandStart.Response> commandStarted(
            CommandStart start);

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
}
