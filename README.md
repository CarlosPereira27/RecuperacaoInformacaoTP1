# RecuperacaoInformacaoTP1
Trabalho Prático 1 da disciplina Recuperação de Informação (DCC-UFLA). 

Todos os comandos dessa aplicação dependem de que o Solr Cloud esteja executando na máquina.
Para instalar o Solr Cloud em sua máquina faça o download no site oficial https://lucene.apache.org/solr/ e siga o tutorial no link https://lucene.apache.org/solr/guide/7_0/solrcloud.html.

Há 3 comandos disponíveis nesta aplicação:
* criarColecao - este comando cria uma coleção com as informações da coleção determinadas pelo usuário (argumentos -c, -conf, -nshard, -nreplicas)
* povoarColecao - este comando povoa uma coleção determinada pelo usuário com a base de dados CFC. O usuário deve ter no sua máquina a base CFC, e informar a endereço desta base de dados com o argumento -bd. Este projeto possui um diretório cfc com a base de dados CFC.
* consultasERelatorio - este comando realiza consultas em uma base de dados determinada pelo usuário (argumento -c). Esta base de dados deve estar povoada pela base CFC. As consultas realizadas estão definidas na base CFC, portanto o usuário deve informar o endereço desta base (argumento -bd). O relatório é gerado em um arquivo informado pelo usuário (argumento -out).

**IMPORTANTE** - todos os comandos possuem os argumentos -host e -p que determinam o host onde o Solr Cloud está executando e a porta em que está rodando respectivamente.

Os comandos disponíveis são:
* java -jar recInfTP1.jar -cmd criarColecao -host &lt;host&gt; -p &lt;porta&gt; -c &lt;colecao&gt; -conf &lt;configuracao&gt; -nshard &lt;qtd_shards&gt; -nreplicas &lt;qtd_replicas&gt;
* java -jar recInfTP1.jar -cmd povoarColecao -host &lt;host&gt; -p &lt;porta&gt; -c &lt;colecao&gt; -bd &lt;baseCFC&gt;
* java -jar recInfTP1.jar -cmd consultasERelatorio -host &lt;host&gt; -p &lt;porta&gt; -c &lt;colecao&gt; -bd &lt;baseCFC&gt; -out &lt;arquivo&gt;

Parâmetros do programa:
* -cmd &lt;arg&gt; &rarr; comando a ser realizado (obrigatório), argumentos suportados: [criarColecao, povoarColecao, consultasERelatorio]
* -host &lt;host&gt; &rarr; define o host da aplicação Solr, o padrão é localhost
* -p &lt;porta&gt; &rarr; define a porta do host da aplicação Solr,o padrão é 8983
* -c &lt;colecao&gt; &rarr; define a coleção utilizada
* -bd &lt;baseCFC&gt; &rarr; define o caminho para a base de dados CFC
* -out &lt;arquivo&gt; &rarr; define o arquivo para salvar o relatório {consultasERelatorio}
* -conf &lt;configuracao&gt; &rarr; define o nome da configuração utilizada na coleção {criarColecao}, o padrão é _default
* -nshard &lt;qtd_shards&gt; &rarr; define a quantidade de shards da coleção {criarColecao}, o padrão é 2
* -nreplicas &lt;qtd_replicas&gt; &rarr; define a quantidade de shards da coleção {criarColecao}, o padrão é 2
* -h ou -help (mostra a mensagem de ajuda)

Para facilitar o uso dos parâmetros no programa, defina os parâmetros no arquivo de configuração.
O arquivo de configuração deve chamar 'config.prop', e deve seguir o padrão do arquivo de configuração exemplo.
