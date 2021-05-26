package mn.foreman.api.endpoints.groups;

import mn.foreman.api.endpoints.notifications.Notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

/**
 * A {@link Notifications} provides a handler for interacting with the
 * <code>/api/groups</code> Foreman API endpoint.
 */
public interface Groups {

    /**
     * Obtains the {@link GroupInfo}.
     *
     * @return The {@link GroupInfo}.
     */
    Optional<GroupInfo> groups();

    /** An object representing group information. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    class GroupInfo {

        /** The group name. */
        @JsonProperty("groupName")
        public String name;

        /** The sub-clients. */
        @JsonProperty("subClients")
        public List<SubClient> subClients;

        /** An object representing a sub-client. */
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SubClient {

            /** The client name. */
            @JsonProperty("clientName")
            public String clientName;

            /** The client ID. */
            @JsonProperty("id")
            public int id;
        }
    }
}
