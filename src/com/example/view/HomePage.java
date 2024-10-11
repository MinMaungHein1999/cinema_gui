package com.example.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomePage {
	
	private JFrame homeFrame;
	private JMenuBar menuBar;
	private JMenuItem bookingsItem;
	private JMenuItem moviesItem;
	private JMenuItem cinemasItem;
	

	public HomePage() {
		initializeComponents();
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.setLocation(0, 0);
		homeFrame.setSize(700, 500);
		homeFrame.setVisible(true);
		
	}
	
	
	private void initializeComponents() {
		this.homeFrame = new JFrame();
		this.menuBar = new JMenuBar();
		this.bookingsItem = new JMenuItem("Bookings");
		this.moviesItem = new JMenuItem("Movies");
		this.cinemasItem = new JMenuItem("Cineams");
		
		this.menuBar.add(bookingsItem);
		this.menuBar.add(moviesItem);
		this.menuBar.add(cinemasItem);
		
		this.homeFrame.setJMenuBar(this.menuBar);
	}
	

	

}
