package mn.foreman.api.endpoints.qse.prioritypower;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * A {@link PriorityPower} provides a mechanism for relaying
 * PriorityPower-related RTAC information to/from Foreman.
 */
public interface PriorityPower {

    /**
     * Updates the RTAC state in Foreman.
     *
     * @param is4Cp            Whether a 4CP event.
     * @param basePoint        The base point.
     * @param targetSetPoint   The target set point.
     * @param consumptionWatts The consumption (W).
     * @param frequency        The frequency.
     *
     * @return The current load.
     */
    Optional<CurrentLoad> updateRtacStats(
            boolean is4Cp,
            double basePoint,
            double targetSetPoint,
            double consumptionWatts,
            double frequency);

    /** The load to configure into the RTAC. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class CurrentLoad {

        /** The high limit. */
        @JsonProperty("highLimit")
        public double highLimit;

        /** The low limit. */
        @JsonProperty("lowLimit")
        public double lowLimit;

        /** The curtailment run ID. */
        @JsonProperty("curtailmentRunId")
        public Integer runId;
    }
}
