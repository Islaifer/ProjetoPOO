package controllers;

import java.util.Date;
import java.util.List;

import models.Reservation;
import services.ReservationService;

public class ReservationController {
	private ReservationService reservationService;
	public ReservationController() {
		reservationService = new ReservationService();
	}
	
	public List<Reservation> getAll() throws Exception{
		return this.reservationService.getAll();
	}
	
	public Reservation getById(int id) throws Exception {
		return this.reservationService.getById(id);
	}
	
	public void post(long cpfUser, Date date, int spaceId, int peopleqnt) throws Exception {
		reservationService.post(cpfUser, date, spaceId, peopleqnt);
	}
	
	public void deleteById(int id) throws Exception {
		this.reservationService.deleteById(id);;
	}
}
