package com.cinema.service;

import java.io.IOException;
import java.sql.SQLException;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.TheatreDao;
import com.cinema.model.Cinema;
import com.cinema.model.Theatre;

public class TheatreService extends BaseService<Theatre>{
	private CinemaService cinemaService;
	private static AbstractDao<Theatre> theatreDao = new TheatreDao();
	
	public TheatreService() {
		super(theatreDao);
		this.cinemaService = new CinemaService();
	}

	@Override
	public String getEntity() {
		return "Theatre";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter theatre name : ");
		String name = br.readLine();
		this.cinemaService.getAll();
		System.out.print("Enter cinema Id : ");
		int cinema_id = Integer.parseInt(br.readLine());
		Theatre theatre = new Theatre();
		Cinema cinema = new Cinema();
		cinema.setId(cinema_id);
		
		theatre.setName(name);
		theatre.setCinema(cinema);
	}

}
