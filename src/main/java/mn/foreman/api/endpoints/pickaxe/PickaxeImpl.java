package mn.foreman.api.endpoints.pickaxe;

import mn.foreman.api.JsonUtils;
import mn.foreman.api.WebUtil;
import mn.foreman.api.endpoints.miners.Miners;
import mn.foreman.api.model.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** A simple {@link Pickaxe} implementation. */
public class PickaxeImpl
        implements Pickaxe {

    /** The logger for this class. */
    private static final Logger LOG =
            LoggerFactory.getLogger(PickaxeImpl.class);

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
    public PickaxeImpl(
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
    public List<PickaxeInstance> all() {
        return this.webUtil.get(
                String.format(
                        "/api/pickaxe/%s",
                        this.clientId))
                .flatMap(
                        s -> JsonUtils.fromJson(
                                s,
                                this.objectMapper,
                                new TypeReference<List<PickaxeInstance>>() {
                                }))
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<CommandDone.Response> commandDone(
            final CommandDone done,
            final String commandId) {
        Optional<CommandDone.Response> result = Optional.empty();
        try {
            final Optional<String> response =
                    this.webUtil.post(
                            String.format(
                                    "/api/pickaxe/%s/command/%s/done",
                                    this.pickaxeId,
                                    commandId),
                            this.objectMapper.writeValueAsString(done));
            if (response.isPresent()) {
                result =
                        JsonUtils.fromJson(
                                response.get(),
                                this.objectMapper,
                                new TypeReference<CommandDone.Response>() {
                                });
            }
        } catch (final JsonProcessingException e) {
            LOG.warn("Exception occurred while parsing json", e);
        }
        return result;
    }

    @Override
    public Optional<CommandStart.Response> commandStarted(
            final CommandStart start) {
        final Optional<String> response =
                this.webUtil.post(
                        String.format(
                                "/api/pickaxe/%s/command/%s/start",
                                this.pickaxeId,
                                start.id));
        if (response.isPresent()) {
            return JsonUtils.fromJson(
                    response.get(),
                    this.objectMapper,
                    new TypeReference<CommandStart.Response>() {
                    });
        }
        return Optional.empty();
    }

    @Override
    public Optional<CommandUpdate.Response> commandUpdate(
            final CommandUpdate update,
            final String commandId) {
        Optional<CommandUpdate.Response> result = Optional.empty();
        try {
            final Optional<String> response =
                    this.webUtil.post(
                            String.format(
                                    "/api/pickaxe/%s/command/%s/update",
                                    this.pickaxeId,
                                    commandId),
                            this.objectMapper.writeValueAsString(update));
            if (response.isPresent()) {
                result =
                        JsonUtils.fromJson(
                                response.get(),
                                this.objectMapper,
                                new TypeReference<CommandUpdate.Response>() {
                                });
            }
        } catch (final JsonProcessingException e) {
            LOG.warn("Exception occurred while parsing json", e);
        }
        return result;
    }

    @Override
    public Optional<Commands> getCommands() {
        final Optional<String> response =
                this.webUtil.get(
                        String.format(
                                "/api/pickaxe/%s/commands",
                                this.pickaxeId));
        if (response.isPresent()) {
            return JsonUtils.fromJson(
                    response.get(),
                    this.objectMapper,
                    new TypeReference<Commands>() {
                    });
        }
        return Optional.empty();
    }

    @Override
    public boolean updateMacs(final Map<Miners.Miner, String> newMacs) {
        boolean updated = false;
        final List<List<MacUpdate>> macUpdates =
                Lists.partition(
                        newMacs
                                .entrySet()
                                .stream()
                                .map(entry -> {
                                    final Miners.Miner miner = entry.getKey();
                                    return MacUpdate
                                            .builder()
                                            .ip(miner.apiIp)
                                            .apiPort(miner.apiPort)
                                            .mac(entry.getValue())
                                            .build();
                                })
                                .collect(Collectors.toList()),
                        100);
        if (!macUpdates.isEmpty()) {
            for (final List<MacUpdate> update : macUpdates) {
                try {
                    final Optional<String> response =
                            this.webUtil.post(
                                    String.format(
                                            "/api/pickaxe/%s/macs",
                                            this.pickaxeId),
                                    this.objectMapper.writeValueAsString(
                                            ImmutableMap.of(
                                                    "updates",
                                                    update)));
                    if (response.isPresent()) {
                        LOG.info("Received response: {}", response);
                        updated = true;
                    }
                } catch (final JsonProcessingException e) {
                    LOG.warn("Exception occurred while parsing json", e);
                }
            }
        } else {
            // Nothing to do, so successful
            updated = true;
        }
        return updated;
    }
}