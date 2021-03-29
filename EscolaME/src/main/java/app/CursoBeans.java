/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import infra.Curso;
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
public class CursoBeans {
    
    private int id;
    private String titulo, cargaHoraria, valor, descricao;
    private boolean existe = false;
    private List<Curso> cursos = new ArrayList<Curso>();
    
    private Curso cdb = new Curso();
    
    public CursoBeans() {
    }
    
    public void selecionar(Curso cdb){
        this.cdb = cdb;
    }
    
    public void cadastrarCurso(){
        EntityManager em = JPAUtils.getEntityManager();
        
        cdb.setTitulo(getTitulo());
        cdb.setCarga(getCargaHoraria());
        cdb.setValor(getValor());
        cdb.setDescricao(getDescricao());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(cdb);
        et.commit();
        em.close();
        
    }
    
    public void atualizar(){
        EntityManager em = JPAUtils.getEntityManager();
        
        cdb.setTitulo(cdb.getTitulo());
        cdb.setCarga(cdb.getCarga());
        cdb.setDescricao(cdb.getDescricao());
        cdb.setValor(cdb.getValor());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(cdb);
        et.commit();
        em.close();
        
        limparCadastro();
    }
    
    public void buscarId(){
        for (Curso curso : getCursos()) {
            if (curso.getId() == getId()) {
                existe = true;
                
                id = curso.getId();
                titulo = curso.getTitulo();
                valor = curso.getValor();
                cargaHoraria = curso.getCarga();
                descricao = curso.getDescricao();
            }
        }preencheCampos();
    }
    
    public void buscarTitulo(){
        for (Curso curso : getCursos()) {
            if (curso.getTitulo().equals(getTitulo())) {
                existe = true;
                
                id = curso.getId();
                titulo = curso.getTitulo();
                valor = curso.getValor();
                cargaHoraria = curso.getCarga();
                descricao = curso.getDescricao();
            }
        }preencheCampos();
    }
    
    public void buscar(){
        buscarId();
        buscarTitulo();
        limparBusca();
    }
    
    public void limparBusca(){
        setId(0);
        setTitulo(null);
    }
    
    public void limparCadastro(){
        cdb.setId(0);
        cdb.setTitulo(null);
        cdb.setValor(null);
        cdb.setCarga(null);
        cdb.setDescricao(null);
    }
    
    public void preencheCampos(){
        if (existe) {
            cdb.setId(id);
            cdb.setTitulo(titulo);
            cdb.setValor(valor);
            cdb.setCarga(cargaHoraria);
            cdb.setDescricao(descricao);
        }
    }

    public List<Curso> getCursos() {
        EntityManager em = JPAUtils.getEntityManager();
        Query q = em.createQuery("select f from Curso f", Curso.class);
        List<Curso> cs = q.getResultList();
        em.close();
        
        return cs;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Curso getCdb() {
        return cdb;
    }

    public void setCdb(Curso cdb) {
        this.cdb = cdb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
