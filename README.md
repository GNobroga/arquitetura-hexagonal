# Hexagonal

Este é um projeto simples que eu desenvolvi utilizando as arquiteturas de Ports and Adapters e Domain-Driven Design.

## Observações


Achei essa arquitetura muito parecida com a Clean Architecture, pois ela foca na ideia de manter nosso domínio livre de frameworks. Os ports são interfaces e os adapters são componentes que implementam a lógica. Além disso, existem os inbounds e outbounds, que são recursos responsáveis pela comunicação interna e externa do sistema.

## Execução

1. Instale as dependências:

### Backend

Clone ou baixe os arquivos do repositório. Em seguida, siga os passos abaixo para configurar e executar o backend:

1. Navegue até a pasta do backend:

```bash
   cd backend
```

2. Execute o projeto:

```bash
  ./gradlew bootRun
```

Certifique-se de ter o Java na versão 17 instalado em seu sistema.

### Frontend 

Clone ou baixe os arquivos do repositório. Em seguida, siga os passos abaixo para configurar e executar o frontend:

1. Navegue até a pasta do frontend:

```bash
   cd frontend
```

2. Instale as dependências:
   
```bash
  npm install
```

3. Execute o projeto:

```bash
  npm start
```

Certifique-se de ter o Angular na versão 17 instalado em seu sistema.



