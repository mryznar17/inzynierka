package com.inz.dao;

import java.util.List;

import com.inz.model.File;

public interface FileDao {
	List<File> getFiles();
	File getFile(int id);
	File getFileByName(String name);
	void updateFile(File file);
	void deleteFile(int file);
	void deleteFile(File file);
	void addFile(byte[] fileBinary, String name, int user_id);
	void addFile(File file);
}
