package mn.foreman.api.endpoints.qse;

import mn.foreman.api.endpoints.qse.prioritypower.PriorityPower;

/** Endpoints for QSE-related processing. */
public interface Qse {

    /**
     * Returns a handler for publishing PriorityPower-related data to Foreman.
     *
     * @return The handler.
     */
    PriorityPower priorityPower();
}
