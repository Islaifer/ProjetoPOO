package services;

import java.util.List;

import daos.SpaceDao;
import models.Space;

public class SpaceService {
	public SpaceService() {
		super();
	}
	
	public List<Space> getAll() throws Exception{
		return SpaceDao.get();
	}
	
	public Space getById(int id) throws Exception {
		return SpaceDao.getById(id);
	}
	
	public Space post(String name, int capacity) throws Exception {
		Space space = new Space(name, capacity);
		SpaceDao.insert(space);
		return space;
	}
	
	public List<Space> post(String name, int capacity, List<Space> list) throws Exception {
		Space space = new Space(name, capacity);
		SpaceDao.insert(space);
		list.add(space);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		SpaceDao.deleteById(id);
	}
}
