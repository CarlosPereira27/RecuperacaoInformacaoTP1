# RecuperacaoInformacaoTP1
Trabalho Prático 1 da disciplina Recuperação de Informação (DCC-UFLA). 

Todos os comandos dessa aplicação dependem de que o _Solr Cloud_ esteja executando na máquina.
Para instalar o _Solr Cloud_ em sua máquina faça o download no site oficial https://lucene.apache.org/solr/ e siga o tutorial no _link_ https://lucene.apache.org/solr/guide/7_0/solrcloud.html.

Há 5 comandos disponíveis nesta aplicação:
* addConfig - este comando adiciona uma configuração definida pelo usuário na aplicação _Solr Cloud_ (argumentos _-zkhost_, _-zkp_, _-conf_, _-dirconf_)
* criarColecao - este comando cria uma coleção com as informações da coleção determinadas pelo usuário (argumentos _-c_, _-conf_, _-nshard_, _-nreplicas_)
* deletarColecao - este comando deleta uma coleção da aplicação _Solr Cloud_ (argumento _-c_)
* povoarColecao - este comando povoa uma coleção determinada pelo usuário com a base de dados CFC. O usuário deve ter no sua máquina a base CFC, e informar a endereço desta base de dados com o argumento _-bd_. Este projeto possui um diretório cfc com a base de dados CFC.
* consultasERelatorio - este comando realiza consultas em uma base de dados determinada pelo usuário (argumento _-c_). Esta base de dados deve estar povoada pela base CFC. As consultas realizadas estão definidas na base CFC, portanto o usuário deve informar o endereço desta base (argumento _-bd_). O relatório é gerado em um arquivo informado pelo usuário (argumento _-out_).

**IMPORTANTE** - todos os comandos, exceto _addConfig_, possuem os argumentos _-host_ e _-p_ que determinam o _host_ e a porta onde o _Solr Cloud_ está executando respectivamente. O comando _addConfig_ possui em vez dos argumentos _-host_ e _-p_, os argumentos _-zkhost_ e _-zkp_ que determinam o _host_ e a porta onde o _Solr Cloud ZooKeeper_ está executando respectivamente.

Os comandos disponíveis são:
```
java -jar recInfTP1.jar -cmd addConfig -zkhost <host_zoo_keeper> -zkp <porta_zoo_keepper> -conf <nome_configuracao> -dirconf <diretorio_configuracao>
```
```
java -jar recInfTP1.jar -cmd criarColecao -host <host> -p <porta> -c <colecao> -conf <nome_configuracao> -nshard <qtd_shards> -nreplicas <qtd_replicas>
```
```
java -jar recInfTP1.jar -cmd deletarColecao -host <host> -p <porta> -c <colecao>
```
```
java -jar recInfTP1.jar -cmd povoarColecao -host <host> -p <porta> -c <colecao> -bd <baseCFC> -preproc <pre-processamento>
```
```
java -jar recInfTP1.jar -cmd consultasERelatorio -host <host> -p <porta> -c <colecao> -bd <baseCFC> -out <arquivo> -preproc <pre-processamento>
```

Argumentos da aplicação:
* ```-cmd <arg>``` &rarr; comando a ser realizado (obrigatório), argumentos suportados: [criarColecao, povoarColecao, consultasERelatorio].
* ```-host <host>``` &rarr; define o _host_ da aplicação _Solr Cloud_. O padrão é localhost.
* ```-p <porta>``` &rarr; define a porta do _host_ da aplicação _Solr Cloud_. O padrão é 8983.
* ```-c <colecao>``` &rarr; define a coleção utilizada.
* ```-bd <baseCFC>``` &rarr; define o caminho para a base de dados CFC.
* ```-out <arquivo>``` &rarr; define o arquivo para salvar o relatório {consultasERelatorio}.
* ```-conf <configuracao>``` &rarr; define o nome da configuração utilizada na coleção {criarColecao}. O padrão é _default.
* ```-nshard <qtd_shards>``` &rarr; define a quantidade de shards da coleção {criarColecao}. O padrão é 2.
* ```-nreplicas <qtd_replicas>``` &rarr; define a quantidade de shards da coleção {criarColecao}. O padrão é 2.
* ```-dirconf <endereco_configuracao>``` &rarr; define o diretório de uma determinada configuração {addConf}
* ```-zkhost <host_zoo_keeper>``` &rarr;  define o _host_ da aplicação _Solr Cloud ZooKeeper_ {addConf}. O padrão é localhost.
* ```-zkp <porta_zoo_keepper>``` &rarr; define a porta do _host_ da aplicação _Solr Cloud ZooKeeper_ {addConf}. O padrão é 9983.
* ```-preproc <pre-processamento>``` &rarr; define se deverá utilizar o módulo de pré-processamento implementado neste projeto, valores possíveis: [nao, sim]. Em caso do valor for igual a sim usará o módulo de pré-processamento, caso contrário não. O padrão é sim.
* ```-h``` ou ```-help``` &rarr; mostra a mensagem de ajuda.

Para facilitar o uso dos argumentos da aplicação, defina os argumentos no arquivo de configuração.
O arquivo de configuração deve chamar 'config.prop', deve estar no mesmo diretório do executável, e deve seguir o padrão do arquivo de configuração exemplo.

**GRÁFICOS** O comando que gera consultas e relatório '_consultasERelatorio_' tentará usar a aplicação em python _gerarGraficos.py_ para gerar os gráficos do resultado do relatório. Alguns cenários que podem gerar erro na geração dos gráficos são: (i) não ter o python instalado na máquina; (ii) não ter as bibliotecas, utilizadas pela aplicação _gerarGraficos.py_, matplotlib e numpy devidamente instaladas; (iii) o arquivo _gerarGraficos.py_ não estar no mesmo diretório da aplicação principal em Java.

Em relação a aplicação _gerarGraficos.py_, ela gera os gráficos em dois formatos: png e svg. Para executar a aplicação _gerarGraficos.py_ de forma independente utilize o seguinte comando:
```
python gerarGraficos.py -r <resultado> -m <modo>
```
Argumentos da aplicação:
* ```-r <resultado>``` &rarr; caminho completo do arquivo de resultados do relatório, extensão _.csv_.
* ```-m <modo>``` &rarr; define o modo de geração de gráficos, há dois modos disponíveis: [simples, completo]. O modo simples apenas gera o gráfico da tabela média da métrica revocação/precisão. O modo completo também gera os gráficos de todas as consultas, os gráficos utilizando os pontos reais de revocação/precisão e também utilizando os 11 níveis da tabela. O padrão é o modo simples. 
* ```-h``` &rarr; mostra a mensagem de ajuda.
