package com.cinema.model;

public class Movie {
	private int id;
	private String title;
	private String duration;
	
	public int getId() {
		return id;
	}
	public String getDuration() {
		return duration;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", duration=" + duration + "]";
	}

}
