<%-- 
    Document   : AdminPage
    Created on : Mar 21, 2021, 6:13:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
          <%
        Cookie[] cookies = request.getCookies();
        if(cookies!= null){
            Cookie lastCookie = cookies[cookies.length-1];
    %>
    <%
        }
    %>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName} 
        </font>
        <h1>Admin Page</h1>
        
        
        <form action="DispatchServlet">
            <input type="submit" value="Manage Account" name="btAction" />
            <input type="submit" value="Manage Book" name="btAction" /><br><br>
            <input type="submit" value="Log Out" name="btAction" />
        </form>
    </body>
</html>
