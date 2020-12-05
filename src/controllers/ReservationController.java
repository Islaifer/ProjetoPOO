package controllers;

import java.util.List;

import daos.ReservationDao;
import models.Reservation;

public class ReservationController {
	public ReservationController() {
		super();
	}
	
	public List<Reservation> getAll() throws Exception{
		return ReservationDao.get();
	}
	
	public Reservation getById(int id) throws Exception {
		return ReservationDao.getById(id);
	}
	
	public Reservation post(Reservation reservation) throws Exception {
		ReservationDao.insert(reservation);
		return reservation;
	}
	
	public List<Reservation> post(Reservation reservation, List<Reservation> list) throws Exception {
		ReservationDao.insert(reservation);
		list.add(reservation);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		ReservationDao.deleteById(id);
	}
}
