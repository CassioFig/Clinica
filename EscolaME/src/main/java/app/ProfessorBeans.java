/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import infra.Funcionario;
import infra.JPAUtils;
import infra.Professor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class ProfessorBeans extends FuncionarioBeans{

    private int idFunc, idFuncionario;
    private String disciplinas, areaInteresses;
    private boolean existe = false;
    private List<Professor> professores = new ArrayList<Professor>();
    
    private Professor p = new Professor();
    
    public ProfessorBeans() {
    }
    
    public void selecionaProf(Professor p){
        this.p = p;
    }
    
    @Override
    public String cadastrar(){
        super.cadastrar();
        
        EntityManager em = JPAUtils.getEntityManager();
        
        p.setIdFuncionario(super.retornaid());
        p.setAreas(getAreaInteresses());
        p.setDisciplinas(getDisciplinas());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(p);
        et.commit();
        em.close();
        
        return "cadCurso";
    }
    
    public void atualizarProfessor(){
        EntityManager em = JPAUtils.getEntityManager();
        
        p.setAreas(p.getAreas());
        p.setDisciplinas(p.getDisciplinas());
        p.setIdFuncionario(p.getIdFuncionario());
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(p);
        et.commit();
        em.close();
        
        limpar_Cadastro();
    }
    
    public void excluirProf(Professor p){
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        p = em.merge(p);
        em.remove(p);
        et.commit();
        em.close();
    }
    
    public void buscarNomeProf(){
        for (Funcionario funcionario : getFuncionarios()) {
            for (Professor professore : getProfessores()) {
                if (funcionario.getNome().equals(getNome()) &&
                        Objects.equals(funcionario.getId(), professore.getIdFuncionario())) {
                    existe = true;
                    
                    idFunc = professore.getId();
                    disciplinas = professore.getDisciplinas();
                    areaInteresses = professore.getAreas();
                    idFuncionario = professore.getIdFuncionario();
                }
            }
        }preencheCampos();
    }
   
    public void buscaDisciplina(){
        for (Professor professore : getProfessores()) {
            if (professore.getDisciplinas().equals(getDisciplinas())) {
                existe = true;
                
                idFunc = professore.getId();
                disciplinas = professore.getDisciplinas();
                areaInteresses = professore.getAreas();
                idFuncionario = professore.getIdFuncionario();
            }
        }preencheCampos();
    }
    
    public void buscaArea(){
        for (Professor professore : getProfessores()) {
            if (professore.getAreas().equals(getAreaInteresses())) {
                existe = true;
                
                idFunc = professore.getId();
                disciplinas = professore.getDisciplinas();
                areaInteresses = professore.getAreas();
                idFuncionario = professore.getIdFuncionario();
            }
        }preencheCampos();
    }
    
    public void busca_Id(){
        for (Professor professore : getProfessores()) {
            if (professore.getId() == getIdFunc()) {
                existe = true;
                
                idFunc = professore.getId();
                disciplinas = professore.getDisciplinas();
                areaInteresses = professore.getAreas();
                idFuncionario = professore.getIdFuncionario();
            }
        }preencheCampos();
    }
    
    public void buscarTudo(){
        buscaArea();
        buscarNomeProf();
        buscaDisciplina();
        busca_Id();
        limpar_busca();
    }
    
    public void limpar_busca(){
        setIdFunc(0);
        setDisciplinas(null);
        setAreaInteresses(null);
    }
    
    public void limpar_Cadastro(){
        p.setId(0);
        p.setAreas(null);
        p.setDisciplinas(null);
        p.setIdFuncionario(null);
    }
    
    public void preencheCampos(){
        if (existe) {
            p.setId(idFunc);
            p.setDisciplinas(disciplinas);
            p.setAreas(areaInteresses);
            p.setIdFuncionario(idFuncionario);
        }
    }

    public List<Professor> getProfessores() {
        EntityManager em = JPAUtils.getEntityManager();
        Query q = em.createQuery("select f from Professor f", Professor.class);
        List<Professor> ps = q.getResultList();
        
        return ps;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
    
    public String getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(String disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getAreaInteresses() {
        return areaInteresses;
    }

    public void setAreaInteresses(String areaInteresses) {
        this.areaInteresses = areaInteresses;
    }

    public Professor getP() {
        return p;
    }

    public void setP(Professor p) {
        this.p = p;
    }
    
    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    
}
