package mn.foreman.api.endpoints.qse.prioritypower;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/** An implementation that will communicate with the Foreman API. */
public class PriorityPowerImpl
        implements PriorityPower {

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
    public PriorityPowerImpl(
            final String pickaxeId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.pickaxeId = pickaxeId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Optional<CurrentLoad> updateRtacStats(
            final boolean is4Cp,
            final double basePoint,
            final double targetSetPoint) {
        Optional<CurrentLoad> currentLoad = Optional.empty();
        try {
            final Optional<String> response =
                    this.webUtil.post(
                            String.format(
                                    "/v2/qse/prioritypower/rtac/%s/state",
                                    this.pickaxeId),
                            this.objectMapper.writeValueAsString(
                                    ImmutableMap.of(
                                            "is4Cp",
                                            is4Cp,
                                            "basePoint",
                                            basePoint,
                                            "targetSetPoint",
                                            targetSetPoint)));
            if (response.isPresent()) {
                currentLoad =
                        JsonUtils.fromJson(
                                response.get(),
                                this.objectMapper,
                                new TypeReference<CurrentLoad>() {
                                });
            }
        } catch (final Exception e) {
            LOG.warn("Failed to update RTAC stats", e);
        }
        return currentLoad;
    }
}
