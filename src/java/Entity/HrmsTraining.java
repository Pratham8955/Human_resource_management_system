/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pratham
 */
@Entity
@Table(name = "hrms_training")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HrmsTraining.findAll", query = "SELECT h FROM HrmsTraining h"),
    @NamedQuery(name = "HrmsTraining.findByTimeDuration", query = "SELECT h FROM HrmsTraining h WHERE h.timeDuration = :timeDuration"),
    @NamedQuery(name = "HrmsTraining.findByDescription", query = "SELECT h FROM HrmsTraining h WHERE h.description = :description"),
    @NamedQuery(name = "HrmsTraining.findByTrainingId", query = "SELECT h FROM HrmsTraining h WHERE h.trainingId = :trainingId")})
public class HrmsTraining implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "time_duration")
    private String timeDuration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "training_id")
    private Integer trainingId;
    @JoinColumn(name = "t_id", referencedColumnName = "t_id")
    @ManyToOne(optional = false)
    private HrmsTraingType tId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Hrmsemployees userId;

    public HrmsTraining() {
    }

    public HrmsTraining(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public HrmsTraining(Integer trainingId, String description) {
        this.trainingId = trainingId;
        this.description = description;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public HrmsTraingType getTId() {
        return tId;
    }

    public void setTId(HrmsTraingType tId) {
        this.tId = tId;
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
        hash += (trainingId != null ? trainingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrmsTraining)) {
            return false;
        }
        HrmsTraining other = (HrmsTraining) object;
        if ((this.trainingId == null && other.trainingId != null) || (this.trainingId != null && !this.trainingId.equals(other.trainingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.HrmsTraining[ trainingId=" + trainingId + " ]";
    }
    
}
