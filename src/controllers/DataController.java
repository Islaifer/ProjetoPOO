package controllers;

import java.util.Date;

import javax.swing.JOptionPane;

import models.Reservation;
import models.Space;
import models.User;

public class DataController {
	private PaymentController payment;
	private PlanController plan;
	private ReservationController reservation;
	private SpaceController space;
	private UserController user;
	
	public DataController() {
		this.payment = new PaymentController();
		this.plan = new PlanController();
		this.reservation = new ReservationController();
		this.space = new SpaceController();
		this.user = new UserController();
	}
	
	public void addReservation(int rgUser, Date date, String spaceName, int peopleqnt) throws Exception {
		User user = this.user.getById(rgUser);
		Space space = this.space.getById(0);
		if(user.getId() != 0) {
			if(space.getId() != 0) {
				Reservation reservation = new Reservation(0, user, date, space, peopleqnt);
				this.reservation.post(reservation);
			}else {
				JOptionPane.showMessageDialog(null, "Espaço não existe!", "ERROR", 0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Associado não encontrado", "ERROR", 0);
		}
	}
}
