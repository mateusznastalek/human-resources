package utils;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerUtil {

    static Scanner scanner = new Scanner(System.in);

    public static String getUserInput(){
        return scanner.nextLine().trim();
    }

    public static void closeScanner(){
        scanner.close();
    }

    public int getUserInputIntZeroToEight(String getUserInput){

        Pattern mainMenuPattern = Pattern.compile("[0-8]");

        while(!mainMenuPattern.matcher((getUserInput)).matches()){
            System.out.println("Incorrect value. Please enter an integer from the list:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public String getUserInputString(String getUserInput){

        Pattern stringPattern = Pattern.compile("[a-zA-Z]+");

        while(!stringPattern.matcher((getUserInput)).matches()){
            System.out.println("Incorrect value. Please enter letters only:");
            getUserInput = scanner.nextLine().trim();
        }

        return getUserInput;
    }

    public char getUserInputGender(String getUserInput){

        Pattern genderPattern = Pattern.compile("[fmFM]");

        while(!genderPattern.matcher((getUserInput)).matches()){
            System.out.println("Incorrect value. Please enter \"F\" for female or \"M\" for male:");
            getUserInput = scanner.nextLine().trim();
        }

        return getUserInput.toUpperCase().charAt(0);
    }

    public int getUserInputNumberDepartment(String getUserInput){

        Pattern intPattern = Pattern.compile("[0-5]");

        while(!intPattern.matcher((getUserInput)).matches()){
            System.out.println("Incorrect value. Please enter department between 1 and 5:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public int getUserInputCheckNumberDepartment(String getUserInput, List departments){

        Pattern intPattern = Pattern.compile("[0-5]");

        while(!intPattern.matcher((getUserInput)).matches() || !departments.contains(Integer.parseInt(getUserInput))){
            System.out.println("Incorrect value. Please enter department from the list:");
            for (Object department:departments) {
                System.out.print(department + ", ");
            }
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public double getUserInputDouble(String getUserInput){

        Pattern doublePattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");

        while(!doublePattern.matcher((getUserInput)).matches()){
            System.out.println("Incorrect value. Please input salary with 2 decimal places:");
            getUserInput = scanner.nextLine().trim();
        }

        return Double.parseDouble(getUserInput);
    }

    public int getUserInputInt(String getUserInput) {

        Pattern intPattern = Pattern.compile("[0-9]+");

        while (!intPattern.matcher((getUserInput)).matches()) {
            System.out.println("Incorrect value. Please enter an integer:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public boolean getUserInputMarriedStatus(String getUserInput) {

        while (!getUserInput.equals("true") && !getUserInput.equals("false")) {
            System.out.println("Incorrect value. Please enter \"true\" or \"false\":");
            getUserInput = scanner.nextLine().trim();
        }

        return getUserInput.equals("true");
    }

    public int getUserInputEmployeesChoice(String getUserInput, int listSize) {

        Pattern employeeChoicePattern = Pattern.compile("[0-9]+");

        while (!employeeChoicePattern.matcher((getUserInput)).matches() || Integer.parseInt(getUserInput)-1 > listSize)
        {
            System.out.println("Incorrect value. Please enter a number from the list:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public int getUserInputIntZeroToSix(String getUserInput) {

        Pattern intPattern = Pattern.compile("[0-6]");

        while (!intPattern.matcher((getUserInput)).matches()) {
            System.out.println("Incorrect value. Please enter number from a list:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }

    public int getUserInputIntZeroToFour(String getUserInput) {

        Pattern intPattern = Pattern.compile("[0-4]");

        while (!intPattern.matcher((getUserInput)).matches()) {
            System.out.println("Incorrect value. Please enter number from a list:");
            getUserInput = scanner.nextLine().trim();
        }

        return Integer.parseInt(getUserInput);
    }
}
