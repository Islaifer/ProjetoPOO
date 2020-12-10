package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import daos.SubscriptionDao;
import models.Subscription;
import models.SubscriptionStatus;
import models.User;

public class SubscriptionService {
	
	private UserService userService;

	public SubscriptionService() {
		this.userService = new UserService();
	}
	
	public List<Subscription> getAll() throws Exception{
		return SubscriptionDao.getAll();
	}
	
	public List<Subscription> getByUserId(int id) throws Exception{
		return SubscriptionDao.getByUserId(id);
	}
	
	public Subscription getById(int id) throws Exception {
		return SubscriptionDao.getById(id);
	}
	
	public List<Subscription> filterByStatus(int statusId) throws Exception {
		return SubscriptionDao.filterByStatus(statusId);
	}
	public void post() throws Exception {
		SubscriptionStatus status = new SubscriptionStatus(2, "Pendente");
		
		List<User> users = this.userService.getAll();

		for(User user : users){
			if(!checkIfUserHasSubscriptionInNextMonth(user.getId())) {
				Subscription subscription = new Subscription(status, user);
				SubscriptionDao.insert(subscription);
			}
        }
	}
	
	private boolean checkIfUserHasSubscriptionInNextMonth(int userId) {
		Date currentDate = new Date();
        Calendar withFirstDayOfMonthCalendar = Calendar.getInstance();
        Calendar withLastDayOfMonthCalendar = Calendar.getInstance();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        if(currentCalendar.get(Calendar.MONTH) + 1 == 12) {
            withFirstDayOfMonthCalendar.set(currentCalendar.get(Calendar.YEAR) + 1, 0, 1);
            withLastDayOfMonthCalendar.setTime(withFirstDayOfMonthCalendar.getTime());
            withLastDayOfMonthCalendar.set(withLastDayOfMonthCalendar.get(Calendar.YEAR), withLastDayOfMonthCalendar.get(Calendar.MONTH), withLastDayOfMonthCalendar.getActualMaximum(Calendar.DATE));
        } else {
        	  withFirstDayOfMonthCalendar.set(currentCalendar.get(Calendar.YEAR),currentCalendar.get(Calendar.MONTH) + 1, 1);
              withLastDayOfMonthCalendar.setTime(withFirstDayOfMonthCalendar.getTime());
              withLastDayOfMonthCalendar.set(withLastDayOfMonthCalendar.get(Calendar.YEAR), withLastDayOfMonthCalendar.get(Calendar.MONTH), withLastDayOfMonthCalendar.getActualMaximum(Calendar.DATE));
        }
        try {
			Subscription subscription = SubscriptionDao.checkIfUserHasSubscriptionOnMonth(withFirstDayOfMonthCalendar.getTime(), withLastDayOfMonthCalendar.getTime(), userId);
			subscription.getId();
			if(subscription.getId() != 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return true;
		}
	}
	public void deleteById(int id) throws Exception {
		SubscriptionDao.deleteById(id);
	}

}
