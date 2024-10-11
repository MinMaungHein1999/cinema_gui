package com.cinema.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import com.cinema.dao.*;
import com.cinema.dao.AbstractDao;
import com.cinema.model.Movie;
import com.cinema.model.Schedule;
import com.cinema.model.Theatre;

public class ScheduleService extends BaseService<Schedule> {
	
	private AbstractDao<Movie> movieDao;
	private AbstractDao<Theatre> theatreDao;
	private static AbstractDao<Schedule> scheduleDao = new ScheduleDao();

	public ScheduleService() {
		super(scheduleDao);
		this.movieDao = new MovieDao();
		this.theatreDao = new TheatreDao();
	}

	@Override
	public String getEntity() {
		return "Schedule";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter Movie ID : ");
		int movieId = Integer.parseInt(br.readLine());
		Movie movie = this.movieDao.findbyId(movieId);
		
		System.out.print("Enter Theatre ID : ");
		int theatreId = Integer.parseInt(br.readLine());
		Theatre theatre = this.theatreDao.findbyId(theatreId);
		
		System.out.print("Enter Start Time (HH:MM:SS) : ");
		Time startTime = Time.valueOf(br.readLine());
		System.out.print("Enter End Time (HH:MM:SS) : ");
		Time endTime = Time.valueOf(br.readLine());
		System.out.print("Enter Public Date (YYYY-MM-DD) : ");
		Date publicDate = Date.valueOf(br.readLine());
		
		Schedule schedule = new Schedule(movie, theatre, startTime, endTime, publicDate);
		scheduleDao.create(schedule);
		
	}

}
