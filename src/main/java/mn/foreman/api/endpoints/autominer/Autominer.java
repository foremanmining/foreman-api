package mn.foreman.api.endpoints.autominer;

import mn.foreman.api.model.ApiType;

import java.util.Map;

/**
 * An {@link Autominer} provides a handler for interacting with the autominer
 * API endpoints.
 */
public interface Autominer {

    /**
     * Returns all of the autominer mappings.
     *
     * @return The autominer mappings.
     */
    Map<String, ApiType> mappings();
}
