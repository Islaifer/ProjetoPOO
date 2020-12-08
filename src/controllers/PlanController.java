package controllers;

import java.util.List;
import models.Plan;
import services.PlanService;



public class PlanController {
		private PlanService planService;
		
		public PlanController() {
			planService = new PlanService();
		}
		
		public List<Plan> getAll() throws Exception{
			return this.planService.getAll();
		}
		
		public Plan getById(int id) throws Exception {
			return this.planService.getById(id);
		}
		
	}
