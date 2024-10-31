package com.example.view;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.CinemaDaoImpl;
import com.cinema.model.Cinema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CinemaListingPage extends JFrame implements ActionListener {
    private AbstractDao<Cinema> cinemaDao;
    private JTable cinemaTable;
    private DefaultTableModel tabelModel;
    private String[][] cinemaDataTable;
    private JButton selectBtn;
    private String[] columns = {"Id", "Name", "Address"};
    private JFrame parentPage;

    public CinemaListingPage(JFrame parentPage){
        this.parentPage = parentPage;
        this.cinemaDao = new CinemaDaoImpl();
        this.setLayout(new BorderLayout());
        initializeBtnComponent();
        initializeTableComponent();
        this.setLocation(200, 150);
        this.setTitle("Cinema Listing Page");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initializeTableComponent() {
        this.tabelModel = new DefaultTableModel(null, this.columns);
        this.cinemaTable = new JTable(this.tabelModel);
        this.add(this.cinemaTable, BorderLayout.CENTER);
        this.loadCineamDataTable();
        this.prepareCinemaDataModel();
    }

    private void prepareCinemaDataModel() {
        for(String[] cinemaDataRow : this.cinemaDataTable){
            this.tabelModel.addRow(cinemaDataRow);
        }
    }

    public void initializeBtnComponent(){
        this.selectBtn = new JButton("Select Cineam");
        this.selectBtn.addActionListener(this);
        this.add(selectBtn, BorderLayout.SOUTH);
    }

    public void loadCineamDataTable(){
        List<Cinema> cinemas = this.cinemaDao.getAll();
        this.cinemaDataTable = new String[cinemas.size()][this.columns.length];
        int rowCount = 0;
        for(Cinema cinema : cinemas){
            this.cinemaDataTable[rowCount] = cinema.toArray();
            rowCount++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.selectBtn){
            int cinemaId = this.getSelectedCinemaId();
            if(this.parentPage instanceof UpdateMovieScheduleForm){
                UpdateMovieScheduleForm page=(UpdateMovieScheduleForm) this.parentPage;
                page.refreshSelectedCinema(cinemaId);
            } else if (this.parentPage instanceof  CreateMovieSchedulePage) {
                CreateMovieSchedulePage page=(CreateMovieSchedulePage) this.parentPage;
                page.refreshSelectedCinema(cinemaId);
            }

            this.dispose();
        }
    }

    public int getSelectedCinemaId(){
       return Integer.parseInt(this.cinemaDataTable[this.getSelectedRow()][0]);
    }

    public int getSelectedRow(){
        int selectedRow = this.cinemaTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Please select a cinema!!");
            return -1;
        }
        return selectedRow;
    }
}
