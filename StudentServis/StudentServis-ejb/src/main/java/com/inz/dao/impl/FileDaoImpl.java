package com.inz.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inz.dao.FileDao;
import com.inz.model.File;
import com.inz.model.User;

public class FileDaoImpl implements FileDao {
	
	@PersistenceContext(name="primary")
	private EntityManager em;
	
	@Override
	public List<File> getFiles() {
		return em.createNamedQuery("File.findAll").getResultList();
	}

	@Override
	public File getFile(int id) {
		return em.find(File.class, id);
	}

	@Override
	public File getFileByName(String name) {
		return (File) em.createNamedQuery("File.findByName").setParameter("fileName", name).getSingleResult();
	}

	@Override
	public void updateFile(File file) {
		em.merge(file);
	}

	@Override
	public void deleteFile(int file) {
		File f = getFile(file);
		em.remove(f);
	}

	@Override
	public void deleteFile(File file) {
		em.remove(file);
	}

	@Override
	public void addFile(byte[] fileBinary, String name, int user_id) {
		File f = new File();
		f.setFileId(generateId());
		f.setFileBinary(fileBinary);
		f.setFileName(name);
		f.setUser(em.find(User.class, user_id));
		em.persist(f);
	}

	@Override
	public void addFile(File file) {
		if(file.getFileId().equals(null)) file.setFileId(generateId());
		em.persist(file);
	}

	private int generateId(){
		List<File> files = getFiles();
		int id=0;
		List<Integer> ints = new LinkedList<Integer>();
		for(File f:files){
			ints.add(f.getFileId());
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
