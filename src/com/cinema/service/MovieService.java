package com.cinema.service;

import java.io.IOException;
import java.sql.SQLException;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.MovieDao;
import com.cinema.model.Movie;


public class MovieService extends BaseService<Movie>{
	
	private static AbstractDao<Movie> abstractDao = new MovieDao();

	public MovieService() {
		super(abstractDao);
	}

	@Override
	public String getEntity() {
		return "Movie";
	}

	@Override
	public void register() throws IOException {
		System.out.print("Enter Movie Title : ");
		String title = br.readLine();
		System.out.print("Enter Movie duration : ");
		String duration = br.readLine();
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setDuration(duration);
		abstractDao.create(movie);
		
	}

}
