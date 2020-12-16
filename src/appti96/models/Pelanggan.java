/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appti96.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "pelanggan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelanggan.findAll", query = "SELECT p FROM Pelanggan p")
    , @NamedQuery(name = "Pelanggan.findByKodepelanggan", query = "SELECT p FROM Pelanggan p WHERE p.kodepelanggan = :kodepelanggan")
    , @NamedQuery(name = "Pelanggan.findByNamapelanggan", query = "SELECT p FROM Pelanggan p WHERE p.namapelanggan = :namapelanggan")
    , @NamedQuery(name = "Pelanggan.findByEmail", query = "SELECT p FROM Pelanggan p WHERE p.email = :email")})
public class Pelanggan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kodepelanggan")
    private String kodepelanggan;
    @Basic(optional = false)
    @Column(name = "namapelanggan")
    private String namapelanggan;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    public Pelanggan() {
    }

    public Pelanggan(String kodepelanggan) {
        this.kodepelanggan = kodepelanggan;
    }

    public Pelanggan(String kodepelanggan, String namapelanggan, String email) {
        this.kodepelanggan = kodepelanggan;
        this.namapelanggan = namapelanggan;
        this.email = email;
    }

    public String getKodepelanggan() {
        return kodepelanggan;
    }

    public void setKodepelanggan(String kodepelanggan) {
        this.kodepelanggan = kodepelanggan;
    }

    public String getNamapelanggan() {
        return namapelanggan;
    }

    public void setNamapelanggan(String namapelanggan) {
        this.namapelanggan = namapelanggan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodepelanggan != null ? kodepelanggan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelanggan)) {
            return false;
        }
        Pelanggan other = (Pelanggan) object;
        if ((this.kodepelanggan == null && other.kodepelanggan != null) || (this.kodepelanggan != null && !this.kodepelanggan.equals(other.kodepelanggan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appti96.models.Pelanggan[ kodepelanggan=" + kodepelanggan + " ]";
    }
    
}
