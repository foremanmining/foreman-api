package mn.foreman.api.endpoints.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

/**
 * A {@link Notifications} provides a handler for interacting with the
 * <code>/api/notifications</code> Foreman API endpoint.
 */
public interface Notifications {

    /**
     * Obtains the Discord notifications.
     *
     * @param sinceId        The previously observed id (-1 to disable).
     * @param sinceTimestamp The cutoff date (null to disable).
     *
     * @return The Discord notifications.
     */
    List<Notification> discord(
            int sinceId,
            Instant sinceTimestamp);

    /**
     * Obtains the Google Chat notifications.
     *
     * @param sinceId        The previously observed id (-1 to disable).
     * @param sinceTimestamp The cutoff date (null to disable).
     *
     * @return The Google Chat notifications.
     */
    List<Notification> googleChat(
            int sinceId,
            Instant sinceTimestamp);

    /**
     * Obtains the Slack notifications.
     *
     * @param sinceId        The previously observed id (-1 to disable).
     * @param sinceTimestamp The cutoff date (null to disable).
     *
     * @return The Slack notifications.
     */
    List<Notification> slack(
            int sinceId,
            Instant sinceTimestamp);

    /**
     * Obtains the Telegram notifications.
     *
     * @param sinceId        The previously observed id (-1 to disable).
     * @param sinceTimestamp The cutoff date (null to disable).
     *
     * @return The Telegram notifications.
     */
    List<Notification> telegram(
            int sinceId,
            Instant sinceTimestamp);

    /** A notification. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    class Notification {

        /** The failing miners. */
        @JsonProperty("failingMiners")
        public List<FailingMiner> failingMiners;

        /** The notification ID. */
        @JsonProperty("id")
        public int id;

        /** When the notification was published. */
        @JsonProperty("published")
        public Instant published;

        /** The subject. */
        @JsonProperty("subject")
        public String subject;

        /** A miner that is currently failing. */
        @JsonIgnoreProperties(ignoreUnknown = true)
        @ToString
        public static class FailingMiner {

            /** The diagnosis. */
            @JsonProperty("diagnosis")
            public List<String> diagnosis;

            /** The miner's name. */
            @JsonProperty("miner")
            public String miner;

            /** The miner ID. */
            @JsonProperty("minerId")
            public int minerId;
        }
    }
}
