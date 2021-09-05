package mn.foreman.api.endpoints.claymore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * A {@link Claymore} provides a handler for interacting with the claymore API
 * endpoints.
 */
public interface Claymore {

    /**
     * Returns all of the claymore mappings.
     *
     * @return The claymore mappings.
     */
    Map<String, BigDecimal> mappings();
}
