package a4;

import java.sql.*;

/**
 *
 * @author syedmfaizan
 */
public class EmployeeCRUD {
    private MysqlDatabaseConnection connection=null;
    
    EmployeeCRUD(MysqlDatabaseConnection conn){
        this.connection=conn;
    }
    
    ResultSet getAllEmployee(){
        return connection.executeQuery("SELECT * FROM SALESPERSON");
    }
    
    ResultSet getEmployeeById(String ID){
        return connection.executeQuery("SELECT * FROM SALESPERSON Where Emp_ID="+ID);
    }
    
    ResultSet getSoldCar(String Code){
        return connection.executeQuery( "SELECT  Fname,Lname,SALESPERSON.Emp_ID,Cid,Make,Colour\n" +
                                        "FROM SALESPERSON\n" +
                                        "INNER JOIN CAR ON SALESPERSON.Emp_ID=CAR.Emp_ID\n"+
        
                                        "WHERE SALESPERSON.Emp_ID="+Code);
    }
           
    int UpdateEmployee(
            String Code, 
            String updatedFName, 
            String updatedSex, 
            String updatedLName,
            String updatedDnum
            
            )
    {
        return connection.executeUpdate("UPDATE SALESPERSON SET Emp_ID='"+Code+"',Sex='"+updatedSex+"', Fname='"+updatedFName+"', Lname='"+updatedLName+"', Dnum='"+updatedDnum+"' WHERE Emp_ID="+Code);
    }
    ResultSet getAllCar(){
        return connection.executeQuery("SELECT * FROM CAR");
    }
    ResultSet getCarByID(String ID){
        return connection.executeQuery("SELECT * FROM CAR Where Cid="+ID);
    }
}
