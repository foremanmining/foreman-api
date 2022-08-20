package mn.foreman.api.endpoints.qse.prioritypower;

import lombok.Builder;
import lombok.ToString;

import java.util.Optional;

/**
 * A {@link PriorityPower} provides a mechanism for relaying
 * PriorityPower-related RTAC information to/from Foreman.
 */
public interface PriorityPower {

    /**
     * Updates the RTAC state in Foreman.
     *
     * @param is4Cp          Whether a 4CP event.
     * @param basePoint      The base point.
     * @param targetSetPoint The target set point.
     *
     * @return The current load.
     */
    Optional<CurrentLoad> updateRtacStats(
            boolean is4Cp,
            double basePoint,
            double targetSetPoint);

    /** The load to configure into the RTAC. */
    @Builder
    @ToString
    class CurrentLoad {

        /** The high limit. */
        double highLimit;

        /** The low limit. */
        double lowLimit;
    }
}
