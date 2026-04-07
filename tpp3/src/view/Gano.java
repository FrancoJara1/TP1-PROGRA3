package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Gano {
	private JFrame frame;
	private FrameMenu menu;
	private String nombre;
	
	public Gano() {
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
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(0, 0, 600, 555));
		frame.getContentPane().setBackground(new Color(128, 255, 255));
		frame.getContentPane().setLayout(null);


		//BOTON VOLVER AL MENU
		final JToggleButton botonVolver = new JToggleButton("Volver al menù");
		botonVolver.setFont(new Font("Eras Medium ITC", Font.ITALIC, 12));
		botonVolver.setForeground(Color.RED);
		botonVolver.setBackground(Color.YELLOW);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor = JOptionPane.showConfirmDialog(frame, "¿Esta seguro de volver al menù ?","Advertencia", JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.YES_OPTION) {
					menu=new FrameMenu();
					frame.setVisible(false);
				}
			}

		});

		botonVolver.setBounds(463, 11, 121, 23);

		frame.getContentPane().add(botonVolver);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon("src/Imagenes/Ganaste.png"));
		lblNewLabel.setMaximumSize(new Dimension(300, 300));
		lblNewLabel.setBounds(0, 0, 600, 550);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 100, 592, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(610, 582);
		frame.setVisible(true);
	}
}
