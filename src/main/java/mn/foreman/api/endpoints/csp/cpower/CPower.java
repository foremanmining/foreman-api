package mn.foreman.api.endpoints.csp.cpower;

import java.util.Optional;

/** Endpoints for CPower-related processing. */
public interface CPower {

    /**
     * Updates the state in Foreman.
     *
     * @param sixSecondBasePoint  The 6 second base point.
     * @param fiveMinuteBasePoint The 5 minute base point.
     * @param realtimeLoad        The realtime load.
     *
     * @return The current load.
     */
    Optional<Boolean> updateState(
            double sixSecondBasePoint,
            double fiveMinuteBasePoint,
            double realtimeLoad);
}
