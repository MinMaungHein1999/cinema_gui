package com.cinema.model;

public class Ticket {
	private int id;
	private double price;
	private Schedule schedule;
	private Seat seat;
	private Customer customer;
	
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public Seat getSeat() {
		return seat;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "\nTicket [id=" + id + ", price=" + price + ", \nschedule=" + schedule + ", \nseat =" + seat.getTitle() + ", \ncustomer="
				+ customer.getName() + "] \n ========================";
	}
	
}
