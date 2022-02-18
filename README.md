# Diagrama de classes
![Class Diagram0](https://user-images.githubusercontent.com/68262792/154746037-392d81a8-dc5c-4141-9703-5ff9a86a4a8c.png)

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/luismiguwl/api-filmes-spring-boot/maven)
# Sobre a API
API Spring Boot desenvolvida para exercitar meus conhecimentos utilizando os dados dos filmes que estou assistindo durante o ano.

No início do ano de 2021 decidi que listaria a quantidade de filmes assistidos em 2021. Fiz isso durante um mês e depois de algum tempo fazendo isso apenas por hobby, decidi utilizar esses dados numa API Spring para exercitar meus conhecimentos e me divertir enquanto produzo algo utilizando dados que eu mesmo coletei.

# Extração de dados
Os dados são extraídos de um arquivo CSV, para caso alguém queira baixar essa API para teste. Ao meu ver, fica mais fácil ler um arquivo CSV do que integrar com um banco de dados, porém entendo a importância de manter a extração de dados via banco de dados.

Vou atualizando a lista de filmes de acordo com que vou assistindo.

Esta é minha primeira API desenvolvida em Spring Boot que estou publicando no GitHub. Aos poucos fui refinando-a até achar que está suficientemente boa para ser compartilhada com mais pessoas. Mesmo após o refinamento, continuo trabalhando nela enquanto penso em ideias para melhorá-la.

# Por que o Spring Boot?
Decidi utilizar o Spring Boot como framework para criação da minha API por ser um pouco mais familiarizado com o Java. Outro motivo é a enorme quantidade de recursos que o framework possui. Sério, eu fico assustado quando olho a documentação e percebo o quão longe posso ir utilizando APENAS o Spring Boot. Sem contar na quantidade de outros recursos que uma Spring REST pode conter.

Outro motivo que explica o porquê de eu ter escolhido o Spring Boot é a minha vontade de me especializar em algum framework Java. A linguagem me atraiu bastante desde a primeira vez que tive contato com ela.

# O que foi aprendido
Esta seção será atualizada de acordo com o desenvolvimento da aplicação

Ao iniciar o projeto, eu já tinha plenos conhecimentos sobre a maioria dos recursos que foram utilizados na aplicação. Entretanto, aprendi a utilizar algumas outras ferramentas que eu não fazia ideia de que existiam, como o Jackson e o Lombok. Este último foi um catalisador no que diz respeito ao famoso "Clean Code". Ao ler num fórum sobre os benefícios da utilização do Lombok, decidi ir atrás e verificar do que se tratava. Descobri que é uma ferramenta que funciona à base de anotações, que no momento em que li sobre, soube que precisava adicionar ao meu projeto.

# Uso de Stream API

Se você assim como eu já leu o livro Clean Code do Robert C. Martin, creio que esta seção irá lhe agradar.

Resolvi dedicar uma seção apenas para falar de Stream pois ao notar que me utilizei deste recurso em praticamente todas as situações onde houve necessidade de implementação de loops, julguei apropriado descrever um pouco da minha experiência.

Além do Lombok e do Jackson, uma outra ferramenta muito útil no que diz respeito ao Clean Code, é a Stream API, fornecida pelo próprio Java. Ao dar uma visão mais detalhada ao projeto, nota-se que o uso de Stream é muito frequente, e isso prova o quão útil é este recurso da linguagem. Usei pipelines de execução em praticamente TODAS as minhas funções. Eu fico me perguntando o quão grande ficaria o código se eu tivesse que desenvolver os algoritmos utilizando for, if/else etc. O uso de loops implícitos facilita bastante na hora de desenvolver uma solução em que eu precisaria de várias linhas de código para criar como era outrora.
