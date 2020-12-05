package controllers;

import java.util.List;

import daos.SpaceDao;
import models.Space;


public class SpaceController {
	public SpaceController() {
		super();
	}
	
	public List<Space> getAll() throws Exception{
		return SpaceDao.get();
	}
	
	public Space getById(int id) throws Exception {
		return SpaceDao.getById(id);
	}
	
	/*public Space post(Space space) throws Exception {
		SpaceDao.insert(space);
		return space;
	}
	
	public List<Space> post(Space space, List<Space> list) throws Exception {
		SpaceDao.insert(space);
		list.add(space);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		SpaceDao.deleteById(id);
	}*/
}
