## Teste Técnico da Ubots

![screenshot](https://raw.githubusercontent.com/mateusmento/invext/main/screenshot.jpeg)

Essa solução inclui uma api em Java e Spring Boot e uma aplicação web em React. Para resolver um problema do teste, foi elaborado uma aplicação para permitir que clientes possam fazer solicitação de atendimentos e atendentes possam atender e finalizar as solicitações. Em caso de indisponibilidade dos atendentes, as solicitações ficam pendentes até que encontrado um atendente disponível. Com o uso de WebSockets, a aplicação permite uma interação em tempo real, de modo a notificar clientes quando atendantes estão disponíveis, assim como atualizar os atendantes quando novas solicitações são feitas.

Antes de executar a solução, certifique-se de ter os seguintes dependencias:

- Docker (23.0.1)
- Node.js (v18.12.1)

Para executar a api:
```bash
cd invext-api
docker compose up
```

Para executar a web app:
```bash
cd invext-app
nvm use
npm install
npm start
```
