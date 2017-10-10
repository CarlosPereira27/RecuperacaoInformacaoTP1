# RecuperacaoInformacaoTP1
Trabalho Prático 1 da disciplina Recuperação de Informação (DCC-UFLA). 

Todos os comandos dessa aplicação dependem de que o Solr Cloud esteja executando na máquina.
Para instalar o Solr Cloud em sua máquina faça o download no site oficial https://lucene.apache.org/solr/ e siga o tutorial no link https://lucene.apache.org/solr/guide/7_0/solrcloud.html.

Há 3 comandos disponíveis nesta aplicação:
	criarColecao - este comando cria uma coleção com as informações da coleção determinadas pelo usuário (argumentos -c, -conf, -nshard, -nreplicas)
	povoarColecao - este comando povoa uma coleção determinada pelo usuário com a base de dados CFC. O usuário deve ter no sua máquina a base CFC, e informar a endereço desta base de dados com o argumento -bd. Este projeto possui um diretório cfc com a base de dados CFC.
	consultasERelatorio - este comando realiza consultas em uma base de dados determinada pelo usuário (argumento -c). Esta base de dados deve estar povoada pela base CFC. As consultas realizadas estão definidas na base CFC, portanto o usuário deve informar o endereço desta base (argumento -bd). O relatório é gerado em um arquivo informado pelo usuário (argumento -out).


IMPORTANTE - todos os comandos possuem os argumentos -host e -p que determinam o host onde o Solr Cloud está executando e a porta em que está rodando respectivamente.



Os comandos disponíveis são:
java -jar recInfTP1.jar -cmd criarColecao -host <host> -p <porta> -c <colecao> -conf <configuracao> -nshard <qtd_shards> -nreplicas <qtd_replicas>
java -jar recInfTP1.jar -cmd povoarColecao -host <host> -p <porta> -c <colecao> -bd <baseCFC>
java -jar recInfTP1.jar -cmd consultasERelatorio -host <host> -p <porta> -c <colecao> -bd <baseCFC> -out <arquivo>

Parâmetros do programa:
-cmd <arg> -> comando a ser realizado (obrigatório), argumentos suportados: [criarColecao, povoarColecao, consultasERelatorio]
-host <host> -> define o host da aplicação Solr, o padrão é localhost
-p <porta> -> define a porta do host da aplicação Solr,o padrão é 8983
-c <colecao> -> define a coleção utilizada
-bd <baseCFC> -> define o caminho para a base de dados CFC
-out <arquivo> -> define o arquivo para salvar o relatório {consultasERelatorio}
-conf <configuracao> -> define o nome da configuração utilizada na coleção {criarColecao}, o padrão é _default
-nshard <qtd_shards> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2
-nreplicas <qtd_replicas> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2
-h ou -help (mostra a mensagem de ajuda)

Para facilitar o uso dos parâmetros no programa, defina os parâmetros no arquivo de configuração.
O arquivo de configuração deve chamar 'config.prop', e deve seguir o padrão do arquivo de configuração exemplo.
