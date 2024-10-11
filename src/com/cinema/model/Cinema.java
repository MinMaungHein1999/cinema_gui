package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
	private int id;
	private String name;
	private String address;
	private List<Theatre> threatres = new ArrayList<Theatre>();
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
