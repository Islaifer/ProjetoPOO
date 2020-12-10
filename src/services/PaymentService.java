package services;

import java.util.Date;
import java.util.List;

import daos.PaymentDao;
import daos.SubscriptionDao;
import models.Payment;
import models.Subscription;
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

	public Payment post(Subscription subscription) throws Exception {
		Payment payment = new Payment(subscription);
		SubscriptionDao.updateStatus(subscription, 3);
		return payment;

	}

	public void deleteById(int id) throws Exception {
		PaymentDao.deleteById(id);
	}
}
