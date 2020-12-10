package services;

import java.util.List;

import javax.swing.JOptionPane;

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

	public void post(Subscription subscription) throws Exception {
		Payment payment = new Payment(subscription);
		if(subscription.getStatus().getId() != 3) {
			PaymentDao.insert(payment);
			SubscriptionDao.updateStatus(subscription, 3);
		} else {
			JOptionPane.showMessageDialog(null, "Mensalidade já paga!", "ERROR", 0);
		}
		
	}

	public void deleteById(int id) throws Exception {
		PaymentDao.deleteById(id);
	}
}
