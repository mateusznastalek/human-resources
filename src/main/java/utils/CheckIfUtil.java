package utils;

import application.EmployeeDAO;
import domain.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CheckIfUtil {

    static EmployeeDAO employeeDAO;

    public static void checkIfFileExistsAndCreate(File file) throws IOException {
        if (!file.exists() || !file.isFile()) {
            file.createNewFile();
            System.out.println("New file \"" + file.getName()  + "\" has been created.");
        }
    }
}
