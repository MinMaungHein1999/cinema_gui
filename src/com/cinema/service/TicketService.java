package com.cinema.service;

import java.io.IOException;
import java.sql.SQLException;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.TicketDao;
import com.cinema.dao.SeatDaoImpl;
import com.cinema.dao.CustomerDao;
import com.cinema.dao.ScheduleDao;
import com.cinema.model.Customer;
import com.cinema.model.Schedule;
import com.cinema.model.Seat;
import com.cinema.model.Ticket;

public class TicketService extends BaseService<Ticket>{
	
	private static AbstractDao<Ticket> ticketDao = new TicketDao();
	private AbstractDao<Customer> customerDao;
	private AbstractDao<Seat> seatDao;
	private AbstractDao<Schedule> scheduleDao;
	private BaseService<Customer> customerService;
	private BaseService<Seat> seatService;
	private BaseService<Schedule> scheduleService;
	
	public TicketService() {
		super(ticketDao);
		this.customerDao = new CustomerDao();
		this.seatDao = new SeatDaoImpl();
		this.scheduleDao = new ScheduleDao();
		this.customerService = new CustomerService();
		this.seatService = new SeatService();
		this.scheduleService = new ScheduleService();
		
	}

	@Override
	public String getEntity() {
		return "Ticket";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter Ticket Price : ");
		double price = Double.parseDouble(br.readLine());
		
		this.customerService.getAll();
		
		System.out.print("Enter Customer Id : ");
		int customer_id = Integer.parseInt(br.readLine());
		Customer customer = this.customerDao.findbyId(customer_id);
		
		this.seatService.getAll();
		
		System.out.print("Enter Seat Id : ");
		int seat_id = Integer.parseInt(br.readLine());
		Seat seat = this.seatDao.findbyId(seat_id);
		
		this.scheduleService.getAll();
		
		System.out.print("Enter Schedule Id : ");
		int schedule_id = Integer.parseInt(br.readLine());
		Schedule schedule = this.scheduleDao.findbyId(schedule_id);
		
		
		Ticket ticket = new Ticket();
		ticket.setPrice(price);
		ticket.setCustomer(customer);
		ticket.setSeat(seat);
		ticket.setSchedule(schedule);
		
		ticketDao.create(ticket);
		
	}

}
