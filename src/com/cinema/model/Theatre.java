package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
	private int id;
	private String name;
	private Cinema cinema;
	
	private List<Seat> seats = new ArrayList<Seat>();
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", cinema=" + cinema + "]";
	}
}
