package controllers;

import java.util.Date;
import java.util.List;

import models.Space;
import services.SpaceService;


public class SpaceController {
	private SpaceService spaceService;
	
	public SpaceController() {
		this.spaceService = new SpaceService();
	}
	
	public List<Space> getAll() throws Exception{
		return this.spaceService.getAll();
	}
	
	public Space getById(int id) throws Exception {
		return this.spaceService.getById(id);
	}
	
	public Space post(String name, int capacity) throws Exception {
		return this.spaceService.post(name, capacity);
	}
	
	public List<Space> post(String name, int capacity, List<Space> list) throws Exception {
		return this.spaceService.post(name, capacity, list);
	}
	
	public void deleteById(int id) throws Exception {
		this.spaceService.deleteById(id);
	}
	
	public boolean disp(int id, Date date) throws Exception {
		return this.spaceService.disp(id, date);
	}
}
