package com.project.todolist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "work")
@EntityListeners(AuditingEntityListener.class)
public class Work implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WORK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @NotBlank
    @Column(name="WORK_NAME")
    private String workName;

    @Column(nullable = false, updatable = true, name="STARTING_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startingDate;

    @Column(nullable = false, updatable = true, name="ENDING_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endingDate;
    
    @Column(name="STATUS")
    private int status;
    
	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}