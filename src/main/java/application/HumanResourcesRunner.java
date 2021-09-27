package application;

import domain.Employee;
import utils.CheckIfUtil;
import utils.FormatUtil;
import utils.ScannerUtil;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HumanResourcesRunner {

    static File objectsFile = new File("employees_data_base.dat");

    static ScannerUtil ScannerUtil = new ScannerUtil();

    static FormatUtil formatUtil = new FormatUtil();

    static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        chooseInMainMenu();
    }

    public static void readObjectsFile(String fileName) throws IOException, ClassNotFoundException {

        File file = new File(fileName);

        CheckIfUtil.checkIfFileExistsAndCreate(file);

        List<Employee> temporaryEmployeesList = new ArrayList<>();

        try (ObjectInputStream pl2 = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                temporaryEmployeesList.add((Employee) pl2.readObject());
            }
        } catch (EOFException ex) {
            System.out.println("");
        }
        employeeDAO.setEmployeesList(temporaryEmployeesList);
    }

    public static void saveToObjectsFile(String fileName) {

        try (ObjectOutputStream pl = new ObjectOutputStream(new FileOutputStream(fileName))) {

            for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
                pl.writeObject(employeeDAO.getEmployeesList().get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int displayMainMenu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Display all employees");
        System.out.println("     2. Add new employee");
        System.out.println("     3. Export objects file to text file");
        System.out.println("     4. Remove employee");
        System.out.println("     5. Employee data editing");
        System.out.println("     6. Additional functions");
        System.out.println("     7. Additional functions for text files");
        System.out.println("     8. Information about application");
        System.out.println("     0. Exit");

        return ScannerUtil.getUserInputIntZeroToEight(utils.ScannerUtil.getUserInput());
    }

    public static void chooseInMainMenu() throws IOException, ClassNotFoundException {

        int choiceInMenu = displayMainMenu();

        while (choiceInMenu != 0) {
            switch (choiceInMenu) {
                case 1 -> displayNameSurnameSalaryEmployeesDataFromList(objectsFile.getPath());
                case 2 -> addEmployeeViaScanner(objectsFile.getPath());
                case 3 -> exportObjectsFileToTextFile(objectsFile.getPath());
                case 4 -> {
                    displayAllEmployeesDataFromList(objectsFile.getPath());
                    removeEmployee(objectsFile.getPath());
                }
                case 5 -> {
                    displayAllEmployeesDataFromList(objectsFile.getPath());
                    editEmployee(objectsFile.getPath());
                }
                case 6 -> chooseInExtraOnFunctionsMenu(objectsFile.getPath());
                case 7 -> chooseInAdditionalFunctionsMenuForTextFile(objectsFile.getPath());
                case 8 -> displayInformationAboutApplication();
            }
            System.out.println("\nPress enter to go back to main Menu...");
            System.in.read();

            choiceInMenu = displayMainMenu();
        }
        System.out.println("     ****************************************");
        System.out.println("\n      Program closed\n\n");
        utils.ScannerUtil.closeScanner();
    }

    public static void displayNameSurnameSalaryEmployeesDataFromList(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println();
        System.out.println("#########################################################");
        System.out.println("######               EMPLOYESS LIST                ######");
        System.out.println("#########################################################");

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
            Employee employee = employeeDAO.getEmployeesList().get(i);
            System.out.println((i + 1) + ": " + employee.displayNameSurnameSalaryEmployeesDataForEmployee());
        }
    }

    //1.Menu
    public static void displayAllEmployeesDataFromList(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println();
        System.out.println("#########################################################");
        System.out.println("######               EMPLOYESS LIST                ######");
        System.out.println("#########################################################");

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
            Employee employee = employeeDAO.getEmployeesList().get(i);
            System.out.println((i + 1) + ": " + employee.displayAllEmployeesDataforEmployee());
        }
    }

    //2.Menu
    public static void addEmployeeViaScanner(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Name: ");
        String nameFromScanner = ScannerUtil.getUserInputString(utils.ScannerUtil.getUserInput());
        String addName = formatUtil.formatStringToFirstUpperRestLowercase(nameFromScanner);

        System.out.println("Surname: ");
        String surnameFromScanner = ScannerUtil.getUserInputString(utils.ScannerUtil.getUserInput());
        String addSurname = formatUtil.formatStringToFirstUpperRestLowercase(surnameFromScanner);

        System.out.println("Gender: ");
        char addGender = ScannerUtil.getUserInputGender(utils.ScannerUtil.getUserInput());

        System.out.println("Department: ");
        int addNumberOfDepartment = ScannerUtil.getUserInputNumberDepartment(utils.ScannerUtil.getUserInput());

        System.out.println("Salary: ");
        double addSalary = ScannerUtil.getUserInputDouble(utils.ScannerUtil.getUserInput());

        System.out.println("Age: ");
        int addAge = ScannerUtil.getUserInputInt(utils.ScannerUtil.getUserInput());

        System.out.println("Number of children: ");
        int addNumberOfChildren = ScannerUtil.getUserInputInt(utils.ScannerUtil.getUserInput());

        System.out.println("Is employee married? Please enter \"true\" or \"false\":");
        boolean addMarriedStatus = ScannerUtil.getUserInputMarriedStatus(utils.ScannerUtil.getUserInput());

        Employee employeeScanner = new Employee(addName, addSurname, addGender, addNumberOfDepartment, addSalary, addAge, addNumberOfChildren, addMarriedStatus);

        employeeDAO.addEmployeeToList(employeeScanner);

        saveToObjectsFile(fileName);

        System.out.println("\nEmployee has been added successfully.");
    }

    //3.Menu
    public static String exportObjectsFileToTextFile(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Please enter name for text file:");

        String textFileName = utils.ScannerUtil.getUserInput().concat(".txt");

        File textFile = new File(textFileName);

        CheckIfUtil.checkIfFileExistsAndCreate(textFile);

        PrintWriter printWriter = new PrintWriter(new FileWriter(textFileName));

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {

            printWriter.println(employeeDAO.getEmployeesList().get(i).getSurname() + " " +
                    employeeDAO.getEmployeesList().get(i).getName() + " " +
                    employeeDAO.getEmployeesList().get(i).getGender() + " " +
                    employeeDAO.getEmployeesList().get(i).getNumberOfDepartment() + " " +
                    employeeDAO.getEmployeesList().get(i).getSalary() + " " +
                    employeeDAO.getEmployeesList().get(i).getAge() + " " +
                    employeeDAO.getEmployeesList().get(i).getNumberOfChildren());
        }
        printWriter.close();
        System.out.println("Export to file \"" + textFileName + "\" was successful.");

        return textFileName;
    }

    //4.Menu
    public static void removeEmployee(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("\nChoose employee to remove from list and confirm with Enter or \"0\" to go back to main Menu:");
        int employeesIndexToRemove = ScannerUtil.getUserInputEmployeesChoice(utils.ScannerUtil.getUserInput(), employeeDAO.employeesList.size());

        if (employeesIndexToRemove == 0) {
            displayMainMenu();
        } else {
            employeeDAO.removeEmployeeFromList(employeesIndexToRemove);
        }

        saveToObjectsFile(fileName);

        System.out.println("Employee has been removed successfully.");
    }

    //5.Menu
    public static int displayEmployeeEditingMenu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *            EMPLOYEES EDITING         *");
        System.out.println("     ****************************************");
        System.out.println("     1. Surname (Please note that change only possible for females)");
        System.out.println("     2. Number of department");
        System.out.println("     3. Salary");
        System.out.println("     4. Age");
        System.out.println("     5. Number of children");
        System.out.println("     6. Married status");
        System.out.println("     0. Go back to main Menu");

        return ScannerUtil.getUserInputIntZeroToSix(utils.ScannerUtil.getUserInput());
    }

    public static void editEmployee(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("\nChoose employee to edit from list and confirm with Enter or \"0\" to go back to main Menu:");
        int employeesIndexToEdit = ScannerUtil.getUserInputEmployeesChoice(utils.ScannerUtil.getUserInput(), employeeDAO.employeesList.size());

        if (employeesIndexToEdit == 0) {
            displayMainMenu();
        }

        Employee chosenEmployee = employeeDAO.getEmployeesList().get(employeesIndexToEdit - 1);

        System.out.println("Chosen employee: " + chosenEmployee.displayNameSurnameUppercaseEmployeesDataForEmployee());

        int choiceInEmployeesEditingMenu = displayEmployeeEditingMenu();

        while (choiceInEmployeesEditingMenu != 0) {
            switch (choiceInEmployeesEditingMenu) {
                case 1:
                    if (chosenEmployee.getGender() == 'M') {
                        System.out.println("Chosen employee is a male. Surname editing is not allowed.");
                    } else {
                        System.out.println("Please enter new surname:");
                        String newSurname = ScannerUtil.getUserInputString(utils.ScannerUtil.getUserInput());
                        chosenEmployee.setSurname(formatUtil.formatStringToFirstUpperRestLowercase(newSurname));
                    }
                    break;
                case 2:
                    System.out.println("Please enter new number of department:");
                    int newNumberDepartment = ScannerUtil.getUserInputNumberDepartment(utils.ScannerUtil.getUserInput());
                    chosenEmployee.setNumberOfDepartment(newNumberDepartment);
                    break;
                case 3:
                    System.out.println("Please enter new salary:");
                    double newSalary = ScannerUtil.getUserInputDouble(utils.ScannerUtil.getUserInput());
                    chosenEmployee.setSalary(newSalary);
                    break;
                case 4:
                    System.out.println("Please enter new age:");
                    int newAge = ScannerUtil.getUserInputInt(utils.ScannerUtil.getUserInput());
                    chosenEmployee.setAge(newAge);
                    break;
                case 5:
                    System.out.println("Please enter new number of children: ");
                    int newNumberOfChildren = ScannerUtil.getUserInputInt(utils.ScannerUtil.getUserInput());
                    chosenEmployee.setNumberOfChildren(newNumberOfChildren);
                    break;
                case 6:
                    System.out.println("Please enter new married status: ");
                    boolean newMarriedStatus = ScannerUtil.getUserInputMarriedStatus(utils.ScannerUtil.getUserInput());
                    chosenEmployee.setMarried(newMarriedStatus);
                    break;
            }
            saveToObjectsFile(fileName);
            System.out.println("Data was updated successfully.");
            System.out.println("\nPress Enter to go to previous Menu..");
            System.in.read();

            choiceInEmployeesEditingMenu = displayEmployeeEditingMenu();
        }
        displayMainMenu();
    }

    //6.Menu
    public static int displayExtraOnFunctionsMenu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *           EXTRA ON FUNCTIONS         *");
        System.out.println("     ****************************************");
        System.out.println("     1. Number of employees with salary not smaller than provided to compare");
        System.out.println("     2. Average salary in chosen department");
        System.out.println("     3. Max salary for gender");
        System.out.println("     4. Statistical data for departments");
        System.out.println("     5. Average salary ratio between genders");
        System.out.println("     6. 10% raise for each employee and extra bonus for specific conditions (2% for each child and 3% for being married)");
        System.out.println("     7. Raise for each employee by provided amount");
        System.out.println("     8. Sort menu");
        System.out.println("     0. Exit to main Menu");

        return ScannerUtil.getUserInputIntZeroToEight(utils.ScannerUtil.getUserInput());
    }

    public static void chooseInExtraOnFunctionsMenu(String fileName) throws IOException, ClassNotFoundException {

        int choiceInExtraOnFunctionsMenu = displayExtraOnFunctionsMenu();

        while (choiceInExtraOnFunctionsMenu != 0) {
            switch (choiceInExtraOnFunctionsMenu) {
                case 1 -> countEmployeesWithHigherSalary(fileName);
                case 2 -> displayAverageSalaryInDepartment(fileName);
                case 3 -> displayMaxSalaryForGender(fileName);
                case 4 -> displayStatisticalDataForDepartments(fileName);
                case 5 -> countAverageRatioForGenders(fileName);
                case 6 -> {
                    countNewSalariesAfterPercentageRaise(fileName);
                    displayNameSurnameSalaryEmployeesDataFromList(fileName);
                }
                case 7 -> {
                    countNewSalariesWithAddedAmount(fileName);
                    displayNameSurnameSalaryEmployeesDataFromList(fileName);
                }
                case 8 -> {
                    sortEmployees(fileName);
                    displayNameSurnameSalaryEmployeesDataFromList(fileName);
                }
            }
            System.out.println("\nPress Enter to go Extra On Functions Menu...");
            System.in.read();

            choiceInExtraOnFunctionsMenu = displayExtraOnFunctionsMenu();
        }
        displayMainMenu();
    }

    //7.Menu
    public static int displayExtraOnFunctionsForTextFilesMenu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *   EXTRA ON FUNCTIONS FOR TEXT FILES  *");
        System.out.println("     ****************************************");
        System.out.println("     1. Display employee with the longest surname");
        System.out.println("     2. Display average age of employees who have children");
        System.out.println("     3. Code surnames in text file");
        System.out.println("     4. Export employees to \"employees.html\"");
        System.out.println("     0. Exit to main Menu");

        return ScannerUtil.getUserInputIntZeroToSix(utils.ScannerUtil.getUserInput());
    }

    public static void chooseInAdditionalFunctionsMenuForTextFile(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        String fileTextName = exportObjectsFileToTextFile(fileName);

        int choiceInExtraOnFunctionsForTextFilesMenu = displayExtraOnFunctionsForTextFilesMenu();

        while (choiceInExtraOnFunctionsForTextFilesMenu != 0) {
            switch (choiceInExtraOnFunctionsForTextFilesMenu) {
                case 1 -> displayEmployeeWithLongestSurname(fileTextName);
                case 2 -> displayAverageAgeOfEmployeesWithChildren(fileTextName);
                case 3 -> codeSurnamesInTextFile(fileTextName);
                case 4 -> exportToHTMLFile(fileTextName);
            }
            System.out.println("\nPress Enter to go to previous Menu.");
            System.in.read();

            choiceInExtraOnFunctionsForTextFilesMenu = displayExtraOnFunctionsForTextFilesMenu();
        }
        displayMainMenu();
    }

    //1. Extra On Functions for text files
    public static void displayEmployeeWithLongestSurname(String textFileName) throws FileNotFoundException {

        File textFile = new File(textFileName);

        Scanner scannedTextFile = new Scanner(new BufferedReader(new FileReader(textFile)));

        List<String> employeeData = new ArrayList<>();
        List<String> employeeSurnames = new ArrayList<>();

        while (scannedTextFile.hasNext()) {
            String line = scannedTextFile.nextLine();
            employeeData.add(line);
            String[] elements = line.split("[\\s+]");
            employeeSurnames.add(elements[0]);
        }

        scannedTextFile.close();

        int indexEmployeeWithTheLongestSurname = employeeSurnames
                .indexOf(Collections.max(employeeSurnames, Comparator.comparingInt(String::length)));

        System.out.println("\nEmployee with the longest surname is:\n" +
                employeeData.get(indexEmployeeWithTheLongestSurname));
    }

    //2. Extra On Functions for text files
    public static void displayAverageAgeOfEmployeesWithChildren(String textFileName) throws FileNotFoundException {

        File textFile = new File(textFileName);

        Scanner scannedTextFile = new Scanner(new BufferedReader(new FileReader(textFile)));

        List<String> employeesAge = new ArrayList<>();

        while (scannedTextFile.hasNext()) {
            String line = scannedTextFile.nextLine();
            String[] elements = line.split("[\\s+]");
            if (Integer.parseInt(elements[6]) > 0) {
                employeesAge.add((elements[5]));
            }
        }
        scannedTextFile.close();

        double averageAgeEmployeesWithChildren = employeesAge.stream()
                .mapToDouble(Double::parseDouble)
                .average()
                .getAsDouble();

        System.out.println("Average age of employees who have children is " + (int) averageAgeEmployeesWithChildren + ".");
    }

    //3. Extra On Functions for text files
    public static void codeSurnamesInTextFile(String textFileName) throws FileNotFoundException {

        File textFile = new File(textFileName);

        Scanner scannedTextFile = new Scanner(new BufferedReader(new FileReader(textFile)));

        List<String> employeesData = new ArrayList<>();
        List<String> employeesSurnames = new ArrayList<>();
        List<Double> employeesSalaries = new ArrayList<>();

        while (scannedTextFile.hasNext()) {
            String line = scannedTextFile.nextLine();
            employeesData.add(line);
            String[] elements = line.split("[\\s+]");
            employeesSurnames.add(elements[0]);
            employeesSalaries.add(Double.parseDouble(elements[4]));
        }
        scannedTextFile.close();

        double employeesAverageSalaries = employeesSalaries.stream().mapToDouble(e -> e).average().getAsDouble();

        String codedSurname;
        String star = "*";

        for (int i = 0; i < employeesSurnames.size(); i++) {
            if (employeesSalaries.get(i) < (employeesAverageSalaries)) {
                codedSurname = (employeesSurnames.get(i).charAt(0) +
                        star.repeat(employeesSurnames.get(i).length() - 2) +
                        employeesSurnames.get(i).charAt(employeesSurnames.get(i).length() - 1));
                employeesSurnames.set(i, codedSurname);
            }
        }

        PrintWriter overwriteFile = new PrintWriter(textFile);

        List<String> employeedataWithCodedSurname = new ArrayList<>();

        for (int i = 0; i < employeesData.size(); i++) {
            employeedataWithCodedSurname
                    .add(employeesSurnames.get(i) +
                            employeesData.get(i).substring(employeesData.get(i).indexOf(" "), employeesData.get(i).length()));
            overwriteFile.println(employeedataWithCodedSurname.get(i));
        }
        overwriteFile.close();

        System.out.println("Employees surnames have been coded successfully.");
    }

    //4. Extra On Functions for text files
    public static void exportToHTMLFile(String textFileName) throws IOException {

        File textFile = new File(textFileName);

        File htmlFile = new File("employees.html");

        CheckIfUtil.checkIfFileExistsAndCreate(htmlFile);

        Scanner scannedTextFile = new Scanner(new BufferedReader(new FileReader(textFile)));

        PrintWriter printWriterHTML = new PrintWriter(new FileWriter(htmlFile));

        printWriterHTML.print("""
                <html><body>
                <table>
                <tr><td>'Surname'</td><td>'Name'</td><td>'Gender'</td><td>'Number of department'</td><td>'Salary'</td><td>'Age'</td></tr>
                """);

        while (scannedTextFile.hasNext()) {
            String line = scannedTextFile.nextLine();
            String[] elements = line.split("\\s+");
            printWriterHTML.println("<tr><td>" + elements[0] + "</td><td>" + elements[1] + "</td><td>" + elements[2] +
                    "</td><td>" + elements[3] + "</td><td>" + elements[4] + "</td><td>" + elements[5] + "</td></tr>");
        }
        printWriterHTML.print("""
                </table>
                </body></html>
                """);
        printWriterHTML.close();
        scannedTextFile.close();

        System.out.println("Text file has been exported successfully to \"" + htmlFile.getName() + "\".");
    }

    //8. Menu
    public static void displayInformationAboutApplication() {
        System.out.println("\nApplication supports human resources department to store data in small company.");
    }

    //1. Extra On Functions Menu
    public static void countEmployeesWithHigherSalary(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Please enter salary to compare: ");
        double salaryToCompare = ScannerUtil.getUserInputDouble(utils.ScannerUtil.getUserInput());

        int counter = 0;

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
            Employee employee = employeeDAO.getEmployeesList().get(i);
            if (employee.isSalaryHigher(salaryToCompare)) {
                counter++;
            }
        }
        System.out.println("Number of employees with salary not smaller than " + salaryToCompare + " is: " + counter + ".");
    }

    //2. Extra On Functions Menu
    public static void displayAverageSalaryInDepartment(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        List<Integer> departments = employeeDAO.getEmployeesList()
                .stream()
                .map(Employee::getNumberOfDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Please enter department's number: ");
        int chosenDepartment = ScannerUtil.getUserInputCheckNumberDepartment(utils.ScannerUtil.getUserInput(), departments);

        System.out.println("Average salary in department \"" + chosenDepartment + "\" is equal to " + countAverageSalaryForDepartment(chosenDepartment) + ".");
    }

    public static double countAverageSalaryForDepartment(int chosenDepartment) {

        double averageSalary = employeeDAO.getEmployeesList()
                .stream()
                .filter(e -> e.getNumberOfDepartment() == chosenDepartment)
                .mapToDouble(Employee::getSalary)
                .average().getAsDouble();

        averageSalary *= 100;
        averageSalary = Math.round(averageSalary);
        averageSalary /= 100;

        return averageSalary;
    }

    //3. Extra On Functions Menu
    public static void displayMaxSalaryForGender(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Please enter gender:");
        char chosenGender = ScannerUtil.getUserInputGender(utils.ScannerUtil.getUserInput());

        System.out.println("The highest salary for \"" + chosenGender + "\" is equal to " + findMaxSalary(chosenGender) + ".");
    }

    public static double findMaxSalary(char chosenGender) {

        return employeeDAO.getEmployeesList()
                .stream()
                .filter(e -> e.getGender() == chosenGender)
                .map(Employee::getSalary)
                .max(Double::compare).get();
    }

    //4. Extra On Functions Menu
    public static void displayStatisticalDataForDepartments(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        List<Integer> departments = employeeDAO.getEmployeesList()
                .stream()
                .map(Employee::getNumberOfDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        for (Integer department : departments) {
            System.out.println("Department " + department +
                    giveInformationAboutEmployeesGendersInDepartment(department) +
                    ", average salary: " + countAverageSalaryForDepartment(department));
        }
    }

    public static int countEmployeesInDepartment(char gender, int numberOfDepartment) {

        List<Employee> list = employeeDAO.getEmployeesList()
                .stream()
                .filter(e -> e.getNumberOfDepartment() == numberOfDepartment)
                .filter(e -> e.getGender() == gender)
                .collect(Collectors.toList());

        return list.size();
    }

    public static String giveInformationAboutEmployeesGendersInDepartment(int numberOfDepartment) {

        return countEmployeesInDepartment('F', numberOfDepartment) > countEmployeesInDepartment('M', numberOfDepartment) ? " - more females" :
                countEmployeesInDepartment('F', numberOfDepartment) < countEmployeesInDepartment('M', numberOfDepartment) ? " - more males" :
                        " - same number of females and males";
    }

    //5. Extra On Functions Menu
    public static void countAverageRatioForGenders(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Ratio between average salary for females and males is "
                + (countAverageSalaryForGender('F') + " : " + countAverageSalaryForGender('M')));
    }

    public static double countAverageSalaryForGender(char chosenGender) {
        double averageSalaryForGender = employeeDAO.getEmployeesList()
                .stream()
                .filter(e -> e.getGender() == chosenGender)
                .mapToDouble(Employee::getSalary)
                .average().getAsDouble();

        averageSalaryForGender *= 100;
        averageSalaryForGender = Math.round(averageSalaryForGender);
        averageSalaryForGender /= 100;

        return averageSalaryForGender;
    }

    //6.Extra On Functions Menu
    public static void countNewSalariesAfterPercentageRaise(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
            Employee employee = employeeDAO.getEmployeesList().get(i);
            double salaryWithRaise = (employee.getSalary() * employee.countRaise(10));
            salaryWithRaise *= 100;
            salaryWithRaise = Math.round(salaryWithRaise);
            salaryWithRaise /= 100;
            employee.setSalary(salaryWithRaise);
        }
        saveToObjectsFile(fileName);

        System.out.println("Employees salaries have been increased successfully.");
    }

    //7. Extra On Functions Menu
    public static void countNewSalariesWithAddedAmount(String fileName) throws IOException, ClassNotFoundException {

        readObjectsFile(fileName);

        System.out.println("Enter raise amount: ");
        double raiseAmount = ScannerUtil.getUserInputDouble(utils.ScannerUtil.getUserInput());

        double femalesSalariesSum = employeeDAO.getEmployeesList().stream().filter(e -> e.getGender() == 'F').mapToDouble(Employee::getSalary).sum();
        double malesSalariesSum = employeeDAO.getEmployeesList().stream().filter(e -> e.getGender() == 'M').mapToDouble(Employee::getSalary).sum();

        double raisesSum = 0;

        for (int i = 0; i < employeeDAO.getEmployeesList().size(); i++) {
            Employee employee = employeeDAO.getEmployeesList().get(i);
            employee.setSalary(employee.getSalary() + raiseAmount);
            raisesSum += raiseAmount;
        }

        double femalesSalariesWithRaisesSum = employeeDAO.getEmployeesList().stream().filter(e -> e.getGender() == 'F').mapToDouble(Employee::getSalary).sum();
        double malesSalariesWithRaisesSum = employeeDAO.getEmployeesList().stream().filter(e -> e.getGender() == 'M').mapToDouble(Employee::getSalary).sum();

        saveToObjectsFile(fileName);

        System.out.println("Sum of raises of all employess is equal to " + raisesSum + ".");

        System.out.println("Sum raises ration between females and males is " +
                (femalesSalariesWithRaisesSum - femalesSalariesSum) + " : " + (malesSalariesWithRaisesSum - malesSalariesSum) + ".");
    }

    //8. Extra On Functions Menu
    public static int displaySortMenu() {
        System.out.print("""

                 ****************************************
                 *              SORT MENU               *
                 ****************************************
                 1. Sort ascending by surname
                 2. Sort descending by surname
                 3. Sort ascending by salary
                 4. Sort descending by salary
                 0. Exit to previous Menu
                """);

        return ScannerUtil.getUserInputIntZeroToFour(utils.ScannerUtil.getUserInput());
    }

    public static void sortEmployees(String nazwaPliku) throws IOException, ClassNotFoundException {

        readObjectsFile(nazwaPliku);

        int choiceInSortMenu = displaySortMenu();

        while (choiceInSortMenu != 0) {

            employeeDAO.chooseSortingMethod(choiceInSortMenu);

            System.out.println("\nEmployees are sorted successfully.");

            saveToObjectsFile(nazwaPliku);

            displayNameSurnameSalaryEmployeesDataFromList(nazwaPliku);

            System.out.println("\nPress Enter to go back to Extra On Functions Menu.");
            System.in.read();

            choiceInSortMenu = displaySortMenu();
        }
        displayExtraOnFunctionsMenu();
    }
}