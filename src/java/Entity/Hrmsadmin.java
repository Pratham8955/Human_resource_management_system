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
@Table(name = "hrmsadmin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hrmsadmin.findAll", query = "SELECT h FROM Hrmsadmin h"),
    @NamedQuery(name = "Hrmsadmin.findByUserId", query = "SELECT h FROM Hrmsadmin h WHERE h.userId = :userId"),
    @NamedQuery(name = "Hrmsadmin.findByAName", query = "SELECT h FROM Hrmsadmin h WHERE h.aName = :aName"),
    @NamedQuery(name = "Hrmsadmin.findByAPassword", query = "SELECT h FROM Hrmsadmin h WHERE h.aPassword = :aPassword")})
public class Hrmsadmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "a_name")
    private String aName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "a_password")
    private String aPassword;

    public Hrmsadmin() {
    }

    public Hrmsadmin(Integer userId) {
        this.userId = userId;
    }

    public Hrmsadmin(Integer userId, String aName, String aPassword) {
        this.userId = userId;
        this.aName = aName;
        this.aPassword = aPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getAPassword() {
        return aPassword;
    }

    public void setAPassword(String aPassword) {
        this.aPassword = aPassword;
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
        if (!(object instanceof Hrmsadmin)) {
            return false;
        }
        Hrmsadmin other = (Hrmsadmin) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Hrmsadmin[ userId=" + userId + " ]";
    }
    
}
