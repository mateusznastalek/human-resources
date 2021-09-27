package domain;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee> {

//    @Serial
//    private static final long serialVersionUID = -5010401699216863641L;

    private String name;
    private String surname;
    private char gender;
    private int numberOfDepartment;
    private double salary;
    private int age;
    private int numberOfChildren;
    private boolean isMarried;

    public Employee(String name, String surname, char gender, int numberOfDepartment, double salary, int age, int numberOfChildren, boolean isMarried) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.numberOfDepartment = numberOfDepartment;
        this.salary = salary;
        this.age = age;
        this.numberOfChildren = numberOfChildren;
        this.isMarried = isMarried;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getNumberOfDepartment() {
        return numberOfDepartment;
    }

    public void setNumberOfDepartment(int numberOfDepartment) {
        this.numberOfDepartment = numberOfDepartment;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int liczbaDzieci) {
        this.numberOfChildren = liczbaDzieci;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        this.isMarried = married;
    }

    public String displayAllEmployeesDataforEmployee() {
        return "Name: " + getName() + ", Surname: " + getSurname() + ", Gender: " + getGender() + ", Department's number: " + getNumberOfDepartment() + ", Salary: " + getSalary() +
                ", Age: " + getAge() + ", Number of children: " + getNumberOfChildren() + ", Married: " + (isMarried());
    }

    public String displayNameSurnameSalaryEmployeesDataForEmployee() {
        return "Name: " + getName() + ", Surname: " + getSurname() + ", Salary: " + getSalary();
    }

    public String displayNameSurnameUppercaseEmployeesDataForEmployee() {
        return getName().toUpperCase() + " " + getSurname().toUpperCase();
    }

    public boolean isSalaryHigher(double salaryToCompare) {
        double comparison = Double.compare(getSalary(), salaryToCompare);
        return comparison >= 0;
    }

    public double countRaise(int raisePercentage) {

        if (getNumberOfChildren() > 0) {
            raisePercentage += (getNumberOfChildren() * 2);
        }

        if (isMarried()) {
            raisePercentage += 3;
        }

        return ((double)(raisePercentage+100))/100;
    }

    @Override
    public int compareTo(Employee employee) {
        return this.getSurname().compareTo(employee.getSurname());
    }
}
