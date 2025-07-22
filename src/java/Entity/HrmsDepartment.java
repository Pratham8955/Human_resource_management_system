/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pratham
 */
@Entity
@Table(name = "hrms_department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HrmsDepartment.findAll", query = "SELECT h FROM HrmsDepartment h"),
    @NamedQuery(name = "HrmsDepartment.findByDepId", query = "SELECT h FROM HrmsDepartment h WHERE h.depId = :depId"),
    @NamedQuery(name = "HrmsDepartment.findByDepName", query = "SELECT h FROM HrmsDepartment h WHERE h.depName = :depName"),
    @NamedQuery(name = "HrmsDepartment.findByManagerName", query = "SELECT h FROM HrmsDepartment h WHERE h.managerName = :managerName")})
public class HrmsDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dep_id")
    private Integer depId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dep_name")
    private String depName;
    @Size(max = 255)
    @Column(name = "manager_name")
    private String managerName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depId")
    private Collection<Hrmsemployees> hrmsemployeesCollection;

    public HrmsDepartment() {
    }

    public HrmsDepartment(Integer depId) {
        this.depId = depId;
    }

    public HrmsDepartment(Integer depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @XmlTransient
    public Collection<Hrmsemployees> getHrmsemployeesCollection() {
        return hrmsemployeesCollection;
    }

    public void setHrmsemployeesCollection(Collection<Hrmsemployees> hrmsemployeesCollection) {
        this.hrmsemployeesCollection = hrmsemployeesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrmsDepartment)) {
            return false;
        }
        HrmsDepartment other = (HrmsDepartment) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.HrmsDepartment[ depId=" + depId + " ]";
    }
    
}
