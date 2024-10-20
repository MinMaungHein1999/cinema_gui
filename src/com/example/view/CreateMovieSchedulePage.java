package com.example.view;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.CinemaDao;
import com.cinema.dao.MovieDao;
import com.cinema.model.Movie;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.cinema.model.Cinema;

public class CreateMovieSchedulePage extends JFrame implements ActionListener {
	private AbstractDao<Movie> movieDao;
	private AbstractDao<Cinema> cinemaDao;
	private JLabel movieLabel;
	private JLabel movieLink;
	
	private JLabel cinemaLabel;
	private JLabel cinemaLink;
	
	private JLabel theatreLabel;
	private JLabel theatreLink;
	
	private JLabel startTimeLabel;
	private JTextField startTimeField;
	
	private JLabel endTimeLabel;
	private JTextField endTimeField;
	
	private JLabel publicDateLabel;
	private JTextField publicDateField;
	
	private JButton createBtn;
	private JButton resetBtn;

	private Movie movie;
	private Cinema cinema;
	
	public CreateMovieSchedulePage() {
		this.movieDao = new MovieDao();
		this.cinemaDao = new CinemaDao();

		this.initializeComponent();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void prepareCinemaLabel() {
		this.cinemaLabel = new JLabel("Cinema:");
		this.cinemaLink = new JLabel(this.getSelecteCinemaLabel());
		this.cinemaLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.cinemaLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCinemaListingPage();
			}
			
		});
	}
	public void prepareTheatreLabel() {
		this.theatreLabel = new JLabel("Theatre:");
		this.theatreLink = new JLabel("<html><a href=''>Select Theatre</html>");
		this.theatreLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.theatreLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicket select theatre!!!!");
			}
			
		});
	}

	public void openCinemaListingPage(){
		new CinemaListingPage(this);
	}

	public String getSelectedMovieLabel(){
		if(this.movie==null){
			return "<html><a href=''>Select Movie</html>";
		}else{
			return "<html><a href=''>"+this.movie+"</html>";
		}
	}

	public void prepareMovieLabel() {
		this.movieLabel = new JLabel("Movie:");
		this.movieLink = new JLabel(getSelectedMovieLabel());
		this.movieLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.movieLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openMovieListingPage();
			}
			
		});
	}

	public void openMovieListingPage(){
		new MovieListingPage(this);
	}


	private void initializeComponent() {
		this.setTitle("Movie Schedule Register");
		this.setSize(500, 400);
		this.setLocation(100, 100);
		this.setLayout(new GridLayout(7,2, 10, 10));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		prepareMovieLabel();
		prepareCinemaLabel();
		prepareTheatreLabel();
		
		this.startTimeLabel = new JLabel("Start Time:");
		this.startTimeField = new JTextField();
		
		this.endTimeLabel = new JLabel("End Time:");
		this.endTimeField = new JTextField();
		
		this.publicDateLabel = new JLabel("Public Date:");
		this.publicDateField = new JTextField();
		
		this.createBtn = new JButton("Create");
		this.resetBtn = new JButton("Reset");
		
		addUIComponent();
		
		this.setVisible(true);
		
	}

	private void addUIComponent() {
		
		this.add(this.movieLabel);
		this.add(this.movieLink);
		
		this.add(this.cinemaLabel);
		this.add(this.cinemaLink);
		
		this.add(this.theatreLabel);
		this.add(this.theatreLink);
		
		this.add(this.startTimeLabel);
		this.add(this.startTimeField);
		
		this.add(this.endTimeLabel);
		this.add(this.endTimeField);
		
		this.add(this.publicDateLabel);
		this.add(this.publicDateField);
		
		this.add(this.createBtn);
		this.add(this.resetBtn);
		
	}

	public void refreshSelectedMovie(int movieId) {
		this.movie = this.movieDao.findbyId(movieId);
		this.movieLink.setText(this.getSelectedMovieLabel());
	}

	public void refreshSelectedCinema(int cinemaId) {
		this.cinema = this.cinemaDao.findbyId(cinemaId);
		this.cinemaLink.setText(this.getSelecteCinemaLabel());
	}

	private String getSelecteCinemaLabel() {
		if(this.cinema==null){
			return "<html><a href=''>Select Cinema</html>";
		}else{
			return "<html><a href=''>"+this.cinema+"</html>";
		}
	}
}
