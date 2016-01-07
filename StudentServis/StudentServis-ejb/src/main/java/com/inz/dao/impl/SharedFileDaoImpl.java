package com.inz.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inz.dao.SharedFileDao;
import com.inz.model.File;
import com.inz.model.SharedFile;
import com.inz.model.User;

public class SharedFileDaoImpl implements SharedFileDao {

	@PersistenceContext(name="primary")
	private EntityManager em;
	
	@Override
	public List<SharedFile> getSharedFiles() {
		return em.createNamedQuery("SharedFile.findAll").getResultList();
	}

	@Override
	public SharedFile getSharedFile(int id) {
		return em.find(SharedFile.class, id);
	}

	@Override
	public void updateSharedFile(SharedFile file) {
		em.merge(file);
	}

	@Override
	public void deleteSharedFile(int file) {
		em.remove(getSharedFile(file));
	}

	@Override
	public void deleteSharedFile(SharedFile file) {
		em.remove(file);
	}

	@Override
	public void addSharedFile(int file_id, int user_id) {
		SharedFile sf = new SharedFile();
		sf.setSharedId(generateId());
		sf.setFile(em.find(File.class, file_id));
		sf.setUser(em.find(User.class, user_id));
		em.persist(sf);
	}

	@Override
	public void addSharedFile(SharedFile file) {
		em.persist(file);
	}

	private int generateId(){
		List<SharedFile> files = getSharedFiles();
		int id=0;
		List<Integer> ints = new LinkedList<Integer>();
		for(SharedFile f:files){
			ints.add(f.getSharedId());
		}
		int i=1;
		while(true){
			if(ints.contains(i)) i++;
			else{
				id=i;
				break;
			}
		}
		return id;
	}
}
