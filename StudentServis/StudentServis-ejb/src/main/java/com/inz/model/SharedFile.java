package com.inz.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shared_file database table.
 * 
 */
@Entity
@Table(name="shared_file")
@NamedQuery(name="SharedFile.findAll", query="SELECT s FROM SharedFile s")
public class SharedFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shared_id")
	private Integer sharedId;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="file_id")
	private File file;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="reveiver_id")
	private User user;

	public SharedFile() {
	}

	public Integer getSharedId() {
		return this.sharedId;
	}

	public void setSharedId(Integer sharedId) {
		this.sharedId = sharedId;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}