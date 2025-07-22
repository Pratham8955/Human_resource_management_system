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
@Table(name = "attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a"),
    @NamedQuery(name = "Attendance.findByDeptName", query = "SELECT a FROM Attendance a WHERE a.deptName = :deptName"),
    @NamedQuery(name = "Attendance.findByTimeIn", query = "SELECT a FROM Attendance a WHERE a.timeIn = :timeIn"),
    @NamedQuery(name = "Attendance.findByTimeOut", query = "SELECT a FROM Attendance a WHERE a.timeOut = :timeOut"),
    @NamedQuery(name = "Attendance.findByPunchDate", query = "SELECT a FROM Attendance a WHERE a.punchDate = :punchDate"),
    @NamedQuery(name = "Attendance.findByUserId", query = "SELECT a FROM Attendance a WHERE a.hrmsemployees.userId = :userId"),
    @NamedQuery(name = "Attendance.findByAId", query = "SELECT a FROM Attendance a WHERE a.aId = :aId")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dept_name")
    private String deptName;
    @Size(max = 50)
    @Column(name = "time_in")
    private String timeIn;
    @Column(name = "time_out")
    @Temporal(TemporalType.TIME)
    private Date timeOut;
    @Column(name = "punch_date")
    @Temporal(TemporalType.DATE)
    private Date punchDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "a_id")
    private Integer aId;
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToOne(optional = false)
    private Hrmsemployees hrmsemployees;

    public Attendance() {
    }

    public Attendance(Integer aId) {
        this.aId = aId;
    }

    public Attendance(Integer aId, String deptName) {
        this.aId = aId;
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
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
        hash += (aId != null ? aId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Attendance[ aId=" + aId + " ]";
    }
    
}
