package controllers;

import java.util.List;

import daos.PlanDao;
import models.Plan;



public class PlanController {
		
		public PlanController() {
			super();
		}
		
		public List<Plan> getAll() throws Exception{
			return PlanDao.get();
		}
		
		public Plan getById(int id) throws Exception {
			return PlanDao.getById(id);
		}
		
		/*public Plan post(Plan plan) throws Exception {
			PlanDao.insert(plan);
			return plan;
		}
		
		public List<Plan> post(Plan plan, List<Plan> list) throws Exception {
			PlanDao.insert(plan);
			list.add(plan);
			return list;
		}
		
		public void deleteById(int id) throws Exception {
			PlanDao.deleteById(id);
		}*/
	}
