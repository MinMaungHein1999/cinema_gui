package com.cinema.service;

import java.io.IOException;
import java.sql.SQLException;
import com.cinema.dao.*;
import com.cinema.dao.AbstractDao;
import com.cinema.model.Cinema;

public class CinemaService extends BaseService<Cinema>{
	private static AbstractDao<Cinema> cinemaDao = new CinemaDao();
	
	public CinemaService() {
		super(cinemaDao);
	}

	@Override
	public String getEntity() {
		return "Cineam";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter Cinema name: ");
		String name = br.readLine();
		Cinema cinema = new Cinema();
		cinema.setName(name);
		System.out.print("Enter Cinema Address: ");
		String address = br.readLine();
		cinema.setAddress(address);
		cinemaDao.create(cinema);
	}

	
}
