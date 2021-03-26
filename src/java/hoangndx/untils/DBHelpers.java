/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.untils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelpers implements Serializable {
    public static Connection makeConnection() 
        throws /*ClassNotFoundException*/NamingException, SQLException {
        
        Context currentContext = new InitialContext();//get OS Naming and Directory
        Context tomccatContext = (Context)currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource)tomccatContext.lookup("DS007");
        Connection con = ds.getConnection();
        return con;
//        //1.Load Driver - add driver into project
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create connection String to DBMS
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;instanceName=SQL2018";
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa","26032000");
//        
//        return con;
    }
}
