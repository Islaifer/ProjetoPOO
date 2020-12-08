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
	
	public Reservation post(int rgUser, Date date, String spaceName, int peopleqnt) throws Exception {
		return reservationService.post(rgUser, date, spaceName, peopleqnt);
	}
	
	public void deleteById(int id) throws Exception {
		this.reservationService.deleteById(id);;
	}
}
