package br.com.Arquivos;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.lang.*;


public class Arquivo {

	public Arquivo()
	{

	}

	public static void main(String[] args) {
		var a  = new Arquivo().ListaArquivos("c://");

		System.out.println(a.toString());
	}

	public ArrayList<String> ListaArquivos(String caminho) {

		File file;

		ArrayList<String> arquivos = new ArrayList<String>();

		file = new File(caminho);

		File[] listagem = file.listFiles();

		if (file.isFile()) {
			
			arquivos.add(file.getPath());
		}else {
			for (File arquivo : listagem) {

				if (arquivo.isFile()) {
					
					arquivos.add(arquivo.getPath());
					
				}

			}
		}
		

		return arquivos;


	}
}
