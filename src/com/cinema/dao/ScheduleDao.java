package com.cinema.dao;

import com.cinema.model.Schedule;

import java.util.List;

public abstract  class ScheduleDao extends AbstractDao<Schedule> {
    public abstract List<Schedule> findScheduleByBeforeCreatedSchedule(Schedule schedule);
}
