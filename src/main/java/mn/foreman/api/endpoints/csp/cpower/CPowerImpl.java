package mn.foreman.api.endpoints.csp.cpower;

import mn.foreman.api.WebUtil;
import mn.foreman.api.endpoints.qse.prioritypower.PriorityPowerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/** A simple implementation for communicating with the Foreman CPower API. */
public class CPowerImpl
        implements CPower {

    /** The logger for this class. */
    private static final Logger LOG =
            LoggerFactory.getLogger(PriorityPowerImpl.class);

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
    public CPowerImpl(
            final String pickaxeId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.pickaxeId = pickaxeId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Optional<Boolean> updateState(
            final double sixSecondBasePoint,
            final double fiveMinuteBasePoint,
            final double realtimeLoad) {
        Boolean success = null;
        try {
            final Optional<String> response =
                    this.webUtil.post(
                            String.format(
                                    "/api/v2/csp/cpower/plc/%s/state",
                                    this.pickaxeId),
                            this.objectMapper.writeValueAsString(
                                    ImmutableMap.of(
                                            "6SecBasePoint",
                                            sixSecondBasePoint,
                                            "5MinBasePoint",
                                            fiveMinuteBasePoint,
                                            "realtimeLoad",
                                            realtimeLoad)));
            success = response.isPresent();
        } catch (final Exception e) {
            LOG.warn("Failed to update PLC state", e);
        }
        return Optional.ofNullable(success);
    }
}
