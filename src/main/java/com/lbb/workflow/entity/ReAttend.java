package com.lbb.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class ReAttend implements Serializable {
    private Long id;

    private Long attendId;

    private String reAttendStarter;

    private Date reAttendDate;

    private Date reAttendEve;

    private Date reAttendMor;

    private String currentHandler;

    private Byte status;

    private String comments;

    private String taskId;

    private String approveFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public String getReAttendStarter() {
        return reAttendStarter;
    }

    public void setReAttendStarter(String reAttendStarter) {
        this.reAttendStarter = reAttendStarter == null ? null : reAttendStarter.trim();
    }

    public Date getReAttendDate() {
        return reAttendDate;
    }

    public void setReAttendDate(Date reAttendDate) {
        this.reAttendDate = reAttendDate;
    }

    public Date getReAttendEve() {
        return reAttendEve;
    }

    public void setReAttendEve(Date reAttendEve) {
        this.reAttendEve = reAttendEve;
    }

    public Date getReAttendMor() {
        return reAttendMor;
    }

    public void setReAttendMor(Date reAttendMor) {
        this.reAttendMor = reAttendMor;
    }

    public String getCurrentHandler() {
        return currentHandler;
    }

    public void setCurrentHandler(String currentHandler) {
        this.currentHandler = currentHandler == null ? null : currentHandler.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }
}