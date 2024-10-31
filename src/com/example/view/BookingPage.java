package com.example.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.cinema.dao.AbstractDao;
import com.cinema.model.Schedule;
import com.cinema.dao.ScheduleDaoImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class BookingPage {
	
	private AbstractDao<Schedule> scheduleDao;
	private JFrame bookingframe;
	private JTable moviesTable;
	private JScrollPane scrollPane;
	private JButton bookingBtn;
	private JButton createScheduleBtn;
	private JButton editScheduleBtn;
	private JButton deleteScheduleBtn;
	private String[] columns = {"id", "Movie Title", "Cinema Name", "Theatre Name", "Start Time", "End Time", "Public Date", "Duration"};
	private DefaultTableModel tableModel;

	public BookingPage() {
		System.out.println("calling constructor !!!!");
		this.scheduleDao = new ScheduleDaoImpl();
		this.initializeComponents();
		
	}
	
	private void initializeComponents() {
		this.bookingframe = new JFrame("Movie Schedule Listing");
		this.bookingframe.setSize(800, 500);
		this.bookingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.bookingframe.setLayout(new BorderLayout());
		this.tableModel = new DefaultTableModel(null, this.columns);
		this.moviesTable = new JTable(this.tableModel);
		TableColumn column = this.moviesTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(200);
		
		this.scrollPane = new JScrollPane(this.moviesTable);
		
		this.bookingframe.add(this.scrollPane, BorderLayout.CENTER);
		this.loadDataToMovieScheduleListing();

		this.bookingBtn = new JButton("Select Movie & Book Seat");
		this.createScheduleBtn = new JButton("Create");
		this.editScheduleBtn = new JButton("Edit");
		this.deleteScheduleBtn = new JButton("Delete");
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,4));
		btnPanel.add(bookingBtn);
		btnPanel.add(createScheduleBtn);
		btnPanel.add(editScheduleBtn);
		btnPanel.add(deleteScheduleBtn);
		this.bookingframe.add(btnPanel, BorderLayout.SOUTH);
		selectMovieForBookingAction();
		addActionCreateBtn();
		addActionEditBtn();
		this.bookingframe.setLocation(100, 100);
		this.bookingframe.setVisible(true);
	}

	public void loadDataToMovieScheduleListing(){
		for(String[] movieArr : getMoviesData()){
			this.tableModel.addRow(movieArr);
		}
	}
	
	private String[][] getMoviesData(){
		
		List<Schedule> schedules = this.scheduleDao.getAll();
		String[][] moviesData = new String[schedules.size()][columns.length];
		int rowCount = 0;
		for(Schedule schedule : schedules) {
			for(int i =0 ; i < columns.length; i++) {
				moviesData[rowCount][i] = schedule.toArray()[i];
			}
			rowCount++;
		}
		
		return moviesData;
	}
	
	private void addActionEditBtn(){
		this.editScheduleBtn.addActionListener(e -> editBtnAction());
	}

	private void editBtnAction() {
		int selectedRow = moviesTable.getSelectedRow();
		if(selectedRow != -1) {
			int scheduleId = Integer.parseInt(getMoviesData()[selectedRow][0]);
			new UpdateMovieScheduleForm(this, scheduleId);
		} else {
			JOptionPane.showMessageDialog(bookingframe, "Please select a movie");
		}
	}

	private void addActionCreateBtn() {
		this.createScheduleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openCreateMovieScheudlePage();
			}
			
		});
	}

	private void openCreateMovieScheudlePage(){
		new CreateMovieSchedulePage(this);
	}

	public void refreshMovieScheduleListingTable(){
		this.tableModel.setRowCount(0);
		this.loadDataToMovieScheduleListing();
	}
	
	private void selectMovieForBookingAction() {
		this.bookingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = moviesTable.getSelectedRow();
				if(selectedRow != -1) {
					int scheduleId = Integer.parseInt(getMoviesData()[selectedRow][0]);
					try {
						SeatView seatView = new SeatView(scheduleId);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(bookingframe, "Please select a movie");
				}
				
			}
			
		});
	}
}
