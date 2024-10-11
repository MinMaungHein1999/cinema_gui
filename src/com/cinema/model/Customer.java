package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private String name;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name +"]";
	}
}
