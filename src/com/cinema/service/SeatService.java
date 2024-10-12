package com.cinema.service;

import java.io.IOException;
import java.sql.SQLException;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.SeatDaoImpl;
import com.cinema.dao.TheatreDao;
import com.cinema.model.Seat;
import com.cinema.model.Theatre;

public class SeatService extends BaseService<Seat>{
	
	private static AbstractDao<Seat> seatDao = new SeatDaoImpl();
	private TheatreService theatreService;
	
	public SeatService() {
		super(seatDao);
		this.theatreService = new TheatreService();
		
	}

	@Override
	public String getEntity() {
		return "Seat";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter Seat Name : ");
		String name = br.readLine();
		this.theatreService.getAll();
		System.out.print("Enter Theatre Id : ");
		int theatre_id = Integer.parseInt(br.readLine());
		Theatre theatre = new Theatre();
		Seat seat = new Seat();
		seat.setTitle(name);
		theatre.setId(theatre_id);
		seat.setTheatre(theatre);
		seatDao.create(seat);
		
	}

}
