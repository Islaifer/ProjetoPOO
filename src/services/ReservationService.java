package services;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;


import daos.ReservationDao;
import models.Reservation;
import models.Space;
import models.User;

public class ReservationService {
	private SpaceService space;
	private UserService user;
	
	public ReservationService() {
		this.space = new SpaceService();
		this.user = new UserService();
	}
	
	public List<Reservation> getAll() throws Exception{
		return ReservationDao.get();
	}
	
	public Reservation getById(int id) throws Exception {
		return ReservationDao.getById(id);
	}
	
	public void post(long cpfUser, Date date, int spaceId, int peopleqnt) throws Exception {
		User user = this.user.getByCPF(cpfUser);
		Space space = this.space.getById(spaceId);
		if(user.getId() != 0) {
			if(space.getId() != 0) {
				if(!existReservationInTheSameDay(0, date, spaceId)) {
					Reservation reservation = new Reservation(0, user, date, space, peopleqnt);
					ReservationDao.insert(reservation);
				}
				else {
					JOptionPane.showMessageDialog(null, "Existe uma reserva agendada no mesmo dia!", "ERROR", 0);

				}
			}else {
				JOptionPane.showMessageDialog(null, "Espaço não existe!", "ERROR", 0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Associado não encontrado!", "ERROR", 0);
		}
	}
	
	public void edit(int id, long cpfUser, Date date, int spaceId, int peopleqnt) throws Exception {
		User user = this.user.getByCPF(cpfUser);
		Space space = this.space.getById(spaceId);
		if(user.getId() != 0) {
			if(space.getId() != 0) {
				if(!existReservationInTheSameDay(id, date, spaceId)) {
					Reservation reservation = new Reservation(id, user, date, space, peopleqnt);
					ReservationDao.update(reservation);
				}
				else {
					JOptionPane.showMessageDialog(null, "Existe uma reserva agendada no mesmo dia!", "ERROR", 0);

				}
			}else {
				JOptionPane.showMessageDialog(null, "Espaço não existe!", "ERROR", 0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Associado não encontrado!", "ERROR", 0);
		}
	}
	
	public void deleteById(int id) throws Exception {	
		if(!ReservationDao.getById(id).getDate().before(new Date())) {
			ReservationDao.deleteById(id);
		}else {
			throw new Exception("Erro ao persistir objeto.");
		}
	}
	
	private boolean existReservationInTheSameDay(int id, Date date, int spaceId) {
		Reservation reservation = ReservationDao.getByDateAndByPlace(id, date, spaceId);
		if(reservation.getId() != 0) {
			return true;
		}
		return false;
	}
	

}
