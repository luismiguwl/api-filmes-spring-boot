# Diagrama de classes
Representação do modelo conceitual da aplicação. Desenvolvi no Astah UML, caso esteja se perguntando.

![Class Diagram0](https://user-images.githubusercontent.com/68262792/154746037-392d81a8-dc5c-4141-9703-5ff9a86a4a8c.png)

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/luismiguwl/api-filmes-spring-boot/maven)
# Sobre a API (senta que lá vem história)
Aqui eu conto a história e motivação pro desenvolvimento do projeto. Se cê tá interessado(a) na parte técnica, desce um pouquinho pra seção "Beleza, mas e a parte técnica?".

O objetivo desse projeto é basicamente expor uma API contendo dados sobre os filmes que eu assisti desde 2021, além de ser uma ótima maneira de melhorar meu código Java, unindo o útil ao agradável. No ano de 2021, decidi que iria salvar os dados dos filmes para realizar uma análise das estatísticas posteriormente, usando Power BI ou o Jupyter. Nessa mesma época eu já tinha bastante familiaridade com o Spring e estava procurando projetos para desenvolver, quando o YouTube me recomendou um vídeo gringo de um rapaz simulando a arquitetura da API do IMDb. Após ver o vídeo - muito interessante, aliás -, resolvi que iria usar os dados que eu estava coletando e expor numa API. Então eu criei o repositório e desde então venho implementando as funcionalidades de acordo com minha necessidade.

No começo era um projetinho pessoal que serviria apenas para 2021, porém 2022 chegou e eu decidi manter o projeto pois eu tenho um apreço bem grande por ele. Desde o começo do projeto eu já tinha aplicado os princípios SOLID então já sabia dos benefícios, portanto apliquei no projeto. Uma vez que você entende e aplica o SOLID, você não quer programar de outro jeito. O uso do SOLID no projeto ficou claro quando eu precisei adicionar os filmes vistos em 2022, também, porque essa mudança foi feita simplesmente adicionando mais um enum contendo o destino do arquivo dos novos dados.

Bom, você acabou de ler a história do projeto. Espero que tenha gostado :)

# Extração de dados
Como dito na seção "Sobre a API (senta que lá vem história)", eu salvei os dados dos filmes para uso posterior, portanto precisei decidir que tipo de arquivo seria: txt, xlsx, SQL? Não, CSV!

Sempre gostei de usar arquivos CSV devido ao dinamismo. É praticamente igual a uma tabela do Excel (ou LibreOffice pra tu que gostas de código aberto) na questão de colunas e linhas. Por ser um arquivo de texto plano, ele pode ser lido em qualquer editor de código. Isso significa que tu podes ler o arquivo usando o comando "cat" do Linux ou tanto num nano da vida, quanto num VSCode ou app de planilha. Escolhi o CSV para armazenar os dados por ser bem simples de manipular esses arquivos e pra facilitar a vida de quem for clonar o repositório um dia. Coincidentemente, na maioria das vezes essa pessoa sou eu :)

# Por que o Spring Boot?
Mas por quê, dentre tantos frameworks (para JavaScript, principalmente), eu escolhi logo o Spring? A resposta é simples: é o framework mais utilizado na linguagem Java. Sendo assim, a chance de cê ter uma dúvida que outra pessoa já teve é muito grande, e isso facilita muito a vida do desenvolvedor.

Outro motivo pra eu ter escolhido o Spring tem motivação profissional. Vou me especializar em Java, portanto esse projeto, além de ser um bom exercício pra obter experiência com aplicações do framework, é uma ótima maneira pra eu desenvolver minha proficiência na linguagem. Não sou evangelista Java, também desenvolvo em outras linguagens como Python, JavaScript e C, mas, no momento em que escrevo esse trecho, meu foco é em Java.

# Beleza, mas e a parte técnica?
Se você leu até aqui ou pulou pra cá, seja bem-vindo. Começando pela API do Java, gostaria de começar listando alguns recursos da linguagem que foram usados e que me ajudaram a não escrever uma montanha de código.

```
Arrays
Generics
Stream API
Collections
Functional Interfaces
Jackson para tratamentos de JSON
```
## Generics
O conceito de tipo genérico não é novidade no Java. Ao contrário do nome, você utiliza o generics pra especializar uma classe, e não generalizar. Basicamente tu colocas um <T> na classe e pronto, agora ela é genérica. O T significa a classe que vai ser usada. Foi muito útil pra criar interfaces para controllers.

## Stream API
De longe foi um dos principais recursos utilizados no projeto inteiro. Utilizei streams para iterar sobre listas e realizar filtros usando predicate. Usei menos de 5 linhas de código na maioria. Imagina realizar os mesmos filtros usando for convencional, if/else, throw e tudo o mais?! Credo!!

## Functional Interfaces
Tenho afinidade com esse recurso desde a primeira vez que tive contato. Basicamente é uma forma de ter programação funcional no Java. Assim como no JavaScript, é comum usar arrow functions pra escrever uma implementação de um método. Nas interfaces funcionais temos o mesmo conceito. Considereo exemplo de um Predicate, interface funcional que recebe um valor genérico (usando generics) e fazer uma asserção booleana:

```
Predicate<String> containsComma = text -> text.contains(",");
```

Agora, vamos ao exemplo de uma Function, que recebe um tipo e devolve outro valor:
```
Function<Director, String> getDirectorName = director -> {
	return director.getName();
}
```

## Jackson
Não tenho muito a dizer sobre o Jackson pois usei pouco, mas o pouco que eu usei já me ajudou bastante.

Segue uma anotação que é usada pra remover valores vazios ou nulos do JSON:
```
@JsonInclude(Include.NON_EMPTY)
public class FilmeVisto {
	private String titulo = "Tenet";
	private String classificacaoIndicativa = null;

	// construtores, getters e setters
}
```

JSON sem JsonInclude
```
{
	"titulo": "Tenet",
	"classificacaoIndicativa": null
}
```

JSON com JsonInclude
```
{
	"titulo": "Tenet"
}
```

Em alguns casos também usei o JsonIgnore, anotação que serve pra ignorar um campo no JSON.