package _teaching_institution;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Teacher> teachers;

    public Department(String name, Teacher teacher) {
        this.name = name;
        teachers = new ArrayList<>();
        this.teachers.add(teacher);
        //Each department has to contain at least one teacher.
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Teacher[] getTeachers() {
        return (Teacher[]) teachers.toArray();
    }
    //It returns a array of the arraylist teachers.

    public void addTeacher(Teacher newTeacher) {
        this.teachers.add(newTeacher);
    }

    public boolean removeTeacher(String name) {
        for (int counter = 0; counter < this.teachers.size(); counter++) {
            if (this.teachers.get(counter).getName().equals(name)) {
                this.teachers.remove(counter);
                return true;
            }
        }
        return false;//Didn't find the specified name.
    }
}
