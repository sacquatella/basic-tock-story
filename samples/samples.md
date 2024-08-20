# Queries samples

These samples are used to test and simulate the behavior of the Tock bot API.

## Query tock_bot_api_actions_history_to_client_bus set to false

```shell
curl -X POST -H 'Content-Type: application/json' http://localhost:8887/webhook -d @tmp/post-no-bus.json
```

## Query tock_bot_api_actions_history_to_client_bus set to true

```shell
curl -X POST -H 'Content-Type: application/json' http://localhost:8887/webhook -d @tmp/post-with-history.json
```