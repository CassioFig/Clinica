/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cassi_wh5ztk2
 */
@Entity
@Table(name = "tbfolha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbfolha.findAll", query = "SELECT t FROM Tbfolha t"),
    @NamedQuery(name = "Tbfolha.findByIdFolha", query = "SELECT t FROM Tbfolha t WHERE t.idFolha = :idFolha"),
    @NamedQuery(name = "Tbfolha.findBySalarioBruto", query = "SELECT t FROM Tbfolha t WHERE t.salarioBruto = :salarioBruto"),
    @NamedQuery(name = "Tbfolha.findByInss", query = "SELECT t FROM Tbfolha t WHERE t.inss = :inss"),
    @NamedQuery(name = "Tbfolha.findByIrrf", query = "SELECT t FROM Tbfolha t WHERE t.irrf = :irrf"),
    @NamedQuery(name = "Tbfolha.findBySalarioLiquido", query = "SELECT t FROM Tbfolha t WHERE t.salarioLiquido = :salarioLiquido")})
public class Tbfolha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFolha")
    private Integer idFolha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salarioBruto")
    private Double salarioBruto;
    @Column(name = "inss")
    private Double inss;
    @Column(name = "irrf")
    private Double irrf;
    @Column(name = "salarioLiquido")
    private Double salarioLiquido;

    public Tbfolha() {
    }

    public Tbfolha(Integer idFolha) {
        this.idFolha = idFolha;
    }

    public Integer getIdFolha() {
        return idFolha;
    }

    public void setIdFolha(Integer idFolha) {
        this.idFolha = idFolha;
    }

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Double getInss() {
        return inss;
    }

    public void setInss(Double inss) {
        this.inss = inss;
    }

    public Double getIrrf() {
        return irrf;
    }

    public void setIrrf(Double irrf) {
        this.irrf = irrf;
    }

    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFolha != null ? idFolha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbfolha)) {
            return false;
        }
        Tbfolha other = (Tbfolha) object;
        if ((this.idFolha == null && other.idFolha != null) || (this.idFolha != null && !this.idFolha.equals(other.idFolha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "infra.Tbfolha[ idFolha=" + idFolha + " ]";
    }
    
}
