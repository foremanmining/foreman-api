package mn.foreman.api;

import mn.foreman.api.endpoints.actions.Actions;
import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.endpoints.notifications.Notifications;
import mn.foreman.api.endpoints.pickaxe.Pickaxe;
import mn.foreman.api.endpoints.ping.Ping;
import mn.foreman.api.endpoints.sitemap.SiteMap;
import mn.foreman.api.endpoints.tags.Tags;

/**
 * A {@link ForemanApi} provides a mechanism for interacting with the Foreman
 * API.
 */
public interface ForemanApi {

    /**
     * Creates a new {@link Actions} that can be leveraged to operate on the
     * <code>/api/actions</code> Foreman API endpoint.
     *
     * @return The API handler.
     */
    Actions actions();

    /**
     * Creates a new {@link Miners} that can be leveraged to operate on the
     * <code>/api/miners</code> Foreman API endpoint.
     *
     * @return The API handler.
     */
    Miners miners();

    /**
     * Creates a new {@link Notifications} that can be leveraged to operate on
     * the <code>/api/notifications</code> Foreman API endpoint.
     *
     * @return The API handler.
     */
    Notifications notifications();

    /**
     * Creates a new {@link Pickaxe} that can be leveraged to operate on the
     * <code>/api/pickaxe</code> Foreman API endpoint.
     *
     * @return The API handler.
     */
    Pickaxe pickaxe();

    /**
     * Creates a new {@link Ping} that can be leveraged to test API
     * credentials.
     *
     * @return The new {@link Ping}.
     */
    Ping ping();

    /**
     * Creates a new {@link SiteMap} that can be leveraged to operate on the
     * <code>/api/site-map</code> Foreman API endpoint.
     *
     * @return The API handler.
     */
    SiteMap siteMap();

    /**
     * Creates a new {@link Tags} that can be leveraged to operate on the
     * <code>/api/tags</code> Foreman API endpoint.
     *
     * @return The API handlers.
     */
    Tags tags();
}
