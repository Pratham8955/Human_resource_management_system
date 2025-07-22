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
@Table(name = "hrms_traing_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HrmsTraingType.findAll", query = "SELECT h FROM HrmsTraingType h"),
    @NamedQuery(name = "HrmsTraingType.findByTId", query = "SELECT h FROM HrmsTraingType h WHERE h.tId = :tId"),
    @NamedQuery(name = "HrmsTraingType.findByTrainer", query = "SELECT h FROM HrmsTraingType h WHERE h.trainer = :trainer"),
    @NamedQuery(name = "HrmsTraingType.findByDescription", query = "SELECT h FROM HrmsTraingType h WHERE h.description = :description"),
    @NamedQuery(name = "HrmsTraingType.findByStatus", query = "SELECT h FROM HrmsTraingType h WHERE h.status = :status")})
public class HrmsTraingType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "t_id")
    private Integer tId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "trainer")
    private String trainer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tId")
    private Collection<HrmsTraining> hrmsTrainingCollection;

    public HrmsTraingType() {
    }

    public HrmsTraingType(Integer tId) {
        this.tId = tId;
    }

    public HrmsTraingType(Integer tId, String trainer, String description) {
        this.tId = tId;
        this.trainer = trainer;
        this.description = description;
    }

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<HrmsTraining> getHrmsTrainingCollection() {
        return hrmsTrainingCollection;
    }

    public void setHrmsTrainingCollection(Collection<HrmsTraining> hrmsTrainingCollection) {
        this.hrmsTrainingCollection = hrmsTrainingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tId != null ? tId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrmsTraingType)) {
            return false;
        }
        HrmsTraingType other = (HrmsTraingType) object;
        if ((this.tId == null && other.tId != null) || (this.tId != null && !this.tId.equals(other.tId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.HrmsTraingType[ tId=" + tId + " ]";
    }
    
}
