# -*- coding: utf-8 -*-
#!/usr/bin/python

from __future__ import unicode_literals
import sys, getopt
import os
import matplotlib.pyplot as plt
import matplotlib
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
	while linha and linha != ",":
		tokens = linha.split(",");
		if tokens[0] == "Tabela" or tokens[0] == "Total rel":
			break;
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
"""
def plotGrafico(arquivoNome, titulo, sufixoArquivo, pontos):
	plt.plot(pontos[0], pontos[1], 'bo-', lw = 3, mew = 7);
	plt.xlim([0,100]);
	plt.ylim([0,100]);
	plt.xlabel("Revocação");
	plt.ylabel('Precisão');
	plt.savefig(os.path.join(arquivoNome + "png", sufixoArquivo + ".png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", sufixoArquivo + ".svg"), dpi=300);
	plt.title(titulo);
	plt.savefig(os.path.join(arquivoNome + "png", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.gcf().clear();

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
"""
def plotGraficoTabelas(arquivoNome, titulo, sufixoArquivo, tabelaGeral, tabelas):
	for tabela in tabelas:
		plt.plot(tabela[0], tabela[1], 'bo-', lw = 1, mew = 1.5);
	line1, = plt.plot(tabelaGeral[0], tabelaGeral[1], 'ro-', lw = 3, mew = 7, label="Média");
	plt.xlim([0,100]);
	plt.ylim([0,100]);
	plt.xlabel("Revocação");
	plt.ylabel('Precisão');
	plt.legend(handles=[line1]);
	plt.savefig(os.path.join(arquivoNome + "png", sufixoArquivo + ".png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", sufixoArquivo + ".svg"), dpi=300);
	plt.title(titulo);
	plt.savefig(os.path.join(arquivoNome + "png", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.gcf().clear();

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
"""
def plotGrafico2(arquivoNome, titulo, sufixoArquivo, pontos, leg, pontos2, leg2):
	line1, = plt.plot(pontos[0], pontos[1], 'bo-', lw = 3, mew = 7, label=leg);
	line2, = plt.plot(pontos2[0], pontos2[1], 'ro-', lw = 3, mew = 7, label=leg2);
	plt.xlim([0,100]);
	plt.ylim([0,100]);
	plt.xlabel("Revocação");
	plt.ylabel('Precisão');
	plt.legend(handles=[line1, line2]);
	plt.savefig(os.path.join(arquivoNome + "png", sufixoArquivo + ".png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", sufixoArquivo + ".svg"), dpi=300);
	plt.title(titulo);
	plt.savefig(os.path.join(arquivoNome + "png", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.gcf().clear();

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
"""
def plotGrafico3(arquivoNome, titulo, sufixoArquivo, pontos, leg, pontos2, leg2, pontos3, leg3):
	line1, = plt.plot(pontos[0], pontos[1], 'bo-', lw = 3, mew = 7, label=leg);
	line2, = plt.plot(pontos2[0], pontos2[1], 'ro-', lw = 3, mew = 7, label=leg2);
	line3, = plt.plot(pontos3[0], pontos3[1], 'yo-', lw = 3, mew = 7, label=leg3);
	plt.xlim([0,100]);
	plt.ylim([0,100]);
	plt.xlabel("Revocação");
	plt.ylabel('Precisão');
	plt.legend(handles=[line1, line2, line3]);
	plt.savefig(os.path.join(arquivoNome + "png", sufixoArquivo + ".png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", sufixoArquivo + ".svg"), dpi=300);
	plt.title(titulo);
	plt.savefig(os.path.join(arquivoNome + "png", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
	plt.savefig(os.path.join(arquivoNome + "svg", "titulo", sufixoArquivo + "_ctitulo.png"), dpi=300);
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
			Por fim deve receber o título a ser dado aos gráficos gerados.
"""
def main(argv):
	arqResultadosStr = ''
	tituloGraf = ''
	arqResultadosStr2 = ''
	tituloGraf2 = ''
	arqResultadosStr3 = ''
	tituloGraf3 = ''
	tituloGrafGeral = ''
	arquivoSaida = ''
	modo = 'simples'
	try:
		opts, args = getopt.getopt(argv,"hr:m:t:s:u:i:v:g:o:",["resultado=", "modo=", "tituloGrafico=", "resultado2=", "tituloGrafico2=", "resultado3=", "tituloGrafico3=", "titulo_geral=", "arquivo_saida="])
	except getopt.GetoptError:
		print (getopt.GetoptError.message)
		print ('python gerarGraficos.py -r <resultados.csv> -m <modo> -t <titulo_grafico>')
		sys.exit(2)
	for opt, arg in opts:
		if opt == '-h':
			print ('python gerarGraficos.py -r <resultados.csv> -m <modo> -t <titulo_grafico>')
			sys.exit()
		elif opt in ("-r", "--resultado"):
			arqResultadosStr = arg
		elif opt in ("-m", "--modo"):
			modo = arg
		elif opt in ("-t", "--tituloGrafico"):
			tituloGraf = arg
		elif opt in ("-s", "--resultado2"):
			arqResultadosStr2 = arg
		elif opt in ("-u", "--tituloGrafico2"):
			tituloGraf2 = arg
		elif opt in ("-i", "--resultado3"):
			arqResultadosStr3 = arg
		elif opt in ("-v", "--tituloGrafico3"):
			tituloGraf3 = arg
		elif opt in ("-g", "--titulo_geral"):
			tituloGrafGeral = arg
		elif opt in ("-o", "--arquivo_saida"):
			arquivoSaida = arg
	index = arqResultadosStr.rfind(os.sep);
	diretorioStr = arqResultadosStr[:index+1];
	arqNome = arqResultadosStr[index+1:-4]

	criarDiretorio(diretorioStr);
	criarDiretorio(diretorioStr + "png" + os.sep);
	criarDiretorio(diretorioStr + "svg" + os.sep);
	criarDiretorio(os.path.join(diretorioStr + "png", "titulo") + os.sep);
	criarDiretorio(os.path.join(diretorioStr + "svg", "titulo") + os.sep);
	if arquivoSaida != '':
		index = arquivoSaida.rfind(os.sep);
		diretorioStr = arquivoSaida[:index+1];
		arqNome = arquivoSaida[index+1:]
		criarDiretorio(diretorioStr);
		criarDiretorio(diretorioStr + "png" + os.sep);
		criarDiretorio(diretorioStr + "svg" + os.sep);
		criarDiretorio(os.path.join(diretorioStr + "png", "titulo") + os.sep);
		criarDiretorio(os.path.join(diretorioStr + "svg", "titulo") + os.sep);

	arquivo = open(arqResultadosStr, 'r');
	pularLinhas(arquivo, 3);
	pontos = proximosPontos(arquivo);
	if arqResultadosStr2 != '':
		arquivo2 = open(arqResultadosStr2, 'r');
		pularLinhas(arquivo2, 3);
		pontos2 = proximosPontos(arquivo2);
	if arqResultadosStr3 != '':
		arquivo3 = open(arqResultadosStr3, 'r');
		pularLinhas(arquivo3, 3);
		pontos3 = proximosPontos(arquivo3);


	if arqResultadosStr2 == '' and arqResultadosStr3 == '':
		plotGrafico(diretorioStr, tituloGraf, arqNome, pontos);
	elif arqResultadosStr3 == '':
		plotGrafico2(diretorioStr, tituloGrafGeral, arqNome, pontos, tituloGraf, pontos2, tituloGraf2);
	else:
		plotGrafico3(diretorioStr, tituloGrafGeral, arqNome, pontos, tituloGraf, pontos2, tituloGraf2, pontos3, tituloGraf3);
	
	if modo != "completo":
		arquivo.close();
		if arqResultadosStr2 != '':
			arquivo2.close();
		if arqResultadosStr3 != '':
			arquivo3.close();
		sys.exit(0);

	diretorioStr = arqResultadosStr[:-4] + "_cons" + os.sep;
	criarDiretorio(diretorioStr);
	criarDiretorio(diretorioStr + "png" + os.sep);
	criarDiretorio(diretorioStr + "svg" + os.sep);
	criarDiretorio(os.path.join(diretorioStr + "png", "titulo") + os.sep);
	criarDiretorio(os.path.join(diretorioStr + "svg", "titulo") + os.sep);

	MAX_CONSULTAS = 100;
	pularLinhas(arquivo, 3);
	tabelaGeral = pontos;
	tabelas = []
	for idConsulta in range(1, MAX_CONSULTAS + 1):
		pularLinhas(arquivo, 9);
		pontos = proximosPontos(arquivo);
		idConsultaStr = str(idConsulta);
		titulo = tituloGraf + " - Cons " + idConsultaStr + " - Pontos";
		grafico = arqNome + "_cons"+ idConsultaStr + "_pontos";
		plotGrafico(diretorioStr, titulo, grafico, pontos);

		pularLinhas(arquivo, 2);
		pontos = proximosPontos(arquivo);
		titulo = tituloGraf + "- Cons " + idConsultaStr + " - 11 níveis";
		grafico = arqNome + "_cons"+ idConsultaStr + "_tabela";
		plotGrafico(diretorioStr, titulo, grafico, pontos);
		tabelas.append(pontos);

	index = arqResultadosStr.rfind(os.sep);
	diretorioStr = arqResultadosStr[:index+1];
	arqNome = arqResultadosStr[index+1:-4];
	plotGraficoTabelas(diretorioStr, tituloGraf + " - tabelas", arqNome + "_tabelas", tabelaGeral, tabelas);
	print("Os gráficos foram gerados com sucesso!");
	arquivo.close();



if __name__ == "__main__":
	main(sys.argv[1:])
