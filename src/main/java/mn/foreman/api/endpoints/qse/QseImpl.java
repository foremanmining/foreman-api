package mn.foreman.api.endpoints.qse;

import mn.foreman.api.WebUtil;
import mn.foreman.api.endpoints.qse.prioritypower.PriorityPower;
import mn.foreman.api.endpoints.qse.prioritypower.PriorityPowerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

/** An implementation to handle QSE-related actions against the Foreman API. */
public class QseImpl
        implements Qse {

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
    public QseImpl(
            final String pickaxeId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.pickaxeId = pickaxeId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public PriorityPower priorityPower() {
        return new PriorityPowerImpl(
                this.pickaxeId,
                this.objectMapper,
                this.webUtil);
    }
}
