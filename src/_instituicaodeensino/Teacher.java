package _instituicaodeensino;
public class Teacher extends Person{
    private String identification;
    private String teachingArea;
    
    public Teacher(String name,String cpf,String phoneNumber,String address,
            String identification,String teachingArea){
        super(name,cpf,phoneNumber,address);
        this.identification=identification;
        this.teachingArea=teachingArea;
    }
    
    public String getIdentification(){return this.identification;}
    
    public void setIdentification(String newIdent){this.identification=newIdent;}
    
    public String getTeachingArea(){return this.teachingArea;}
    
    public void setTeachingArea(String newTeachingArea){this.teachingArea=newTeachingArea;}
}
