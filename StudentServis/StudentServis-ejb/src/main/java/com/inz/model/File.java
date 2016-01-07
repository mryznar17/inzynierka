package com.inz.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the file database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="File.findAll", query="SELECT f FROM File f"),
	@NamedQuery(name="File.findByName", query="SELECT f FROM File as f where f.fileName=:fileName")
})
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="file_id")
	private Integer fileId;

	@Column(name="file_binary")
	private byte[] fileBinary;

	@Column(name="file_ext")
	private String fileExt;

	@Column(name="file_name")
	private String fileName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to SharedFile
	@OneToMany(mappedBy="file")
	private List<SharedFile> sharedFiles;

	public File() {
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public byte[] getFileBinary() {
		return this.fileBinary;
	}

	public void setFileBinary(byte[] fileBinary) {
		this.fileBinary = fileBinary;
	}

	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SharedFile> getSharedFiles() {
		return this.sharedFiles;
	}

	public void setSharedFiles(List<SharedFile> sharedFiles) {
		this.sharedFiles = sharedFiles;
	}

	public SharedFile addSharedFile(SharedFile sharedFile) {
		getSharedFiles().add(sharedFile);
		sharedFile.setFile(this);

		return sharedFile;
	}

	public SharedFile removeSharedFile(SharedFile sharedFile) {
		getSharedFiles().remove(sharedFile);
		sharedFile.setFile(null);

		return sharedFile;
	}

}