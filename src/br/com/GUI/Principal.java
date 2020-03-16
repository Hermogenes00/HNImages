package br.com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JPanel;
import javax.swing.JSlider;



import br.com.Arquivos.Arquivo;



public class Principal extends JFrame {


	JLabel label_nome_imagem;
	JButton botao_abrir;
	String caminho;
	Centro c;



	public Principal() {
		super("HN Images");

		c = new Centro("C:\\Users\\ITAUTEC\\Pictures\\");
	};



	public void criarComponentes()
	{
		super.setLayout(new BorderLayout());



		super.getContentPane().add(BorderLayout.NORTH,new Topo());
		super.getContentPane().add(BorderLayout.SOUTH,new Rodape(c));
		super.getContentPane().add(BorderLayout.WEST,new PaletaCores());
		super.getContentPane().add(BorderLayout.CENTER,c);

		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(new Dimension(800,800));
		super.setLocationRelativeTo(null);
		super.setVisible(true);


	}

	private class PaletaCores extends JPanel{
		
		public PaletaCores() {
			super();
			
			super.setBackground(Color.blue);
		}
	}
	
	
	
	private class Topo extends JMenuBar
	{
		JMenu abrirMenu = new JMenu("Abrir");

		public Topo() {

			super();

			super.add(abrirMenu);

			abrirMenu.addMouseListener(new abrirMenuMouseListener());

			super.add(new JMenu("Salvar"));
			super.add(new JMenu("Exportar"));
			super.add(new JMenu("Configurar"));

		}

		class abrirMenuMouseListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser escolhaDiretorio = new JFileChooser("Selecionar diretório ou imagem");
				escolhaDiretorio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int escolha = escolhaDiretorio.showOpenDialog(null);

				if (escolha == JFileChooser.APPROVE_OPTION) {

					c.setDiretorio(escolhaDiretorio.getSelectedFile().getPath());
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}}
	}

	private static class Centro extends JPanel{

		public String caminhoImagem;
		Arquivo arq; 

		private static int contador = 0;

		public static int largura,altura = 0;

		private ArrayList<String> Imagens;

		public Centro(String diretorio) {

			arq = new Arquivo();

			Imagens = arq.ListaArquivos(diretorio);


		}

		public void setDiretorio(String diretorio) {

			Imagens = arq.ListaArquivos(diretorio);

		}

		public String getCaminhoImagem()
		{
			
			return Imagens.get(contador);
		}

		public void proximaImagem(){

			if (contador < Imagens.size()) {

				caminhoImagem = Imagens.get(contador);	

				if ((contador+1)<Imagens.size()) {
					contador++;

				}else {

					contador = 0;
				}


			}else {

				contador = 0;
			}

			System.out.println(Imagens.get(contador));
		}

		public void anteriorImagem(){

			if (contador > 1 && contador <= Imagens.size()) {

				contador--;
				caminhoImagem = Imagens.get(contador-1);

			}else {

				caminhoImagem = Imagens.get(contador);
				contador = Imagens.size();


			}
		}

		@Override
		public void paint(Graphics g) {

			super.paint(g);

			
			g.drawImage(new ImageIcon(caminhoImagem).getImage(),0,0,Centro.largura,Centro.altura,null);
		}


	}

	private class Rodape extends JMenuBar{

		Centro c;
		JSlider slider;
		public Rodape(Centro c)
		{
			super();

			this.c = c;

			super.setLayout(new FlowLayout());
			JMenu anteriorMenu = new JMenu("Anterior");
			JMenu proximoMenu = new JMenu("Próxima");

			slider = new JSlider();
			
			slider.setToolTipText("Zoom");
			
			slider.setSize(10,10);
			
			slider.setMinimum(1);
			slider.setMaximum(100);

			anteriorMenu.addMouseListener(new anteriorMenuMouseListener());
			proximoMenu.addMouseListener(new proximoMenuMouseListener());
			slider.addMouseListener(new sliderMenuMouseListener());

			super.add(anteriorMenu);
			super.add(proximoMenu);

			super.add(slider);


		}

		private class anteriorMenuMouseListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				c.anteriorImagem();
				c.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		}
		private class proximoMenuMouseListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				c.proximaImagem();
				c.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		}
		private class sliderMenuMouseListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				Centro.largura = (int) slider.getValue() * (c.getWidth()/100);
								
				Centro.altura =  (int) slider.getValue() * (c.getHeight()/100);

				System.out.println("Largura = "+Centro.largura + " Altura = "+Centro.altura);
				
				c.repaint();
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}



		}
	}



}
