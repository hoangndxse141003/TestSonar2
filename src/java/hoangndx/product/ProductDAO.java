/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.product;

import hoangndx.untils.DBHelpers;
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
public class ProductDAO {
    
    private List<ProductDTO> ProductList;
    public List<ProductDTO> getProductList(){
        return ProductList;
    }
   
    public void LoadProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if(con != null) {
                String sql = "Select ID, BookName, Price, Quantity "
                        + "From Book" ;
                stm=con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String id = rs.getString("ID");
                    String bookname = rs.getString("BookName");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    ProductDTO dto = new ProductDTO(id, bookname, price, quantity);
                    if (this.ProductList==null) {
                        this.ProductList = new ArrayList<>();
                    }
                    this.ProductList.add(dto);
                }
                 
                                      
            }
        } finally {
             if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
    