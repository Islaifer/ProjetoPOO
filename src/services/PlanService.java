package services;

import java.util.List;

import daos.PlanDao;
import models.Plan;


public class PlanService {
	public PlanService() {
		
	}
	public List<Plan> getAll() throws Exception{
		return PlanDao.get();
	}
	
	public Plan getById(int id) throws Exception {
		return PlanDao.getById(id);
	}
	
}
