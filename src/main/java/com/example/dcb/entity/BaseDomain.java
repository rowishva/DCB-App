package com.example.dcb.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "create_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createTime;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateTime;

	@Column(name = "created_by_user", nullable = false)
	@CreatedBy
	private String createdByUser = "DUMMY_USER";

	@Column(name = "updated_by_user")
	@LastModifiedBy
	private String updatedByUser = "DUMMY_USER";

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

	@PrePersist
	public void onPrePersist() {
		this.createTime = new Date();
		this.createdByUser = "SYSTEM";
	}

	@PreUpdate
	public void onPreUpdate() {
		this.updateTime = new Date();
		this.updatedByUser = "SYSTEM";
	}

}
