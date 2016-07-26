package com.applicationbot.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;



@Entity
@Table(name = "failedDetails", catalog = "autoscript")
public class FailedDetails implements java.io.Serializable {
		// TODO Auto-generated method stub
		

		private static final long serialVersionUID = 1L;

		private String JobId;
		private Date created_at = new Date();
		private Date updated_at = new Date();
		


		public FailedDetails() {
		}

		public FailedDetails(String JobID) {
			this.JobId = JobID;
		}

		@Id
		//@GeneratedValue(strategy = IDENTITY)
		@Column(name = "Job_Url", unique = true, nullable = false)
		public String getJobId() {
			return this.JobId;
		}

		public void setJobId(String JobId) {
			this.JobId = JobId;
		}
		
		@Column(name = "created_at", nullable = false)
		public Date getCreated_at() {
			return this.created_at;
		}
		
		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public void setUpdated_at(Date updated_at) {
			this.updated_at = updated_at;
		}

		@Column(name = "updated_at", nullable = false)
		public Date getUpdated_at() {
			return this.updated_at;
		}

		@PreUpdate
		public void setLastUpdate_at() {  this.updated_at = new Date(); }

}
