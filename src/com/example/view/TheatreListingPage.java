package com.example.view;

import com.cinema.dao.TheatreDaoImpl;
import com.cinema.model.Cinema;
import com.cinema.model.Theatre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TheatreListingPage extends JFrame implements ActionListener {

    private TheatreDaoImpl theatreDao;
    private JButton selectBtn;
    private JTable theatreListingTable;
    private DefaultTableModel tableModel;
    private String[][] theatresData;
    private String[] theatreTableColumn;
    private Cinema cinema;
    private JFrame parentPage;

    public TheatreListingPage(JFrame parentPage){
        this.theatreDao = new TheatreDaoImpl();
        this.parentPage = parentPage;
        this.prepareSelectedCinema();
        this.initializeComponents();
        this.addComponentsToUI();
        this.prepareTheatresData();
        this.loadDataToTable();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void loadDataToTable(){
        System.out.println("theatres Data : "+ this.theatresData.length);
        for(String[] row : this.theatresData){
            this.tableModel.addRow(row);
        }
    }

    private void initializeComponents() {
        this.selectBtn = new JButton("Select Theatre");
        this.selectBtn.addActionListener(this);
        this.theatreTableColumn = new String[]{"Id", "Name"};
        this.tableModel = new DefaultTableModel(null, this.theatreTableColumn);
        this.theatreListingTable = new JTable(this.tableModel);
    }

    private void addComponentsToUI(){
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        this.setTitle("Theatres Listing for "+ this.cinema.getName());
        this.add(this.theatreListingTable, BorderLayout.CENTER);
        this.add(this.selectBtn, BorderLayout.SOUTH);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void prepareSelectedCinema(){
        CreateMovieSchedulePage createMovieSchedulePage = (CreateMovieSchedulePage) this.parentPage;
        this.cinema = createMovieSchedulePage.getCinema();
    }

    private void prepareTheatresData(){
        List<Theatre> theatres = this.theatreDao.getTheatresByCinema(this.cinema.getId());
        System.out.println(theatres.size());
        this.theatresData = new String[theatres.size()][this.theatreTableColumn.length];
        int rowCount = 0;
        for(Theatre theatre : theatres){
            this.theatresData[rowCount] = theatre.toArray();
            rowCount++;
        }
    }
}
