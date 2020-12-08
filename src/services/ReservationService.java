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
	
	public Reservation post(int rgUser, Date date, String spaceName, int peopleqnt) throws Exception {
		User user = this.user.getById(rgUser);
		Space space = this.space.getById(0);
		if(user.getId() != 0) {
			if(space.getId() != 0) {
				Reservation reservation = new Reservation(0, user, date, space, peopleqnt);
				ReservationDao.insert(reservation);
				return reservation;
			}else {
				JOptionPane.showMessageDialog(null, "Espaço não existe!", "ERROR", 0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Associado não encontrado!", "ERROR", 0);
		}
		return null;
	}
	
	public void deleteById(int id) throws Exception {
		ReservationDao.deleteById(id);
	}

}
