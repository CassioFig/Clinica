/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import domain.notasRN;
import infra.Notas;
import infra.JPAUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author cassi_wh5ztk2
 */
@ManagedBean
@RequestScoped
public class NotasBeans {
    
    private int id;
    private double nota1, nota2, nota3, nota4, notaFinal;
    private String status;
    private List<Notas> notases = new ArrayList<Notas>();
    
    private notasRN nrn;
    private Notas ndb = new Notas();
    public NotasBeans() {
    }
    
    public void selecionar(Notas ndb){
        this.ndb = ndb;
    }
    
    
    public void calculaNotaFinal(){
        nrn = new notasRN(getNota1(), getNota2(), getNota3(), getNota4());
        
        nrn.calcularMedia();
        
        setNotaFinal(nrn.getMedia());
        situacao();
        cadastrarNota();
        
    }
    
    public void atualizarNota(){
        EntityManager em = JPAUtils.getEntityManager();
        
        ndb.setNota1(ndb.getNota1());
        ndb.setNota2(ndb.getNota2());
        ndb.setNota3(ndb.getNota3());
        ndb.setNota4(ndb.getNota4());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(ndb);
        et.commit();
        em.close();
        
        atualizarNotaFinal();
        
    }
    
    public void atualizarNotaFinal(){
        nrn = new notasRN(ndb.getNota1(), ndb.getNota2(), ndb.getNota3(), ndb.getNota4());
        
        nrn.calcularMedia();
        
        setNotaFinal(nrn.getMedia());
        situacao();
        
    }
    
    public void cadastrarNota(){
        EntityManager em = JPAUtils.getEntityManager();
        
        ndb.setNota1(nrn.getNota1());
        ndb.setNota2(nrn.getNota2());
        ndb.setNota3(nrn.getNota3());
        ndb.setNota4(nrn.getNota4());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(ndb);
        et.commit();
        em.close();
    }
    
    public void situacao(){
        if (notaFinal >= 6) {
            this.status = "Aprovado";
        }else if(notaFinal < 6 && notaFinal >= 4){
            this.status = "Prova Final";
        }else if(notaFinal < 4){
            this.status = "Reprovado";
        }
    }

    public List<Notas> getNotases() {
        EntityManager em = JPAUtils.getEntityManager();
        Query q = em.createQuery("select f from Notas f", Notas.class);
        List<Notas> ns = q.getResultList();
        em.close();
        
        return ns;
    }

    public void setNotases(List<Notas> notases) {
        this.notases = notases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getNota4() {
        return nota4;
    }

    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public notasRN getNrn() {
        return nrn;
    }

    public void setNrn(notasRN nrn) {
        this.nrn = nrn;
    }

    public Notas getNdb() {
        return ndb;
    }

    public void setNdb(Notas ndb) {
        this.ndb = ndb;
    }

    
}
