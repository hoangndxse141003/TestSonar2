 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.login;

import hoangndx.untils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/** 
 *
 * @author Admin
 */
public class LoginDAO implements Serializable {

    public  LoginDTO checkLogin(String username, String password)
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LoginDTO user = null;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Login "
                        + "Where username = ? And password = ?";
                //3.create statement & assign parameter value
                 stm = con.prepareStatement(sql);
                 stm.setString(1, username);
                 stm.setString(2, password);
                 //4.Execute Query
                 rs = stm.executeQuery();
                 //5. process
                 if(rs.next()) {
                    user = new LoginDTO(username, password, rs.getString("lastname"), rs.getBoolean("isAdmin"));                                      
                 }
                 
            }//end if con is opened
            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(con != null){
                    con.close();
                }
            }
        return user;
    }
    
    private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }
    
    
    public void searchLastname(String searchValue)         
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Login "
                        + "Where lastname Like ? ";
                 //4.Execute Query
                 stm = con.prepareStatement(sql);
                 stm.setString(1, "%" + searchValue + "%");
                 rs = stm.executeQuery();
                 //5. process
                 while (rs.next()) {
                     String username = rs.getString("username");
                     String password = rs.getString("password");
                     String fullname = rs.getString("lastname");
                     boolean role = rs.getBoolean("isAdmin");
                     LoginDTO dto = new LoginDTO(username, password, fullname, role);
                     if (this.accountList == null) {
                         this.accountList = new ArrayList<>();
                     }//end if acccount List is not allocated
                     this.accountList.add(dto);
                 }
                 
                 
            }//end if con is opened
            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(con != null){
                    con.close();
                }
            }
    }
    
    public boolean deleteAccount(String username) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Login "
                        + "Where username = ?";
                //3.create statement & assign parameter value
                 stm = con.prepareStatement(sql);
                 stm.setString(1, username);
                 
                 //4.Execute Query
                 int effectRow = stm.executeUpdate();
                 //5. process
                 if(effectRow > 0) {
                     return true;
                 }
                 
            }//end if con is opened
            } finally {               
                if(stm != null) {
                    stm.close();
                }
                if(con != null){
                    con.close();
                }
            }
        return false;
    }
    
    public boolean updateAccount(String username, String password)
                throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update Login "
                        + "set password=? "
                        + "Where username = ?";
                //3.create statement & assign parameter value
                 stm = con.prepareStatement(sql);
                 stm.setString(1, password);
                 stm.setString(2, username);
                 
                 
                 //4.Execute Query
                 int effectRow = stm.executeUpdate();
                 //5. process
                 if(effectRow > 0) {
                     return true;
                 }
                 
            }//end if con is opened
            } finally {
                
                if(stm != null) {
                    stm.close();
                }
                if(con != null){
                    con.close();
                }
            }
        return false;
    }
    
    
        
}


