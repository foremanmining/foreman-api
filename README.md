# foreman-api

The following repository provides a Java library for interacting with the
Foreman API. It provides handlers to the following endpoints:

- actions
- miners
- notifications
- pickaxe
- ping
- site-map
- tags

## Endpoints

### actions

`/api/actions`

API endpoint that can be leveraged to run actions against miners and check the
action statuses. The following actions are supported by this library:

- change-pools: apply a pool change to the provided ASICs
- network: apply a network config to the provided ASICs

### miners

`/api/miners`

API endpoint that can be leveraged to obtain the current miners in Foreman to
observe their status and configuration.

### notifications

`/api/notifications`

API endpoint to obtain notifications that were fired as a result of triggers and
miner status.

### pickaxe

`/api/pickaxe`

API endpoint that can be leveraged to interact with a Pickaxe, providing the
ability to obtain commands and provide status updates regarding them as they
progress.

### ping

`/api/ping`

API endpoint to test connectivity with the Foreman API and authenticate the
provided credentials against the provided client.

### site-map

`/api/site-map`

API endpoint that can be used to manipulate a Site Map configuration.

### tags

`/api/tags`

API endpoint that can be used to create new tags and apply them to miners.

## Requirements

- JDK version 8 (or higher)

## License ##

Copyright © 2021, [OBM LLC](https://obm.mn/). Released under
the [GPL-3.0 License](LICENSE).
