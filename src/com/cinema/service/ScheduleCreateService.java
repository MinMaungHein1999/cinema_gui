package com.cinema.service;

import com.cinema.dao.ScheduleDaoImpl;
import com.cinema.error.CreateScheduleError;
import com.cinema.model.Schedule;

import javax.swing.*;

public class ScheduleCreateService {
    private  Schedule schedule;
    private ScheduleDaoImpl scheduleDao;

    public ScheduleCreateService(){
        this.scheduleDao = new ScheduleDaoImpl();
    }

    public void call(Schedule schedule) throws CreateScheduleError{
        this.schedule = schedule;
        this.validateSchedule();
    }

    private void validateSchedule() throws CreateScheduleError{
        if(!this.scheduleDao.findScheduleByBeforeCreatedSchedule(schedule).isEmpty()){
            throw new CreateScheduleError("This schedule is already have in the theatre "+this.schedule.getThreatre()+" at "+ this.schedule.getPublicDate());
        }else{
            this.scheduleDao.create(schedule);
        }
    }
}
