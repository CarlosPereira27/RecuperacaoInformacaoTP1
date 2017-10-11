# -*- coding: utf-8 -*-
#!/usr/bin/python

from __future__ import unicode_literals
import sys, getopt
import os
import matplotlib.pyplot as plt
import numpy as np

"""
Recupera a próxima lista de pontos do arquivo, para quando encontrar uma linha em branco.

@param arquivo
			arquivo que recuperará os pontos
@return
			lista de pontos recuperada do arquivo
"""
def proximosPontos(arquivo):
	revocacao = [];
	precisao = [];
	linha = arquivo.readline()[:-1];
	while linha:
		tokens = linha.split(",");
		revocacao.append(float(tokens[0]));
		precisao.append(float(tokens[1]));
		linha = arquivo.readline()[:-1];
	
	return (revocacao, precisao);

"""
Pula n linhas do arquivo.

@param arquivo 
			arquivo que pulará as linhas
@param n 
			quantidade de linhas para pular
"""
def pularLinhas(arquivo, n):
	for i in range(n):
		arquivo.readline();

"""
Plota um gráfico de revocação por precisão.

@param arquivoNome 
			caminho e possível prefixo do nome do arquivo que salvará o gráfico
@param titulo
			titulo do gráfico
@param sufixoArquivo
			sufixo do nome do arquivo  que salvará o gráfico
@param pontos
			pontos utilizados para plotar o gráfico
@param dirSep
			determina se deverá utilizar diretórios diferentes para salvar os 
			gráficos em png e svg, se o parâmetro contêm o valor True deverá
			usar dois diretórios, caso contrário não
"""
def plotGrafico(arquivoNome, titulo, sufixoArquivo, pontos, dirSep):
	plt.plot(pontos[0], pontos[1], 'bo-', lw = 3, mew = 7);
	plt.title(titulo)
	plt.xlim([0,100]);
	plt.ylim([0,100]);
	plt.xlabel("Revocação")
	plt.ylabel('Precisão')
	grafico = arquivoNome + sufixoArquivo;
	if dirSep:
		plt.savefig(arquivoNome + "png/" + sufixoArquivo + ".png", dpi=300);
		plt.savefig(arquivoNome + "svg/" + sufixoArquivo + ".svg", dpi=300);
	else:
		plt.savefig(grafico + ".png", dpi=300);
		plt.savefig(grafico + ".svg", dpi=300);
	plt.gcf().clear();

"""
Cria um diretório, caso ele não exista.

@param diretorioStr
			diretório a ser criado
"""
def criarDiretorio(diretorioStr):
	diretorio = os.path.dirname(diretorioStr)
	if not os.path.exists(diretorio):
		os.makedirs(diretorio);

"""
Gera os gráficos relacionados aos resultados encontrados no programa Java.

@param argv 
			como argumento, deve receber o nome do arquivo de resultados e o modo de geração dos gráficos (opcional).
			O modo de geração dos gráficos pode ser: simples ou completo. O simples, que é o padrão, apenas gera o
			gráfico da tabela média de revocação/precisão. Já o completo gera gráficos para todas as consultas.  
"""
def main(argv):
	arqResultadosStr = ''
	modo = 'simples'
	try:
		opts, args = getopt.getopt(argv,"hr:m:",["resultado=", "modo="])
	except getopt.GetoptError:
		print getopt.GetoptError.message
		print 'python gerarGraficos.py -r <resultados.csv> -m <modo>'
		sys.exit(2)
	for opt, arg in opts:
		if opt == '-h':
			print 'python gerarGraficos.py -r <resultados.csv> -m <modo>'
			sys.exit()
		elif opt in ("-r", "--resultado"):
			arqResultadosStr = arg
		elif opt in ("-m", "--modo"):
			modo = arg
	arquivo = open(arqResultadosStr, 'r');
	pularLinhas(arquivo, 2);
	pontos = proximosPontos(arquivo);
	plotGrafico(arqResultadosStr[:-4], "Gráfico Revocação/Precisão Médio" , "_GraficoRevPrecMed", pontos, False);
	
	
	if modo != "completo":
		arquivo.close();
		sys.exit(0);
	"""
	Comentar as duas linhas acimas para gerar todos os gráficos.
	"""

	diretorioStr = arqResultadosStr[:-4] + "Consultas/";
	criarDiretorio(diretorioStr);
	criarDiretorio(diretorioStr + "png/");
	criarDiretorio(diretorioStr + "svg/");

	MAX_CONSULTAS = 100;
	for idConsulta in range(1, MAX_CONSULTAS + 1):
		pularLinhas(arquivo, 6);
		pontos = proximosPontos(arquivo);
		idConsultaStr = str(idConsulta);
		titulo = "Gráfico Revocação/Precisão Consulta " + idConsultaStr + " - Pontos";
		grafico = "grafRevPrec_Cons"+ idConsultaStr + "_Pontos";
		plotGrafico(diretorioStr, titulo, grafico, pontos, True);

		pularLinhas(arquivo, 2);
		pontos = proximosPontos(arquivo);
		titulo = "Gráfico Revocação/Precisão Consulta " + idConsultaStr + " - 11 níveis";
		grafico = "grafRevPrec_Cons"+ idConsultaStr + "_11Niveis";
		plotGrafico(diretorioStr, titulo, grafico, pontos, True)


	print("Os gráficos foram gerados com sucesso!")
	arquivo.close();



if __name__ == "__main__":
	main(sys.argv[1:])
