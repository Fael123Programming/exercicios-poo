package _teaching_institution;

import java.util.ArrayList;

public class Institution {
    private String name;
    private ArrayList<Department> departments;
    private ArrayList<Class> classes;

    public Institution(String name, Department department, Class cls) {
        this.name = name;
        this.departments = new ArrayList<>();
        this.departments.add(department);
        this.classes = new ArrayList<>();
        this.classes.add(cls);
        //Each institution has to contain at least one department and one class.
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Department[] getDepartments() {
        return (Department[]) this.departments.toArray();
    }

    public Class[] getClasses() {
        return (Class[]) this.classes.toArray();
    }

    public void addDepartment(Department newDepart) {
        this.departments.add(newDepart);
    }

    public boolean removeDepartment(String name) {
        for (int counter = 0; counter < this.departments.size(); counter++) {
            if (this.departments.get(counter).getName().equals(name)) {
                this.departments.remove(counter);
                return true;
            }
        }
        return false;//Didn't find the specified name.
    }

    public void addClass(Class newCls) {
        this.classes.add(newCls);
    }

    public boolean removeClass(String identification) {
        for (int counter = 0; counter < this.classes.size(); counter++) {
            if (this.classes.get(counter).getIdentification().equals(identification)) {
                this.classes.remove(counter);
                return true;
            }
        }
        return false;//Didn't find the specified identification.
    }
}
