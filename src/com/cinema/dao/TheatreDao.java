package com.cinema.dao;

import com.cinema.model.Theatre;

import java.util.List;

public abstract class TheatreDao extends AbstractDao<Theatre> {
    public abstract List<Theatre> getTheatresByCinema(int cinemaId);
}
