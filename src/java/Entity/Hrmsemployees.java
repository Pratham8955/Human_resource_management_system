/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pratham
 */
@Entity
@Table(name = "hrmsemployees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hrmsemployees.findAll", query = "SELECT h FROM Hrmsemployees h"),
    @NamedQuery(name = "Hrmsemployees.findByUserId", query = "SELECT h FROM Hrmsemployees h WHERE h.userId = :userId"),
    @NamedQuery(name = "Hrmsemployees.findByEmpFirstname", query = "SELECT h FROM Hrmsemployees h WHERE h.empFirstname = :empFirstname"),
    @NamedQuery(name = "Hrmsemployees.findByEmpLastname", query = "SELECT h FROM Hrmsemployees h WHERE h.empLastname = :empLastname"),
    @NamedQuery(name = "Hrmsemployees.findByEmpEmail", query = "SELECT h FROM Hrmsemployees h WHERE h.empEmail = :empEmail"),
    @NamedQuery(name = "Hrmsemployees.findByEmpPhone", query = "SELECT h FROM Hrmsemployees h WHERE h.empPhone = :empPhone"),
    @NamedQuery(name = "Hrmsemployees.findByEmpAddress", query = "SELECT h FROM Hrmsemployees h WHERE h.empAddress = :empAddress"),
    @NamedQuery(name = "Hrmsemployees.findByEmpGender", query = "SELECT h FROM Hrmsemployees h WHERE h.empGender = :empGender"),
    @NamedQuery(name = "Hrmsemployees.findByEmpPassword", query = "SELECT h FROM Hrmsemployees h WHERE h.empPassword = :empPassword"),
    @NamedQuery(name = "Hrmsemployees.findByHireDate", query = "SELECT h FROM Hrmsemployees h WHERE h.hireDate = :hireDate"),
    @NamedQuery(name = "Hrmsemployees.findBySalary", query = "SELECT h FROM Hrmsemployees h WHERE h.salary = :salary"),
    @NamedQuery(name = "Hrmsemployees.findByManagerName", query = "SELECT h FROM Hrmsemployees h WHERE h.managerName = :managerName")})
public class Hrmsemployees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
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
    @Size(min = 1, max = 50)
    @Column(name = "emp_email")
    private String empEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "emp_phone")
    private String empPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "emp_address")
    private String empAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "emp_gender")
    private String empGender;
    @Size(max = 500)
    @Column(name = "emp_password")
    private String empPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private int salary;
    @Size(max = 45)
    @Column(name = "manager_name")
    private String managerName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<HrmsTraining> hrmsTrainingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hrmsemployees")
    private Collection<Applicationsleave> applicationsleaveCollection;
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    @ManyToOne
    private GroupMaster groupId;
    @JoinColumn(name = "dep_id", referencedColumnName = "dep_id")
    @ManyToOne(optional = false)
    private HrmsDepartment depId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hrmsemployees")
    private Collection<Resignation> resignationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<TaskSheet> taskSheetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hrmsemployees")
    private Collection<Attendance> attendanceCollection;

    public Hrmsemployees() {
    }

    public Hrmsemployees(Integer userId) {
        this.userId = userId;
    }

    public Hrmsemployees(Integer userId, String empFirstname, String empLastname, String empEmail, String empPhone, String empAddress, String empGender, Date hireDate, int salary) {
        this.userId = userId;
        this.empFirstname = empFirstname;
        this.empLastname = empLastname;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
        this.empGender = empGender;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @XmlTransient
    public Collection<HrmsTraining> getHrmsTrainingCollection() {
        return hrmsTrainingCollection;
    }

    public void setHrmsTrainingCollection(Collection<HrmsTraining> hrmsTrainingCollection) {
        this.hrmsTrainingCollection = hrmsTrainingCollection;
    }

    @XmlTransient
    public Collection<Applicationsleave> getApplicationsleaveCollection() {
        return applicationsleaveCollection;
    }

    public void setApplicationsleaveCollection(Collection<Applicationsleave> applicationsleaveCollection) {
        this.applicationsleaveCollection = applicationsleaveCollection;
    }

    public GroupMaster getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupMaster groupId) {
        this.groupId = groupId;
    }

    public HrmsDepartment getDepId() {
        return depId;
    }

    public void setDepId(HrmsDepartment depId) {
        this.depId = depId;
    }

    @XmlTransient
    public Collection<Resignation> getResignationCollection() {
        return resignationCollection;
    }

    public void setResignationCollection(Collection<Resignation> resignationCollection) {
        this.resignationCollection = resignationCollection;
    }

    @XmlTransient
    public Collection<TaskSheet> getTaskSheetCollection() {
        return taskSheetCollection;
    }

    public void setTaskSheetCollection(Collection<TaskSheet> taskSheetCollection) {
        this.taskSheetCollection = taskSheetCollection;
    }

    @XmlTransient
    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hrmsemployees)) {
            return false;
        }
        Hrmsemployees other = (Hrmsemployees) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Hrmsemployees[ userId=" + userId + " ]";
    }
    
}
