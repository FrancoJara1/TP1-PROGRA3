package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import service.Tablero;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FrameImagenes {
	private JFrame frame;
	private FrameMenu menu;
	private static JTextField cantMovs;
	private BufferedImage[] buffer;
	private String nombre;
	private Hashtable< String, BufferedImage> Imagenes;
	private int contAyuda;
	private JTextField cantAyuda;

	public FrameImagenes(String n) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nombre=n;
		initialize();
	}

	private void initialize() {
		JFrame frame = new JFrame("Grid "+4+"x"+4+" y Direcciones de Flechas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 592, 401);
		frame.setSize(700, 600);
		frame.getContentPane().setLayout(null);
		Imagenes=new Hashtable();
		imagenes();
		Tablero tablero=new Tablero(4);

		// Crear el panel con el GridLayout de 4x4 para los botones
		JPanel gridPanel = new JPanel();
		gridPanel.setBounds(5, 23, 512, 512);
		gridPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		gridPanel.setLayout(new GridLayout(4,4 ));
		frame.getContentPane().add(gridPanel);
		//BOTON PARA VOLVER AL MENU///////////////////////////////////
		JButton botonVolver = new JButton("Volver al Menú");
		botonVolver.setBounds(528, 0, 111, 23);
		frame.getContentPane().add(botonVolver);
		frame.setVisible(true);
		botonVolver.setForeground(Color.RED);
		botonVolver.setBackground(Color.YELLOW);
		botonVolver.setFocusable(false);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor = JOptionPane.showConfirmDialog(frame, "¿Esta seguro de volver al menù?\n"
						+ "Perderas el progreso de la partida","Advertencia", JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.YES_OPTION) {
					menu=new FrameMenu();
					frame.setVisible(false);
				}
			}
		});
		////CANTIDAD DE MOVIMIENTOS///////////////////////////
		JLabel cantidad = new JLabel("Movimientos: ");
		cantidad.setFont(new Font("Eras Medium ITC", Font.BOLD, 12));
		cantidad.setBounds(520, 393, 80, 14);
		frame.getContentPane().add(cantidad);
		cantidad.setVisible(true);
		
		cantMovs = new JTextField("");
		cantMovs.setForeground(Color.RED);
		cantMovs.setEditable(false);
		cantMovs.setBounds(599, 390, 44, 20);
		frame.getContentPane().add(cantMovs);
		cantMovs.setColumns(10);

		//CANTIDAD DE AYUDAS
		cantAyuda = new JTextField("");
		cantAyuda.setForeground(Color.RED);
		cantAyuda.setEditable(false);
		cantAyuda.setBounds(630, 445, 44, 20);
		frame.getContentPane().add(cantAyuda);
		cantAyuda.setColumns(10);

		JButton[][] botones = new JButton[4][4];
		// Crear e insertar los botones en la matriz 4x4
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				botones[i][j] = new JButton("" + (i * 4 + j + 1));
				gridPanel.add(botones[i][j]);
				Border borde= BorderFactory.createLineBorder(Color.RED);
				botones[i][j].setBorder(borde);
			}
		}
		frame.getContentPane().setLayout(null);
		
		//CREACION DE BOTONES EN FRAME///////////////
		JToggleButton teclaArriba = crearBotonMovimiento("\u2191",tablero ,botones,gridPanel,566, 210);
		frame.getContentPane().add(teclaArriba);
		teclaArriba.setFocusable(false);
		teclaArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(0, -1);
				if(confirmarVictoria(tablero)) frame.setVisible(false);
			}
		});
		JToggleButton teclaIzquierda = crearBotonMovimiento("\u2190",tablero,botones,gridPanel, 515, 255);
		frame.getContentPane().add(teclaIzquierda);
		teclaIzquierda.setFocusable(false);
		teclaIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(-1, 0);
				if(confirmarVictoria(tablero)) frame.setVisible(false);	
			}
		});
		JToggleButton teclaDerecha = crearBotonMovimiento("\u2192",tablero,botones,gridPanel, 614, 255);
		frame.getContentPane().add(teclaDerecha);
		teclaDerecha.setFocusable(false);
		teclaDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(1, 0);
				if(confirmarVictoria(tablero)) frame.setVisible(false);		
			}
		});
		JToggleButton teclaAbajo = crearBotonMovimiento("\u2193",tablero,botones,gridPanel, 566, 300);
		frame.getContentPane().add(teclaAbajo);
		teclaAbajo.setFocusable(false);
		teclaAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(0, 1);
				if(confirmarVictoria(tablero))frame.setVisible(false);
			}
		});		
		/////OBJETIVO///
		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblObjetivo.setBounds(528, 60, 61, 14);
		frame.getContentPane().add(lblObjetivo);
		/////MINI IMAGEN PARA OBJETIVO////////////////////////////
		JLabel lblMiniImagen = new JLabel("");
		lblMiniImagen.setBounds(538, 85, 100, 99);
		frame.getContentPane().add(lblMiniImagen);
		lblMiniImagen.setVisible(false);
		lblMiniImagen.setIcon(new ImageIcon(imagenObjetivo(Imagenes.get(nombre))));

		lblObjetivo.setVisible(false);
		/////////////////////////////////////
		frame.setVisible(true);

		//EMPEZAR A JUGAR
		int valor = JOptionPane.showConfirmDialog(frame,"Aprete enter/espacio para iniciar a jugar",null,JOptionPane.DEFAULT_OPTION);
		if(valor==JOptionPane.OK_OPTION) {
			actualizarPantalla(botones,tablero,gridPanel);
			frame.setVisible(true);
			lblMiniImagen.setVisible(true);
			lblObjetivo.setVisible(true);

		}
		JButton Ayuda = new JButton("Ayuda");
		Ayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(contAyuda<100) {
					tablero.ayuda();
					actualizarPantalla(botones,tablero,gridPanel);
					contAyuda++;
					int mov = tablero.consultarMovs();
					cantMovs.setText("" + mov);
					cantAyuda.setText(""+ contAyuda);
					// Asegurarse de que el foco esté en el frame
					frame.setFocusable(true);
					frame.requestFocusInWindow(); 
					if(confirmarVictoria(tablero))frame.setVisible(false);
				}
				else {
					int valor = JOptionPane.showConfirmDialog(frame,"TE QUEDASTE SIN AYUDAS",null,JOptionPane.CLOSED_OPTION);
					Ayuda.setFocusable(false);
				}
			}
		});
		frame.setFocusable(true);
		frame.requestFocusInWindow(); 
		Ayuda.setBounds(531, 444, 89, 23);
		frame.getContentPane().add(Ayuda);
		//MOVIMIENTO CON TECLADO
				frame.addKeyListener(new KeyAdapter(){	
					public void keyPressed(KeyEvent e) {
						if(e.getKeyChar()=='d') {
							tablero.mover(1, 0);
						}
						if(e.getKeyChar()=='a') { 
							tablero.mover(-1, 0);
						}
						if(e.getKeyChar()=='w') { 
							tablero.mover(0, -1);
						}
						if(e.getKeyChar()=='s') { 
							tablero.mover(0, 1);
						}
						actualizarPantalla(botones,tablero,gridPanel);
						frame.setVisible(true);
						if(confirmarVictoria(tablero)) {
							frame.setVisible(false);
						}
						int mov=tablero.consultarMovs();
						cantMovs.setText(""+mov);
					}});
				frame.setFocusable(true);
				frame.requestFocusInWindow();

	}
	//ACTUALIZAR PANTALLA
	public void actualizarPantalla(JButton[][] botones,Tablero tablero,JPanel panel) {
		botones[tablero.devuelveF()][tablero.devuelveC()].setVisible(false);
		panel.removeAll();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				botones[i][j] = new JButton(""+ tablero.consultarTablero(i, j));
				try {
					buffer= dividirImagen(this.nombre);
					ponerImagen(botones,tablero,buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				panel.add(botones[i][j]);
				Border borde= BorderFactory.createLineBorder(Color.GREEN);
				botones[i][j].setBorder(borde);
			}
		}	
		botones[tablero.devuelveF()][tablero.devuelveC()].setVisible(false);
		tablero.condicionGano();
	}
	private BufferedImage[] dividirImagen(String n) throws IOException {
		BufferedImage bi=Imagenes.get(n);
		int ancho=bi.getHeight()/4;
		int alto=bi.getWidth()/4;
		int cont=0;
		BufferedImage[] subimage = new BufferedImage[4*4];
		while (cont<4*4) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j <4; j++) {
					subimage[i+(j*4)] = bi.getSubimage(alto*i, ancho*j, alto, ancho);
					cont++;
				}
			}
			return subimage;
		}
		return null;
	}
	public void ponerImagen(JButton[][] botones,Tablero tablero, BufferedImage[] imagen) {
		ImageIcon imagenaux;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				imagenaux=new ImageIcon(imagen[(tablero.consultarTablero(i, j))-1]);
				botones[i][j].setIcon(imagenaux);
			}
		}
	}
	//CARGAMOS LAS IMAGENES EN EL HASHTABLE
	private void imagenes(){
		try {
			BufferedImage bi=ImageIO.read(new File("src/Imagenes/Messi.png"));
			Imagenes.put("messi", bi);
			bi=ImageIO.read(new File("src/Imagenes/loroo.png"));
			Imagenes.put("loro", bi);
			bi=ImageIO.read(new File("src/Imagenes/diego.png"));
			Imagenes.put("diego", bi);
			bi=ImageIO.read(new File("src/Imagenes/perro.png"));
			Imagenes.put("perritos", bi);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//ACHICAR IMAGEN PARA OBJETIVO
	private Image imagenObjetivo(BufferedImage img) {
		Image newImage = img.getScaledInstance(100, 100, img.SCALE_DEFAULT);
		return newImage;
	}
	//CREAR LOS 4 BOTONES DEL FRAME DEL MOVIMIENTO
	private JToggleButton crearBotonMovimiento(String texto,Tablero tablero,JButton[][] botones,JPanel gridPanel,int x,int y) {
		JToggleButton boton = new JToggleButton(texto);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarPantalla(botones, tablero, gridPanel);	
				int mov = tablero.consultarMovs();
				cantMovs.setText("" + mov);
			}
		});
		boton.setFocusable(false);
		boton.requestFocusInWindow();
		boton.setBounds(x, y, 70, 34);
		tablero.condicionGano();
		return boton;
	}
	private boolean confirmarVictoria(Tablero tablero) {
		if (tablero.ganaste()) {
			Gano gano=new Gano();
			return true;
		}
		return false;
	}
}

