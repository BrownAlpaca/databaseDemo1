package a4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author syedmfaizan
 */
public class Application {
    private UserInterface UI=null;
    private MysqlDatabaseConnection dbConnection=null;
    private Scanner scanner = null;
    private Boolean isRunning=true;
    
    void start(){
        this.initialize();
        this.run();
    }
    
    void initialize(){
        this.scanner = new Scanner(System.in);
        this.UI = new UserInterface();
        MysqlDatabaseConnection dbConnection = new MysqlDatabaseConnection();
        if(dbConnection.connect())
        {
            this.dbConnection=dbConnection;
        }
        else{
            this.isRunning=false;
            this.UI.printInitializationError();
        }
    }
    
    void run(){
        UI.printWelcomeGreetings();
        while(this.isRunning){
            UI.printMainMenu();
            int mainOption = Integer.parseInt(scanner.nextLine());
            switch(mainOption){
                case 1: viewEmployee();
                        break;
                case 2: updateEmployee();
                        break;
                case 3: viewEmployeeSoldProducts();
                        break;
                case 4: viewCar();  
                        break;
                case 999: this.exit();
                        break;
                default: System.out.println("Incorrect Option selected");
            }
            System.out.flush();
        }
    }
    
    void exit(){
        this.dbConnection.disconnect();
        this.scanner.close();
        this.isRunning=false;
    }
    
    void viewEmployee(){
        Boolean backPressed = false;
        while(!backPressed){
            this.UI.printViewEmployeeMenu();
            int customerOption = Integer.parseInt(scanner.nextLine());
            switch(customerOption){
                case 1: viewAllEmployee();
                        break;
                case 2: viewEmployeeById();
                        break;
                case 999: backPressed=true;
                        break;
                default: System.out.println("Incorrect Option selected");
            }
        }
    }
    
    void viewAllEmployee(){
        EmployeeCRUD employeeview = new EmployeeCRUD(dbConnection);
        ResultSet ev = employeeview.getAllEmployee();
        try {
            ResultSetMetaData rsmd = ev.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5));
            while(ev.next())
                System.out.println(ev.getString(1) + "  " + ev.getString(2) + "  " + ev.getString(3)+" "+ev.getString(4)+" "+ev.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void viewEmployeeById(){
        EmployeeCRUD employeeview = new EmployeeCRUD(dbConnection);
        UI.printEnterEmployeeID();
        String EmployeeID = scanner.nextLine();
        ResultSet ev = employeeview.getEmployeeById(EmployeeID);
        try {
            ResultSetMetaData rsmd = ev.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5));
            while(ev.next())
                System.out.println(ev.getString(1) + "  " + ev.getString(2) + "  " + ev.getString(3)+" "+ev.getString(4)+" "+ev.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void updateEmployee(){
        EmployeeCRUD employeeService = new EmployeeCRUD(dbConnection);
        UI.printEnterEmployeeID();
        String employeeID = scanner.nextLine();
        ResultSet rs = employeeService.getEmployeeById(employeeID);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "   " + rsmd.getColumnName(2) + "   " + rsmd.getColumnName(3)+"   "+rsmd.getColumnName(4)+"   "+rsmd.getColumnName(5));
            while(rs.next())
                System.out.println(rs.getString(1) + "   " + rs.getString(2) + "   " + rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5));
            
            
            UI.printEnterEmployeeNewFName();
            String updatedFName=scanner.nextLine();
            UI.printEnterEmployeeNewLName();
            String updatedLName=scanner.nextLine();
            UI.printEnterEmployeeSex();
            String updatedSex=scanner.nextLine();
            UI.printEnterDnum();
            String updatedDnum=scanner.nextLine();
            int updated = employeeService.UpdateEmployee(employeeID,updatedSex,updatedFName,updatedLName,updatedDnum);
            if(updated==1)
                System.out.println("Customer Updated!");
        } catch (SQLException ex) {
             Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //get the cars which sold by a specific employee
    void viewEmployeeSoldProducts(){
        EmployeeCRUD employeeview = new EmployeeCRUD(dbConnection);
        UI.printEnterEmployeeID();
        String employeeID = scanner.nextLine();
        ResultSet rs = employeeview.getSoldCar(employeeID);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "   " + rsmd.getColumnName(2) + "   " + rsmd.getColumnName(3)+"   "+rsmd.getColumnName(4) + "   " + rsmd.getColumnName(5) + "   " + rsmd.getColumnName(6));
            while(rs.next())
                System.out.println(rs.getString(1) + "   " + rs.getString(2) + "   " + rs.getString(3)+"    "+rs.getString(4) + "   " + rs.getString(5) + "   " + rs.getString(6));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void viewCar(){
        Boolean backPressed = false;
        while(!backPressed){
            this.UI.printViewCarMenu();
            int customerOption = Integer.parseInt(scanner.nextLine());
            switch(customerOption){
                case 1: viewAllCar();
                        break;
                case 2: viewCarById();
                        break;
                case 999: backPressed=true;
                        break;
                default: System.out.println("Incorrect Option selected");
            }
        }
    }
    void viewAllCar(){
        EmployeeCRUD employeeService= new EmployeeCRUD(dbConnection);
        ResultSet es = employeeService.getAllCar();
        try {
            ResultSetMetaData rsmd = es.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5));
            while(es.next())
                System.out.println(es.getString(1) + "  " + es.getString(2) + "  " + es.getString(3)+" "+es.getString(4)+" "+es.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void viewCarById(){
        EmployeeCRUD employeeview = new EmployeeCRUD(dbConnection);
        UI.printEnterCarIDText();
        String ID = scanner.nextLine();
        ResultSet ev = employeeview.getCarByID(ID);
        try {
            ResultSetMetaData rsmd = ev.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5));
            while(ev.next())
                System.out.println(ev.getString(1) + "  " + ev.getString(2) + "  " + ev.getString(3)+" "+ev.getString(4)+" "+ev.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
}
