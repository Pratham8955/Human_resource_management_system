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
import javax.persistence.JoinColumns;
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
@Table(name = "applicationsleave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicationsleave.findAll", query = "SELECT a FROM Applicationsleave a"),
    @NamedQuery(name = "Applicationsleave.findByStatus", query = "SELECT a FROM Applicationsleave a WHERE a.status = :status"),
    @NamedQuery(name = "Applicationsleave.findByApplicationId", query = "SELECT a FROM Applicationsleave a WHERE a.applicationId = :applicationId"),
    @NamedQuery(name = "Applicationsleave.findByRequestDate", query = "SELECT a FROM Applicationsleave a WHERE a.requestDate = :requestDate"),
    @NamedQuery(name = "Applicationsleave.findByLeaveType", query = "SELECT a FROM Applicationsleave a WHERE a.leaveType = :leaveType"),
    @NamedQuery(name = "Applicationsleave.findByLeaveDuration", query = "SELECT a FROM Applicationsleave a WHERE a.leaveDuration = :leaveDuration"),
    @NamedQuery(name = "Applicationsleave.findByFromDate", query = "SELECT a FROM Applicationsleave a WHERE a.fromDate = :fromDate"),
    @NamedQuery(name = "Applicationsleave.findByToDate", query = "SELECT a FROM Applicationsleave a WHERE a.toDate = :toDate"),
    @NamedQuery(name = "Applicationsleave.findByReason", query = "SELECT a FROM Applicationsleave a WHERE a.reason = :reason"),
    @NamedQuery(name = "Applicationsleave.findByManagerName", query = "SELECT a FROM Applicationsleave a WHERE a.managerName = :managerName")})
public class Applicationsleave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "application_id")
    private Integer applicationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "leave_type")
    private String leaveType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "leave_duration")
    private String leaveDuration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "reason")
    private String reason;
    @Size(max = 25)
    @Column(name = "manager_name")
    private String managerName;
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToOne(optional = false)
    private Hrmsemployees hrmsemployees;

    public Applicationsleave() {
    }

    public Applicationsleave(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Applicationsleave(Integer applicationId, Date requestDate, String leaveType, String leaveDuration, Date fromDate, Date toDate, String reason) {
        this.applicationId = applicationId;
        this.requestDate = requestDate;
        this.leaveType = leaveType;
        this.leaveDuration = leaveDuration;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(String leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Hrmsemployees getHrmsemployees() {
        return hrmsemployees;
    }

    public void setHrmsemployees(Hrmsemployees hrmsemployees) {
        this.hrmsemployees = hrmsemployees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicationsleave)) {
            return false;
        }
        Applicationsleave other = (Applicationsleave) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Applicationsleave[ applicationId=" + applicationId + " ]";
    }
    
}
