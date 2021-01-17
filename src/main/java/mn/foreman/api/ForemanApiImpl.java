package mn.foreman.api;

import mn.foreman.api.endpoints.actions.Actions;
import mn.foreman.api.endpoints.actions.ActionsImpl;
import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.endpoints.miners.MinersImpl;
import mn.foreman.api.endpoints.notifications.Notifications;
import mn.foreman.api.endpoints.notifications.NotificationsImpl;
import mn.foreman.api.endpoints.pickaxe.Pickaxe;
import mn.foreman.api.endpoints.pickaxe.PickaxeImpl;
import mn.foreman.api.endpoints.ping.Ping;
import mn.foreman.api.endpoints.ping.PingImpl;
import mn.foreman.api.endpoints.sitemap.SiteMap;
import mn.foreman.api.endpoints.sitemap.SiteMapImpl;
import mn.foreman.api.endpoints.tags.Tags;
import mn.foreman.api.endpoints.tags.TagsImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

/** An implementation of a {@link ForemanApi}. */
public class ForemanApiImpl
        implements ForemanApi {

    /** The client ID. */
    private final String clientId;

    /** The json mapper. */
    private final ObjectMapper objectMapper;

    /** The pickaxe ID. */
    private final String pickaxeId;

    /** The web utilities. */
    private final WebUtil webUtil;

    /**
     * Constructor.
     *
     * @param clientId     The client ID.
     * @param pickaxeId    The pickaxe ID.
     * @param objectMapper The mapper.
     * @param webUtil      The web utilities.
     */
    public ForemanApiImpl(
            final String clientId,
            final String pickaxeId,
            final ObjectMapper objectMapper,
            final WebUtil webUtil) {
        this.clientId = clientId;
        this.pickaxeId = pickaxeId;
        this.objectMapper = objectMapper;
        this.webUtil = webUtil;
    }

    @Override
    public Actions actions() {
        return new ActionsImpl(
                this.objectMapper,
                this.webUtil);
    }

    @Override
    public Miners miners() {
        return new MinersImpl(
                this.clientId,
                this.pickaxeId,
                this.objectMapper,
                this.webUtil);
    }

    @Override
    public Notifications notifications() {
        return new NotificationsImpl(
                this.clientId,
                this.objectMapper,
                this.webUtil);
    }

    @Override
    public Pickaxe pickaxe() {
        return new PickaxeImpl(
                this.pickaxeId,
                this.objectMapper,
                this.webUtil);
    }

    @Override
    public Ping ping() {
        return new PingImpl(
                this.clientId,
                this.webUtil);
    }

    @Override
    public SiteMap siteMap() {
        return new SiteMapImpl(
                this.objectMapper,
                this.webUtil);
    }

    @Override
    public Tags tags() {
        return new TagsImpl(
                this.objectMapper,
                this.webUtil);
    }
}