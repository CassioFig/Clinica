/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import infra.Aluno;
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
public class AlunoBeans {
    
    private int id;
    private String nome, cpf, rg, data, status, sexo;
    private double notafinal;
    private boolean existe = false;
    private List<Aluno> alunos = new ArrayList<Aluno>();
    
    private Aluno a = new Aluno();
    private NotasBeans nb = new NotasBeans();
    private CursoBeans cb = new CursoBeans();
    
    public AlunoBeans() {
    }
    
    
    public void selecionar(Aluno a){
        this.a = a;
    }
    
    public String cadastrarAluno(){
        EntityManager em = JPAUtils.getEntityManager();
        
        a.setNome(getNome());
        a.setCpf(getCpf());
        a.setRg(getRg());
        a.setNascimento(getData());
        a.setStatus(nb.getStatus());
        a.setNotaFinal(nb.getNotaFinal());
        a.setSexo(getSexo());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(a);
        et.commit();
        em.close();
        
        return "cadCurso";
    }
    
    public void atualizarAluno(){
        EntityManager em = JPAUtils.getEntityManager();
        
        a.setNome(a.getNome());
        a.setCpf(a.getCpf());
        a.setRg(a.getRg());
        a.setNascimento(a.getNascimento());
        a.setSexo(a.getSexo());
        a.setNotaFinal(a.getNotaFinal());
        a.setStatus(a.getStatus());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(a);
        et.commit();
        em.close();
        
        limparCadastro();
    }
    
    public void teste(){
        a.setNotaFinal(nb.getNotaFinal());
    }
    
    public void excluirAluno(Aluno a){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        a = em.merge(a);
        em.remove(a);
        et.commit();
        em.close();
    }
    
    public void buscarCPF(){
        for (Aluno aluno : getAlunos()) {
            if (aluno.getCpf().equals(getCpf())) {
                existe = true;
                
                id = aluno.getId();
                nome = aluno.getNome();
                rg = aluno.getRg();
                cpf = aluno.getCpf();
                data = aluno.getNascimento();
                sexo = aluno.getSexo();
                notafinal = aluno.getNotaFinal();
                status = aluno.getStatus();
            }
        }preencheCampos();
    }
    
    public void buscarNome(){
        for (Aluno aluno : getAlunos()) {
            if (aluno.getNome().equals(getNome())) {
                existe = true;
                
                id = aluno.getId();
                nome = aluno.getNome();
                rg = aluno.getRg();
                cpf = aluno.getCpf();
                data = aluno.getNascimento();
                sexo = aluno.getSexo();
                notafinal = aluno.getNotaFinal();
                status = aluno.getStatus();
            }
        }preencheCampos();
    }
    
    public void buscarNascimento(){
        for (Aluno aluno : getAlunos()) {
            if (aluno.getNascimento().equals(getData())) {
                existe = true;
                
                id = aluno.getId();
                nome = aluno.getNome();
                rg = aluno.getRg();
                cpf = aluno.getCpf();
                data = aluno.getNascimento();
                sexo = aluno.getSexo();
                notafinal = aluno.getNotaFinal();
                status = aluno.getStatus();
            }
        }preencheCampos();
    }
    
    public void buscarStatus(){
        for (Aluno aluno : getAlunos()) {
            if (aluno.getStatus().equals(getStatus())) {
                existe = true;
                
                id = aluno.getId();
                nome = aluno.getNome();
                rg = aluno.getRg();
                cpf = aluno.getCpf();
                data = aluno.getNascimento();
                sexo = aluno.getSexo();
                notafinal = aluno.getNotaFinal();
                status = aluno.getStatus();
            }
        }preencheCampos();
    }
    
    public void buscar(){
        buscarCPF();
        buscarNascimento();
        buscarNome();
        buscarStatus();
        limparBusca();
    }
    
    public void preencheCampos(){
        if (existe) {
            a.setId(id);
            a.setNome(nome);
            a.setRg(rg);
            a.setCpf(cpf);
            a.setNascimento(data);
            a.setSexo(sexo);
            a.setNotaFinal(notafinal);
            a.setStatus(status);
        }
    }
    
    public void limparCadastro(){
        a.setId(0);
        a.setNome(null);
        a.setRg(null);
        a.setCpf(null);
        a.setNascimento(null);
        a.setSexo(null);
        a.setNotaFinal(0.0);
        a.setStatus(null);
    }
    
    public void limparBusca(){
        setId(0);
        setNome(null);
        setRg(null);
        setCpf(null);
        setData(null);
        setSexo(null);
        setNotafinal(0.0);
        setStatus(null);
    }

    public List<Aluno> getAlunos() {
        EntityManager em = JPAUtils.getEntityManager();
        Query q = em.createQuery("select f from Aluno f", Aluno.class);
        List<Aluno> as = q.getResultList();
        em.close();
        
        return as;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

    public Aluno getA() {
        return a;
    }

    public void setA(Aluno a) {
        this.a = a;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public NotasBeans getNb() {
        return nb;
    }

    public void setNb(NotasBeans nb) {
        this.nb = nb;
    }

    public CursoBeans getCb() {
        return cb;
    }

    public void setCb(CursoBeans cb) {
        this.cb = cb;
    }
    
    
}
