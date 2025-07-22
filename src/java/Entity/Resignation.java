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
@Table(name = "resignation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resignation.findAll", query = "SELECT r FROM Resignation r"),
    @NamedQuery(name = "Resignation.findByStatus", query = "SELECT r FROM Resignation r WHERE r.status = :status"),
    @NamedQuery(name = "Resignation.findByEmpFirstname", query = "SELECT r FROM Resignation r WHERE r.empFirstname = :empFirstname"),
    @NamedQuery(name = "Resignation.findByEmpLastname", query = "SELECT r FROM Resignation r WHERE r.empLastname = :empLastname"),
    @NamedQuery(name = "Resignation.findByDateOfResign", query = "SELECT r FROM Resignation r WHERE r.dateOfResign = :dateOfResign"),
    @NamedQuery(name = "Resignation.findByReason", query = "SELECT r FROM Resignation r WHERE r.reason = :reason"),
    @NamedQuery(name = "Resignation.findByFeedback", query = "SELECT r FROM Resignation r WHERE r.feedback = :feedback"),
    @NamedQuery(name = "Resignation.findByCompanyPropertyReturn", query = "SELECT r FROM Resignation r WHERE r.companyPropertyReturn = :companyPropertyReturn"),
    @NamedQuery(name = "Resignation.findByManager", query = "SELECT r FROM Resignation r WHERE r.manager = :manager"),
    @NamedQuery(name = "Resignation.findByResigId", query = "SELECT r FROM Resignation r WHERE r.resigId = :resigId")})
public class Resignation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "emp_firstname")
    private String empFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "emp_lastname")
    private String empLastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_resign")
    @Temporal(TemporalType.DATE)
    private Date dateOfResign;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "feedback")
    private String feedback;
    @Size(max = 50)
    @Column(name = "company_property_return")
    private String companyPropertyReturn;
    @Size(max = 50)
    @Column(name = "manager")
    private String manager;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resig_id")
    private Integer resigId;
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToOne(optional = false)
    private Hrmsemployees hrmsemployees;

    public Resignation() {
    }

    public Resignation(Integer resigId) {
        this.resigId = resigId;
    }

    public Resignation(Integer resigId, String empFirstname, String empLastname, Date dateOfResign, String reason, String feedback) {
        this.resigId = resigId;
        this.empFirstname = empFirstname;
        this.empLastname = empLastname;
        this.dateOfResign = dateOfResign;
        this.reason = reason;
        this.feedback = feedback;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    public Date getDateOfResign() {
        return dateOfResign;
    }

    public void setDateOfResign(Date dateOfResign) {
        this.dateOfResign = dateOfResign;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCompanyPropertyReturn() {
        return companyPropertyReturn;
    }

    public void setCompanyPropertyReturn(String companyPropertyReturn) {
        this.companyPropertyReturn = companyPropertyReturn;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getResigId() {
        return resigId;
    }

    public void setResigId(Integer resigId) {
        this.resigId = resigId;
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
        hash += (resigId != null ? resigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resignation)) {
            return false;
        }
        Resignation other = (Resignation) object;
        if ((this.resigId == null && other.resigId != null) || (this.resigId != null && !this.resigId.equals(other.resigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Resignation[ resigId=" + resigId + " ]";
    }
    
}
