package mn.foreman.api.endpoints.csp;

import mn.foreman.api.endpoints.csp.cpower.CPower;

/** Endpoints for CSP-related processing. */
public interface Csp {

    /**
     * Returns a new {@link CPower}.
     *
     * @return A new {@link CPower}.
     */
    CPower cpower();
}
