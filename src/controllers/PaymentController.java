package controllers;

import java.util.List;

import daos.PaymentDao;
import models.Payment;



public class PaymentController {
	
	public PaymentController() {
		super();
	}
	
	public List<Payment> getAll() throws Exception{
		return PaymentDao.get();
	}
	
	public Payment getById(int id) throws Exception {
		return PaymentDao.getById(id);
	}
	
	public Payment post(Payment payment) throws Exception {
		PaymentDao.insert(payment);
		return payment;
	}
	
	public List<Payment> post(Payment payment, List<Payment> list) throws Exception {
		PaymentDao.insert(payment);
		list.add(payment);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		PaymentDao.deleteById(id);
	}
}
