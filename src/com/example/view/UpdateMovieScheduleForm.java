package com.example.view;

import com.cinema.dao.*;
import com.cinema.model.Cinema;
import com.cinema.model.Movie;
import com.cinema.model.Schedule;
import com.cinema.model.Theatre;
import com.cinema.util.DateConverter;
import com.cinema.util.TimeConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateMovieScheduleForm extends JFrame implements ActionListener {

    private AbstractDao<Movie> movieDao;
    private AbstractDao<Cinema> cinemaDao;
    private TheatreDaoImpl theatreDao;
    private AbstractDao<Schedule> scheduleDao;

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
    private BookingPage parentFrame;
    private Movie movie;
    private Theatre theatre;
    private Schedule schedule;



    private Cinema cinema;

    public UpdateMovieScheduleForm(BookingPage parentFrame, int selectedRecordId) {
        this.parentFrame = parentFrame;
        this.theatreDao = new TheatreDaoImpl();
        this.movieDao = new MovieDao();
        this.cinemaDao = new CinemaDaoImpl();
        this.scheduleDao = new ScheduleDao();
        this.schedule = this.scheduleDao.findbyId(selectedRecordId);

        this.cinema = this.schedule.getThreatre().getCinema();
        this.theatre = this.schedule.getThreatre();
        this.movie = this.schedule.getMovie();

        this.initializeComponent();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.createBtn) {
            this.createBtnAction();
        }
    }

    private void createBtnAction(){
        Schedule schedule = new Schedule();

        schedule.setMovie(this.movie);
        schedule.setThreatre(this.theatre);

        String startTime = this.startTimeField.getText();
        schedule.setStartTime(TimeConverter.toSqlTime(startTime));

        String endTimeStr = this.endTimeField.getText();
        schedule.setEndTime(TimeConverter.toSqlTime(endTimeStr));

        String publicDate = this.publicDateField.getText();
        schedule.setPublicDate(DateConverter.toSqlDate(publicDate));

        this.scheduleDao.create(schedule);

        JOptionPane.showMessageDialog(this, "Movie Schedule Successfully Created!!!!");
        this.parentFrame.refreshMovieScheduleListingTable();

        this.dispose();
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
        this.theatreLink = new JLabel(this.getSelectedTheatreLabel());
        this.theatreLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.theatreLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openTheatreListingPage();
            }
        });
    }

    public void openTheatreListingPage(){
        this.validateSelectedCinema();
        if(this.cinema != null){
            new TheatreListingPage(this, "edit");
        }
    }

    private void validateSelectedCinema() {
        if(this.cinema == null){
            JOptionPane.showMessageDialog(this, "Please Select a Cinema!!!");
        }
    }

    public void openCinemaListingPage(){
        new CinemaListingPage(this, "edit");
    }

    public String getSelectedMovieLabel(){
        if(this.movie==null){
            return "<html><a href=''>Select Movie</html>";
        }else{
            return "<html><a href=''>"+this.movie+"</html>";
        }
    }

    public String getSelectedTheatreLabel(){
        if(this.theatre != null){
            return "<html><a href=''>"+this.theatre+"</html>";
        }else{
            return "<html><a href=''>Select Theatre</html>";
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
        this.setTitle("Movie Schedule Update Form");
        this.setSize(500, 400);
        this.setLocation(100, 100);
        this.setLayout(new GridLayout(7,2, 10, 10));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.prepareMovieLabel();
        this.prepareCinemaLabel();
        this.prepareTheatreLabel();

        this.startTimeLabel = new JLabel("Start Time:");
        this.startTimeField = new JTextField(this.schedule.getStartTime().toString());

        this.endTimeLabel = new JLabel("End Time:");
        this.endTimeField = new JTextField(this.schedule.getEndTime().toString());

        this.publicDateLabel = new JLabel("Public Date:");
        this.publicDateField = new JTextField(this.schedule.getPublicDate().toString());

        this.createBtn = new JButton("Update");
        this.resetBtn = new JButton("Reset");

        this.createBtn.addActionListener(this);

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

    public void refreshSelectedTheatre(int selectedTheatreId){
        this.theatre = this.theatreDao.findbyId(selectedTheatreId);
        this.theatreLink.setText(this.getSelectedTheatreLabel());
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

    public Cinema getCinema() {
        return this.cinema;
    }
}
