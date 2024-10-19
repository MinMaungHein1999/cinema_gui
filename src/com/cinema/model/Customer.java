package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private String name;
	private String email;
	private String address;
	
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	public Customer() {
		
	}
	
	public Customer(int id, String name, String email, String address) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public Customer(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}
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
	public String[] toArray() {
		String[] customerArr = { this.id+"", this.name, this.email, this.address };
		return customerArr;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
