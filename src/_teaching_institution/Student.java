package _teaching_institution;
public class Student extends Person{
    private String matricula;
    private Teacher orientador;
    
    public Student(String name,String cpf,String phoneNumber,String address,String matricula,Teacher orientador){
        super(name,cpf,phoneNumber,address);
        this.matricula=matricula;
        this.orientador=orientador;
    }
    
    public String getMatricula(){return this.matricula;}
    
    public void setMatricula(String newMatricula){
        this.matricula=newMatricula;
    }
    
    public Teacher getOrientador(){return this.orientador;}
    
    public void setOrientador(Teacher newOrientador){this.orientador=newOrientador;}
}
