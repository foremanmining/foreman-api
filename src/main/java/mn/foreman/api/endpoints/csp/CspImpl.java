package mn.foreman.api.endpoints.csp;

import mn.foreman.api.WebUtil;
import mn.foreman.api.endpoints.csp.cpower.CPower;
import mn.foreman.api.endpoints.csp.cpower.CPowerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

/** An implementation to handle CSP-related actions against the Foreman API. */
public class CspImpl
        implements Csp {

    /** The mapper for JSON processing. */
    private final ObjectMapper objectMapper;

    /** The pickaxe ID. */
    private final String pickaxeId;

    /** The web util. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param pickaxeId    The pickaxe ID.
     * @param objectMapper The mapper for JSON.
     * @param webUtil      The web util.
     */
    public CspImpl(
            final String pickaxeId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.pickaxeId = pickaxeId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public CPower cpower() {
        return new CPowerImpl(
                this.pickaxeId,
                this.objectMapper,
                this.webUtil);
    }
}
