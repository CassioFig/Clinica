/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import infra.JPAUtils;
import infra.Funcionario;
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
public class FuncionarioBeans {
    
    private String nome, cpf, rg, cargo;
    private double salario;
    private int id;
    private boolean existe = false;
    private Funcionario f = new Funcionario();
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    
    private folhaBeans fb = new folhaBeans();
   
    public FuncionarioBeans() {
    }
    
    public void selecionar(Funcionario f){
        this.f = f;
    }

    public String cadastrar(){
        EntityManager em = JPAUtils.getEntityManager();
        
        f.setNome(getNome());
        f.setCpf(getCpf());
        f.setRg(getRg());
        f.setCargo(getCargo());
        f.setSalario(fb.getSalario());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(f);
        et.commit();
        em.close();
        
        return "cadFuncionario";
    }
    
    public void excluirFuncionario(Funcionario f){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        f = em.merge(f);
        em.remove(f);
        et.commit();
        em.close();
    }
    
    public void atualizarFuncionario(){
        EntityManager em = JPAUtils.getEntityManager();
        
        f.setNome(f.getNome());
        f.setCargo(f.getCargo());
        f.setCpf(f.getCpf());
        f.setRg(f.getRg());
        f.setSalario(f.getSalario());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(f);
        et.commit();
        em.close();
        
        limparCadastro();
    }
    
    public void buscarNome(){
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getNome().equals(getNome())) {
                existe = true;
                
                id = funcionario.getId();
                nome = funcionario.getNome();
                rg = funcionario.getRg();
                cpf = funcionario.getCpf();
                cargo = funcionario.getCargo();
                salario = funcionario.getSalario();
            }
        }if (existe) {
            f.setId(id);
            f.setNome(nome);
            f.setRg(rg);
            f.setCpf(cpf);
            f.setCargo(cargo);
            f.setSalario(salario);
        }
    }
    
    public void buscarId(){
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getId() == getId()) {
                existe = true;
                
                id = funcionario.getId();
                nome = funcionario.getNome();
                rg = funcionario.getRg();
                cpf = funcionario.getCpf();
                cargo = funcionario.getCargo();
                salario = funcionario.getSalario();
            }
        }if (existe) {
            f.setId(id);
            f.setNome(nome);
            f.setRg(rg);
            f.setCpf(cpf);
            f.setCargo(cargo);
            f.setSalario(salario);
        }
    }
    
    public void limparCadastro(){
        f.setId(0);
        f.setNome(null);
        f.setRg(null);
        f.setCpf(null);
        f.setCargo(null);
        f.setSalario(0.0);
    }
    
    public void limparBusca(){
        setId(0);
        setNome(null);
        setRg(null);
        setCpf(null);
        setCargo(null);
        setSalario(0);
    }
    
    public void buscar(){
        buscarId();
        buscarNome();
        limparBusca();
    }
    
    public int retornaid(){
        int idF = 0;
        for (Funcionario funcionario : getFuncionarios()) {
            idF = funcionario.getId();
        }
        return idF;
    }
    
    public List<Funcionario> getFuncionarios() {
        EntityManager em = JPAUtils.getEntityManager();
        Query q = em.createQuery("select f from Funcionario f", Funcionario.class);
        List<Funcionario> ts = q.getResultList();
        em.close();
        
        return ts;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getCargo() {
        return cargo;
    }
    
    public double getSalario() {
        return salario;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public folhaBeans getFb() {
        return fb;
    }

    public void setFb(folhaBeans fb) {
        this.fb = fb;
    }

    
    
}
