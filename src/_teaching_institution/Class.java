package _teaching_institution;
import java.util.ArrayList;
public class Class {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private String identification;
    
    public Class(String ident,Teacher teacher,Student student){
        this.identification=ident;
        this.teachers=new ArrayList<>();
        this.teachers.add(teacher);
        this.students=new ArrayList<>();
        this.students.add(student);
        //Each class has to contain at least one teacher and one student.
    }
    
    public String getIdentification(){return this.identification;}
    
    public void setIdentification(String newIdent){this.identification=newIdent;}
    
    public Teacher[] getTeachers(){return (Teacher[]) this.teachers.toArray();}
    
    public Student[] getStudents(){return (Student[]) this.students.toArray();}
    
    public void addTeacher(Teacher newTeacher){
        this.teachers.add(newTeacher);
    }
    
    public void addStudent(Student newStudent){
        this.students.add(newStudent);
    }
    
    public boolean removeTeacher(String name){
        for(int counter=0;counter<this.teachers.size();counter++){
            if(this.teachers.get(counter).getName().equals(name)){
                this.teachers.remove(counter);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeStudent(String name){
        for(int counter=0;counter<this.students.size();counter++){
            if(this.students.get(counter).getName().equals(name)){
                this.students.remove(counter);
                return true;
            }
        }
        return false;
    }
}
