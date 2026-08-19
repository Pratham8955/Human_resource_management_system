/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pratham
 */
@Entity
@Table(name = "task_sheet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskSheet.findAll", query = "SELECT t FROM TaskSheet t"),
    @NamedQuery(name = "TaskSheet.findByEmpName", query = "SELECT t FROM TaskSheet t WHERE t.empName = :empName"),
    @NamedQuery(name = "TaskSheet.findByEntryDate", query = "SELECT t FROM TaskSheet t WHERE t.entryDate = :entryDate"),
    @NamedQuery(name = "TaskSheet.findByTask", query = "SELECT t FROM TaskSheet t WHERE t.task = :task"),
    @NamedQuery(name = "TaskSheet.findByActionTime", query = "SELECT t FROM TaskSheet t WHERE t.actionTime = :actionTime"),
    @NamedQuery(name = "TaskSheet.findByStatus", query = "SELECT t FROM TaskSheet t WHERE t.status = :status"),
    @NamedQuery(name = "TaskSheet.findByTaskId", query = "SELECT t FROM TaskSheet t WHERE t.taskId = :taskId")})
public class TaskSheet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "emp_name")
    private String empName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "task")
    private String task;
    @Size(max = 50)
    @Column(name = "action_time")
    private String actionTime;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Hrmsemployees userId;

    public TaskSheet() {
    }

    public TaskSheet(Integer taskId) {
        this.taskId = taskId;
    }

    public TaskSheet(Integer taskId, Date entryDate, String task) {
        this.taskId = taskId;
        this.entryDate = entryDate;
        this.task = task;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Hrmsemployees getUserId() {
        return userId;
    }

    public void setUserId(Hrmsemployees userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskSheet)) {
            return false;
        }
        TaskSheet other = (TaskSheet) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TaskSheet[ taskId=" + taskId + " ]";
    }
    
}
