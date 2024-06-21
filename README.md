# GERENCIADOR DE VIAGENS PRIVADAS JETSETJOURNEYS
![JETSETJOURNEYS](https://github.com/filipeats/JetSetJourneyskotlin/assets/121572315/babd6482-61c6-4ef8-998a-ffeacf4ba335)
## Descrição
Este projeto é um aplicativo Android desenvolvido em Kotlin. Ele foi projetado para gerenciar viagens privadas dos motoristas, permitindo-lhes adicionar novas viagens e acompanhar o total gasto hoje e no mês atual. A aplicação facilita o controle e a visualização das despesas relacionadas às viagens individuais, proporcionando uma experiência personalizada e intuitiva para os usuários gerenciarem seus registros de viagem de forma eficiente.
## Funcionalidades
1. **Tema Dinâmico**: O aplicativo suporta temas claro e escuro, adaptando-se ao tema do sistema através da função `isSystemInDarkTheme`.

2. **TopAppBar Customizada**: Uma barra superior personalizada (`TopAppBar`) exibe um logotipo centralizado usando um `Image` com o logo do aplicativo.

3. **Gerenciamento de Estado com ViewModel**: Utiliza o `ViewModel` do Android Architecture Components para gerenciar o estado da aplicação, mantendo uma lista de `Viagem` e os totais gastos hoje e no mês.

4. **Adição de Viagens**: Permite aos usuários adicionar novas viagens através de campos de texto (`OutlinedTextField`) para nome do passageiro, destino e valor da viagem. Ao adicionar uma viagem, uma mensagem animada é exibida por 2 segundos usando `AnimatedVisibility`.

5. **Exibição de Viagens**: As viagens adicionadas são exibidas em uma lista vertical (`LazyColumn`) usando `items`. Cada item de viagem é representado por `ViagemItem`, que mostra detalhes como nome do passageiro, destino, data e valor da viagem em um `Card`.

6. **Componentes Visuais Customizados**: Utiliza `Card` e `InfoCard` para exibir informações estruturadas de viagens e totais diários/mensais de forma estilizada.

7. **Animações**: Incorpora animações suaves (`fadeIn` e `fadeOut`) para a transição da mensagem de confirmação ao adicionar uma nova viagem.

8. **Pré-visualização no Compose**: Inclui uma função de pré-visualização (`@Preview`) para visualizar a interface do usuário em diferentes estados.
## Capturas de Tela

![image 1 (1)](https://github.com/filipeats/JetSetJourneyskotlin/assets/121572315/7e8321de-e3fe-45c6-bef7-8e800fffe5db)

![image 2 (1)](https://github.com/filipeats/JetSetJourneyskotlin/assets/121572315/455baaa6-4cf4-4cc7-b882-4afa31794022)

![imagem3 (1)](https://github.com/filipeats/JetSetJourneyskotlin/assets/121572315/d8a2e225-14c9-4bdc-ad22-3962981bb935)

![imagem 4  (1)](https://github.com/filipeats/JetSetJourneyskotlin/assets/121572315/ef96445b-cbdb-418e-bb15-c516291e5533)

## Como Inicializar

1. **Abrir o Aplicativo:**
   - Ao abrir o dispositivo móvel, localize o ícone do aplicativo "Viagens".
   - Toque no ícone para iniciar o aplicativo.

2. **Página Inicial do Aplicativo:**
   - Após o aplicativo ser carregado, você será direcionado automaticamente para a página inicial.
   - A página inicial exibirá uma barra superior com o título do aplicativo e o logotipo da empresa de viagens.

3. **Adicionar uma Viagem:**
   - Na página inicial, role para baixo até encontrar a seção onde você pode adicionar novas viagens.
   - Preencha os campos solicitados:
     - **Nome do Passageiro:** Digite o nome do passageiro que realizará a viagem.
     - **Destino:** Informe o destino da viagem.
     - **Valor da Viagem:** Insira o valor estimado da viagem.
   - Após preencher os campos, clique no botão "Adicionar Viagem".

4. **Visualização das Viagens Adicionadas:**
   - Após adicionar uma viagem, uma mensagem de confirmação será exibida temporariamente na tela, indicando que a viagem foi adicionada com sucesso.
   - Abaixo da mensagem de confirmação, você verá um resumo das viagens adicionadas hoje e no mês atual, incluindo o total gasto.

5. **Gerenciar Viagens:**
   - Para gerenciar as viagens adicionadas, role para baixo na página inicial.
   - Você verá a lista de viagens registradas.
   - Cada viagem listada mostrará detalhes como o nome do passageiro, destino, data da viagem e valor.
   - Você pode adicionar novas viagens, editar informações existentes ou remover viagens antigas conforme necessário.

6. **Acompanhar Totais:**
   - Para acompanhar os totais de gastos com viagens:
     - Na parte superior da página inicial, haverá uma seção destacada com informações como "Hoje" e "Este Mês".
     - Essas informações mostrarão o total gasto em viagens hoje e no mês atual.

7. **Navegação entre Telas:**
   - Utilize os botões de navegação ou gestos do dispositivo para acessar diferentes seções do aplicativo, como adicionar viagens, gerenciar viagens existentes ou visualizar totais de gastos.

8. **Finalizar Sessão:**
   - Para sair do aplicativo, utilize o botão padrão de voltar do dispositivo móvel ou navegue até a tela inicial do sistema operacional.
## Instalação

1. **Clone o Repositório:**
   Abra o terminal (ou Git Bash) e execute o seguinte comando para clonar o repositório:
   ```bash
   git clone https://github.com/filipeats/JetSetJourneyskotlin.git
   ```

2. **Abra no Android Studio:**
   - Abra o Android Studio.
   - No menu principal, clique em `File > Open`.
   - Navegue até o diretório onde você clonou o repositório `JetSetJourneyskotlin` e selecione-o.
   - Clique em `Open` para importar o projeto no Android Studio.

3. **Construa o Projeto:**
   - Após abrir o projeto no Android Studio, aguarde o Gradle sincronizar e indexar o projeto.
   - No menu do Android Studio, clique em `Build > Make Project` ou pressione `Ctrl + F9` para construir o projeto.

4. **Configure um Dispositivo Android ou Emulador:**
   - Conecte um dispositivo Android via USB ou utilize um emulador Android configurado e iniciado no Android Studio.

5. **Execute o Aplicativo:**
   - No Android Studio, clique em `Run > Run app` ou pressione `Shift + F10` para compilar e executar o aplicativo no dispositivo ou emulador conectado.
   - Após a compilação e instalação, o aplicativo será iniciado automaticamente no dispositivo ou emulador.
## Integrantes

- **Nome dos Desenvolvedores**:
- Fillipe Tiago de Almeida Abdala - RA: 22250885;
- Luiz Eduardo Amaro da Silva - RA: 22252539
- Ludimila lins de figueiredo Teles - RA: 22252262:
- Adria Vitória Ferreira da Silva - RA: 22251510:




