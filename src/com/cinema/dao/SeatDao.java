package com.cinema.dao;

import java.sql.SQLException;
import java.util.List;

import com.cinema.model.Seat;

public abstract class SeatDao extends AbstractDao<Seat>{
	
	public abstract List<Seat> getAllSeatByTheatre(int id);
}
