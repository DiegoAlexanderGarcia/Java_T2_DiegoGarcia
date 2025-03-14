package PatronesDeDiseno_Estructurales.Composite;

import java.util.ArrayList;
import java.util.List;

public class main {
    static abstract class Employee {
        protected String name;
        protected String position;

        public Employee(String name, String position) {
            this.name = name;
            this.position = position;
        }

        abstract void showDetails();
    }

    static class Developer extends Employee {
        public Developer(String name) {
            super(name, "Desarrollador");
        }
        @Override
        void showDetails() {
            System.out.println(position + ": " + name);
        }
    }
    static class Manager extends Employee {
        private List<Employee> Subordinates = new ArrayList<>();

        public Manager(String name) {super(name, "Gerente");}

        public void addEmployee(Employee emp) {
            Subordinates.add(emp);
        }
        @Override
        void showDetails() {
            System.out.println(position + ": " + name);
            for (Employee e : Subordinates) {
                e.showDetails();
            }
        }
    }
    public static void main(String[] args) {
        Manager manager = new Manager("Carlos");
        manager.addEmployee(new Developer("Pedro"));
        manager.addEmployee(new Developer("Ana"));
    }
}
