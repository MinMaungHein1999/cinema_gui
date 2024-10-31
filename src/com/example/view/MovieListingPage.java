package com.example.view;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.MovieDao;
import com.cinema.model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MovieListingPage extends JFrame implements ActionListener {
    private AbstractDao<Movie> movieDao;
    private JTable movieTable;
    private JButton selectMovieBtn;
    private DefaultTableModel tableModel;
    private String[] columns = {"Id", "Title", "Duration"};
    private String[][] movieDataTable;
    private JFrame parentPage;
    private JScrollPane scrollPane;

    public MovieListingPage(JFrame parentPage){
        this.parentPage = parentPage;
        this.movieDao = new MovieDao();
        initializeComponent();
        initializeTableComponent();
        this.setVisible(true);

    }

    private void initializeComponent(){

        this.selectMovieBtn = new JButton("Select Movie");
        this.setSize(600, 400);
        this.setLocation(100, 100);
        this.setTitle("Movie Listing");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.selectMovieBtn.addActionListener(this);
        this.add(this.selectMovieBtn, BorderLayout.SOUTH);
    }

    private void initializeTableComponent(){
        this.tableModel = new DefaultTableModel(null, columns);
        this.movieTable = new JTable(this.tableModel);
        this.scrollPane = new JScrollPane(this.movieTable);
        this.add(this.scrollPane, BorderLayout.CENTER);
        prepareMovieDataTable();
        this.loadMovieData();
    }

    private void loadMovieData() {
        for(String[] movieDataRow : this.movieDataTable){
            this.tableModel.addRow(movieDataRow);
        }
    }

    private void prepareMovieDataTable() {
        List<Movie> movies = this.movieDao.getAll();
        this.movieDataTable = new String[movies.size()][this.columns.length];
        int rowCount = 0;
        for(Movie movie : movies){
            this.movieDataTable[rowCount] = movie.toArray();
            rowCount++;
        }
    }

    private void selectBtnAction(){
        int movieId = getSelectedMovieId();
        if(this.parentPage instanceof CreateMovieSchedulePage){
            CreateMovieSchedulePage page = (CreateMovieSchedulePage) this.parentPage;
            page.refreshSelectedMovie(movieId);
        }else if(this.parentPage instanceof  UpdateMovieScheduleForm){
            UpdateMovieScheduleForm page = (UpdateMovieScheduleForm) this.parentPage;
            page.refreshSelectedMovie(movieId);
        }

        this.dispose();
    }

    private int getSelectedMovieId(){
        return Integer.parseInt(this.movieDataTable[this.getSelectedIndex()][0]);
    }

    private int getSelectedIndex(){
       int selectedRow = this.movieTable.getSelectedRow();
       if(selectedRow == -1){
           JOptionPane.showMessageDialog(this, "Please Select a Movie");
       }else{
        return selectedRow;
       }
       return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.selectMovieBtn){
            this.selectBtnAction();
        }
    }
}
