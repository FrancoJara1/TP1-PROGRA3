package view;


import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FrameMenu {
	private JFrame frame;
	private JTextField txtBienvenidosAlRompecabezas;
	private FrameNumeros juego;
	private Reglas reglas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private FrameImagenes imagenmodo;

	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMenu window = new FrameMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FrameMenu() {
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
		initialize();
		cerrar();
	}
	private void initialize() {
		//CREACION DEL FRAME///////////////////////////
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 255, 255));
		frame.setBounds(300, 100, 592, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		/////////////////////////////////////////////////
		//TXT BIENVENIDOS////////////////////////////////
		txtBienvenidosAlRompecabezas = new JTextField();
		txtBienvenidosAlRompecabezas.setBounds(131, 37, 219, 75);
		txtBienvenidosAlRompecabezas.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBienvenidosAlRompecabezas.setBackground(Color.LIGHT_GRAY);
		txtBienvenidosAlRompecabezas.setHorizontalAlignment(SwingConstants.CENTER);
		txtBienvenidosAlRompecabezas.setEditable(false);
		txtBienvenidosAlRompecabezas.setText("BIENVENIDOS AL ROMPECABEZAS");
		txtBienvenidosAlRompecabezas.setColumns(10);
		///////////////////////////////////////////////////
		
		
		//LABEL MODO DE JUEGO/////////////////////////////////////
		JLabel lblIngrese = new JLabel("Ingrese modo de juego:");
		lblIngrese.setBounds(73, 157, 121, 30);
		lblIngrese.setVisible(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblIngrese);
		lblIngrese.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 11));
		////////////////////////////////////////////////////////
		
		///COMBO BOX Y RADIOBUTTON////////////////////////////////
		JRadioButton radioNumeros = new JRadioButton("Números");
		radioNumeros.setBounds(199, 167, 109, 23);
		buttonGroup.add(radioNumeros);
		frame.getContentPane().add(radioNumeros);
		radioNumeros.setVisible(false);
		JRadioButton radioImagenes = new JRadioButton("Imágenes");
		radioImagenes.setBounds(199, 193, 109, 23);
		buttonGroup.add(radioImagenes);
		frame.getContentPane().add(radioImagenes);
		radioImagenes.setVisible(false);
		
		JComboBox comboImagenes = new JComboBox();
		comboImagenes.setBounds(174, 223, 134, 22);
		comboImagenes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una imagen", "Messi", "Loro", "Diego", "Perritos"}));
		frame.getContentPane().add(comboImagenes);
		comboImagenes.setVisible(false);
		
		//////////////////////////////////////////////////////
		JButton botonComenzar = new JButton("COMENZAR");
		botonComenzar.setBounds(174, 256, 134, 23);
		botonComenzar.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
		frame.getContentPane().add(botonComenzar);
		botonComenzar.setVisible(false);
		
		//BOTON PARA SALIR DEL JUEGO 
		JToggleButton botonSalir = new JToggleButton("SALIR");
		botonSalir.setBounds(174, 223, 134, 23);
		botonSalir.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor = JOptionPane.showConfirmDialog(botonSalir, "¿Esta seguro de cerrar el juego?","Advertencia", JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		frame.getContentPane().add(txtBienvenidosAlRompecabezas);
		frame.getContentPane().add(botonSalir);
		///////////////////////////////////////////////////////////////////////
		//BOTON PARA FRAME REGLAS
		JButton botonReglas = new JButton("REGLAS");
		botonReglas.setBounds(174, 167, 134, 23);
		botonReglas.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
		botonReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Reglas reglas=new Reglas();
			    frame.setVisible(false);
			}
		});
		frame.getContentPane().add(botonReglas);
		
		//BOTON PARA JUGAR Y SELECCIONAR MODO DE JUEGO
		JToggleButton botonJugar = new JToggleButton("JUGAR");
		botonJugar.setBounds(174, 123, 134, 23);
		botonJugar.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 11));
		frame.getContentPane().add(botonJugar);
		botonJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblIngrese.setVisible(true);
				radioNumeros.setVisible(true);
				radioImagenes.setVisible(true);
				botonComenzar.setVisible(true);
				botonSalir.setBounds(174, 330, 134, 23);
				botonReglas.setBounds(174, 300, 134, 23);
				botonComenzar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(radioNumeros.isSelected()) {
							FrameNumeros juego=new FrameNumeros();
							frame.setVisible(false);
						}
					}
				});
				radioImagenes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						comboImagenes.setVisible(true);	
						botonComenzar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								String item=comboImagenes.getSelectedItem().toString();

								if(item=="Messi") {
									FrameImagenes imagenmodo= new FrameImagenes("messi");
									frame.setVisible(false);
								}
								if(item=="Diego") {
									FrameImagenes imagenmodo= new FrameImagenes("diego");
									frame.setVisible(false);	
								}
								if(item=="Perritos") {
									FrameImagenes imagenmodo= new FrameImagenes("perritos");
									frame.setVisible(false);
								}
								if(item=="Loro") {
									FrameImagenes imagenmodo= new FrameImagenes("loro");
									frame.setVisible(false);	
								}
							}
						});
					}
				});
			}
		});
		/////CARGAMOS LA IMAGEN DE FONDO//////////////////////////////////////////////////////////
		JLabel lblMenu = new JLabel("");
		lblMenu.setVerticalAlignment(SwingConstants.TOP);
		lblMenu.setBounds(0, 0, 500, 500);
		lblMenu.setIcon(new ImageIcon("src/Imagenes/MenuJuego (1).png"));
		frame.getContentPane().add(lblMenu);	
}
	//PARA CONFIRMAR LA SALIDA DEL JUEGO
	public void cerrar() {
		try {
			this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					confirmarSalida();
				}
			});
			frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void confirmarSalida() {
		int valor = JOptionPane.showConfirmDialog(frame, "¿Esta seguro de cerrar el juego?","Advertencia", JOptionPane.YES_NO_OPTION);
		if (valor == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
