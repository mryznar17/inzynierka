package com.inz.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "fileMan", eager = true)
@SessionScoped
public class FileManager {

	private byte[] fileBinary;
	private String fileName;
	
	public FileManager() {
	}

	public byte[] getFileBinary() {
		return fileBinary;
	}

	public void setFileBinary(byte[] fileBinary) {
		this.fileBinary = fileBinary;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String addFile(){
		return "index";
	}
}
