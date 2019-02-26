package a4;

/**
 *
 * @author syedmfaizan
 */
public class UserInterface {
    
    void printWelcomeGreetings(){
        System.out.println("Welcome to Sample Java MYSQL Connection Application!");
    }
    
    void printMainMenu(){
        System.out.println("Select Operation from List:");
        System.out.println("1: View Employee(s)");
        System.out.println("2: Update Employee");
        System.out.println("3: Cars which sold by one salesperson");
        System.out.println("4: VIEW CARS");
        System.out.println("999: Exit Application");
    }
    
    void printViewEmployeeMenu(){
        System.out.println("\tSelect Operation from List:");
        System.out.println("\t1: View All Employee");
        System.out.println("\t2: View Employee by Employee id");
        System.out.println("\t999: Back");
    }
    void printViewCarMenu(){
        System.out.println("\tSelect Operation from List:");
        System.out.println("\t1: View All CAR");
        System.out.println("\t2: View Car by CAR id");
        System.out.println("\t999: Back");
    }
    void printEnterCarIDText(){
        System.out.print("Enter CAR ID: ");
    }
    void printEnterEmployeeID(){
        System.out.print("Ebter employee id: ");
    }

    void printEnterEmployeeNewFName(){
        System.out.print("Enter Updated First Name: ");
    }
    
    
    void printEnterEmployeeNewLName(){
        System.out.print("Enter Updated Last Name: ");
    }
    
    void printEnterEmployeeSex(){ 
        System.out.print("Enter Employee sex: ");
    }
    
    void printEnterDnum(){
        System.out.print("Enter the dealer number of this employee: ");
    }
    
    void printInitializationError(){
        System.out.println("Application Cannot Initialize!");
    }
    
}
