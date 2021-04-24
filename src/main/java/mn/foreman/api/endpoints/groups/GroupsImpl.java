package mn.foreman.api.endpoints.groups;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;
import mn.foreman.api.endpoints.actions.Actions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/** A simple {@link Actions} implementation. */
public class GroupsImpl
        implements Groups {

    /** The client ID. */
    private final String clientId;

    /** The json mapper. */
    private final ObjectMapper objectMapper;

    /** The web utilities. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param clientId     The client ID.
     * @param objectMapper The mapper.
     * @param webUtil      The web utilities.
     */
    public GroupsImpl(
            final String clientId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.clientId = clientId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Optional<GroupInfo> groups() {
        return this.webUtil.get(
                String.format(
                        "/api/groups/%s",
                        this.clientId))
                .flatMap(
                        response ->
                                JsonUtils.fromJson(
                                        response,
                                        this.objectMapper,
                                        new TypeReference<GroupInfo>() {
                                        }));
    }
}