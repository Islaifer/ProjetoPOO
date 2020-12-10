package controllers;

import java.util.List;

import models.Payment;
import models.Subscription;
import services.PaymentService;



public class PaymentController {
	private PaymentService paymentService;
	public PaymentController() {
		paymentService = new PaymentService();
	}
	
	public List<Payment> getAll() throws Exception{
		return this.paymentService.getAll();
	}
	
	public List<Payment> getByUserId(int id) throws Exception{
		return this.paymentService.getByUserId(id);
	}
	
	public Payment getById(int id) throws Exception {
		return paymentService.getById(id);
	}
	
	public void post(Subscription subscription) throws Exception {
		this.paymentService.post(subscription);
	}
	
		
	public void deleteById(int id) throws Exception {
		this.paymentService.deleteById(id);
	}
}
