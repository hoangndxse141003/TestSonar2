<%-- 
    Document   : viewCart
    Created on : Mar 6, 2021, 11:31:14 AM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="hoangndx.cart.CartOb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>H - Store</title>
    </head>
    <body>
        <h1>Your cart includes</h1>
        <%
            if (session != null) {
                CartOb cart = (CartOb) session.getAttribute("CART");

                if (cart != null) {
                    if (cart.getItems() != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="DispatchServlet">
                <tbody>
                    <%
                        Map<String, Integer> items = cart.getItems();
                        int count = 0;
                        for (String title : items.keySet()) {
                    %>
                    <tr>
                        <th>
                            <%= ++count%>
                            .</th>
                        <th>
                            <%=title%>
                        </th>
                        <th>
                            <%= items.get(title)%>
                        <td>
                            <input type="checkbox" name="chkItem" 
                                   value="<%= title%>" />
                        </td>
                        </th>
                    </tr>           
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="onlineBookStoree.jsp">Add More Book to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </form>


        </table><br>
        <form action="DispatchServlet">
            Name <input type="text" name="txtName" value="" /><br><br>
            Address <textarea name="txtAddress" rows="4" cols="20">
            </textarea><br><br>
            <input type="submit" value="Check out" name="btAction" />
        </form>

        <%
                        return;

                    }
                }
            }
        %>

        <h2>No Cart Is Existed!!!</h2>


    </body>
</html>
