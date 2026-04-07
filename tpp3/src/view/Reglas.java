package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Reglas {

	private JFrame frame;
	private FrameMenu menu;

	public Reglas() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 701, 401));
		frame.setBounds(100, 100, 711, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/Imagenes/Reglas (1).png"));
		lblNewLabel.setBounds(0, 0, 695, 364);
		frame.getContentPane().add(lblNewLabel);
		System.out.println(lblNewLabel);
		JButton botonVolver = new JButton("Volver al Menú");
		botonVolver.setBounds(560, 11, 135, 23);
		frame.getContentPane().add(botonVolver);
		frame.setVisible(true);
		botonVolver.setForeground(Color.RED);
		botonVolver.setBackground(Color.YELLOW);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					menu=new FrameMenu();
				    frame.setVisible(false);
				    
				}
		});	
	}
}
