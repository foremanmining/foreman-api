package mn.foreman.api.endpoints.nicehash;

import mn.foreman.api.model.ApiType;

import java.util.List;

/**
 * A {@link Nicehash} provides a handler for interacting with the nicehash API
 * endpoints.
 */
public interface Nicehash {

    /**
     * Returns all of the nicehash mappings.
     *
     * @return The nicehash mappings.
     */
    List<ApiType> mappings();
}
