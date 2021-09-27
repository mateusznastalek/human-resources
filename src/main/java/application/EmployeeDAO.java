package application;

import domain.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeDAO {

    List<Employee> employeesList = new ArrayList<>();

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public void addEmployeeToList(Employee employee) {
        if (employeesList.size() < 100) {
            employeesList.add(employee);
        } else {
            System.out.println("You have reached limit of employees." +
                    "\nTo add employee please remove at least one from the list.");
        }
    }

    public void removeEmployeeFromList(int index) {
        employeesList.remove(index - 1);
    }

    public void chooseSortingMethod(int sortingMethod) {

        SortingMethod whichSort = SortingMethod.values()[sortingMethod-1];

        switch (whichSort) {
            case BY_SURNAME_ASC:
                Collections.sort(getEmployeesList());
                break;
            case BY_SURNAME_DESC:
                getEmployeesList().sort(Collections.reverseOrder());
                break;
            case BY_SALARY_ASC:
                Comparator<Employee> SalaryEmployeeComparatorAsc =
                        Comparator.comparingDouble(Employee::getSalary);
                getEmployeesList().sort(SalaryEmployeeComparatorAsc);
                break;
            case BY_SALARY_DESC:
                Comparator<Employee> SalaryEmployeeComparatorDesc =
                        (employee1, employee2) -> Double.compare(employee2.getSalary(), employee1.getSalary());
                getEmployeesList().sort(SalaryEmployeeComparatorDesc);
                break;
        }
    }
}
