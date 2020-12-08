package services;

import java.util.Date;
import java.util.List;

import daos.PaymentDao;
import models.Payment;
import models.User;

public class PaymentService {
	private User user;

	public PaymentService() {
		this.user = new User();
	}

	public List<Payment> getAll() throws Exception {
		return PaymentDao.get();
	}

	public Payment getById(int id) throws Exception {
		return PaymentDao.getById(id);
	}

	public Payment post(Date date, User user, double price) throws Exception {
		Payment payment = new Payment ();
		PaymentDao.insert(payment);
		return payment;

	}

	public void deleteById(int id) throws Exception {
		PaymentDao.deleteById(id);
	}
}
