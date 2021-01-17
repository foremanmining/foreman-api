package mn.foreman.api.endpoints.tags;

import java.util.List;

/** Provides an API handler for tagging miners. */
public interface Tags {

    /**
     * Tags the provided miner with the provided tags.
     *
     * @param minerId The miner to tag.
     * @param tags    The tags.
     *
     * @return Whether or not the tags were applied.
     */
    boolean tag(
            int minerId,
            List<String> tags);
}