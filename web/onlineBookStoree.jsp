<%-- 
    Document   : onlineBookStoree
    Created on : Mar 21, 2021, 9:16:03 PM
    Author     : Admin
--%>

<%@page import="hoangndx.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="hoangndx.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            ProductDAO dao = new ProductDAO();
            dao.LoadProduct();
            List<ProductDTO> bookList = dao.getProductList();
            if(bookList!=null){
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Price</th>
                    <th>Add To Cart</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="BookBean" class="hoangndx.product.ProductDTO" scope="request" />
                <%
                    int count=0;
                    for(ProductDTO dto : bookList){
                %>
                        <form action="DispatchServlet">
            
                            <jsp:setProperty name="BookBean" property="id" value="<%=dto.getId()%>" />
                            <jsp:setProperty name="BookBean" property="bookname" value="<%=dto.getBookname()%>" />
                            <jsp:setProperty name="BookBean" property="price" value="<%=dto.getPrice()%>" />
                            <tr>
                                <td><%=++count%></td>
                                <td>
                                    <jsp:getProperty name="BookBean" property="id"/>
                                    <input type="hidden" name="txtBookId" value="<%=BookBean.getId()%>" />
                                </td>
                                <td>
                                    <jsp:getProperty name="BookBean" property="bookname"/>
                                    <input type="hidden" name="txtBookname" value="<%=BookBean.getBookname()%>" />
                                </td>
                                <td>
                                    <jsp:getProperty name="BookBean" property="price"/>
                                    <input type="hidden" name="txtBookPrice" value="<%=BookBean.getPrice()%>" />
                                </td>
                                <td>
                                    <input type="submit" value="Add To Cart" name="btAction"/>
                                </td>
                            </tr>
                        </form>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        <form action="DispatchServlet">
            <input type="submit" value="View Cart" name="btAction" />
        </form>
        
    </body>
</html>
