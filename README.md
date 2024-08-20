# Basic Tock Story implementation with Kotlin

Basic bot that could be used as a starting point to implement a Tock story or for debug.

## Build

```shell
make build
```

## Run

```shell
make start
```

## Use it

### 1) deploy [Tock](https://github.com/theopenconversationkit/tock) via Helm chart

You need a K8S cluster with Helm installed

```shell
$ helm install my-release oci://registry.hub.docker.com/onelans/tock --version 0.4.3
```

On OSX, you can use Rancher Desktop to deploy a local K8S cluster

```shell
$ helm install my-release oci://registry.hub.docker.com/onelans/tock --version 0.4.3 -f values.yaml
```

With `values.yaml` content:

```yaml
global:
  wildcardDomain: rancher.localhost
  deployMongoDb:
    enabled: true

botApi:
  ingress:
    enabled: true

adminWeb:
  ingress:
    enabled: true

mongodb:
  architecture: "replicaset"
  auth:
    enabled: false
  persistence:
    enabled: false
    size: 1Gi
  image:
    repository: zcube/bitnami-compat-mongodb
    tag: 6.0.5
```
You can access Tock Studio at `http://tockstudio-my-release.rancher.localhost`


### 2) Start this story

Story is implemented in Kotlin, as a webhook server(default) or as WebSocket Client. Webhook will be available at `http://localhost:8887`
```shell
$ make start
```

__Run as websocket client__

If you want start Story as WebSocket client, you can set `TOCK_BOT_API_WEBSOCKET` to `true`. In its case, don't forget to set :
- `TOCK_BOT_API_URL` to the websocket url of your tock instance. Default is localhost
- `TOCK_BOT_API_KEY` to the api key of your tock instance
- `TOCK_BOT_API_PORT` to the port of your tock instance. default is 8080
- `TOCK_BOT_API_SSL` to true if your tock instance is using ssl


### 3) Configure tock to use this story

Edit your bot setting to use this story implementation as webhook

Sample, if tock is running in rancher desktop k8s.   
Webhook url : http://host.docker.internal:8887
>Because rancher desktop is using a VM, you need to use `host.docker.internal` to access your local machine.

### 4) Enjoy with test cases

Set `debug` as intent to see debugBot response.
