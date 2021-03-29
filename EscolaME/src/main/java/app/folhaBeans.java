/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import infra.JPAUtils;
import infra.Tbfolha;
import domain.folhaPagamento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author cassi_wh5ztk2
 */
@ManagedBean
@RequestScoped
public class folhaBeans {
    
    private double salario, inss, irrf, salarioliquido;
    private Tbfolha tbf = new Tbfolha();
    private folhaPagamento fp;
    
    
    public folhaBeans() {
    }
    
    
    public void calcularSalario(){
        fp = new folhaPagamento(getSalario());
        
        fp.calcularInss();
        fp.calcularIrrf();
        fp.calculaSalarioliquido();
        
        setInss(fp.getInss());
        setIrrf(fp.getIrrf());
        setSalarioliquido(fp.getSalarioliquido());
        
        cadastrar();
    }
    
    
    public void cadastrar(){
        EntityManager em = JPAUtils.getEntityManager();
        
        tbf.setSalarioBruto(fp.getSalario());
        tbf.setInss(fp.getInss());
        tbf.setIrrf(fp.getIrrf());
        tbf.setSalarioLiquido(fp.getSalarioliquido());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(tbf);
        et.commit();
        em.close();
        
        
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getInss() {
        return inss;
    }

    public void setInss(double inss) {
        this.inss = inss;
    }

    public double getIrrf() {
        return irrf;
    }

    public void setIrrf(double irrf) {
        this.irrf = irrf;
    }

    public double getSalarioliquido() {
        return salarioliquido;
    }

    public void setSalarioliquido(double salarioliquido) {
        this.salarioliquido = salarioliquido;
    }

    public folhaPagamento getFp() {
        return fp;
    }

    public void setFp(folhaPagamento fp) {
        this.fp = fp;
    }

    public Tbfolha getTbf() {
        return tbf;
    }

    public void setTbf(Tbfolha tbf) {
        this.tbf = tbf;
    }
    
    
}
