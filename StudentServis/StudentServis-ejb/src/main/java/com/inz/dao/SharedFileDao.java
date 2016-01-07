package com.inz.dao;

import java.util.List;

import com.inz.model.File;
import com.inz.model.SharedFile;

public interface SharedFileDao {
	List<SharedFile> getSharedFiles();
	SharedFile getSharedFile(int id);
	void updateSharedFile(SharedFile file);
	void deleteSharedFile(int file);
	void deleteSharedFile(SharedFile file);
	void addSharedFile(int file_id, int user_id);
	void addSharedFile(SharedFile file);
}
