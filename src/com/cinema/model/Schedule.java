package com.cinema.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private int id;
	private Movie movie;
	private Theatre threatre;
	private Time startTime;
	private Time endTime;
	private Date publicDate;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	public Schedule() {
		
	}
	
	public Schedule(Movie movie, Theatre threatre, Time startTime, Time endTime, Date publicDate) {
		this.movie = movie;
		this.threatre = threatre;
		this.startTime = startTime;
		this.endTime = endTime;
		this.publicDate = publicDate;

	}
	
	public int getId() {
		return id;
	}
	public Movie getMovie() {
		return movie;
	}
	public Theatre getThreatre() {
		return threatre;
	}
	public Time getStartTime() {
		return startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setThreatre(Theatre threatre) {
		this.threatre = threatre;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public String[] toArray() {
		String[] movieData = new String[8];
		movieData[0] = this.id+"";
		movieData[1] = this.movie.getTitle();
		movieData[2] = this.threatre.getCinema().getName();
		movieData[3] = this.threatre.getName();
		movieData[4] = this.startTime.toString();
		movieData[5] = this.endTime.toString();
		movieData[6] = this.publicDate.toString();
		movieData[7] = this.movie.getDuration();
		return movieData;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", movie Title =" + movie.getTitle() + ", threatre Name=" + threatre.getName() + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", publicDate=" + publicDate +"]";
	}
	
}
