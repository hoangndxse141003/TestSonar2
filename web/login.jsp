<%-- 
    Document   : login
    Created on : Mar 19, 2021, 8:19:04 AM
    Author     : Admin
--%>

<%@page import="hoangndx.errors.LoginErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <body>
    <h1>Login Page</h1>
    <%
        LoginErrorDTO errorDTO = (LoginErrorDTO) request.getAttribute("ERROR");
    %>
    <form action="DispatchServlet" method="POST">
        Username <input type="text" name="txtUsername" value="" /><br> 
        <%
            if (errorDTO != null) {
                if (errorDTO.getErrorusername() != null) {
        %>
        <font color="red">
        <%=errorDTO.getErrorusername()%> 
        </font><br/>
        <%
                }
            }
        %>

        Password <input type="password" name="txtPassword" value="" /><br>
        <%
            if (errorDTO != null) {
                if (errorDTO.getErrorpassword() != null) {
        %>
        <font color="red">
        <%=errorDTO.getErrorpassword()%> 
        </font><br/>
        <%
                }
            }
        %>
        <input type="submit" value="Login" name="btAction" /><br/>
    </form>
    <form action="DispatchServlet">
        <input type="submit" value="Click here to buy book" name="btAction" />
    </form>
    <!--        <title>Login</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
        <body>
            <h1>Login Page</h1>
            <form action="DispatchServlet" method="POST">
                Username <input type="text" name="txtUsername" required="" /><br>            
                Password <input type="password" name="txtPassword" required="" /><br>
                <input type="submit" value="login" name="btAction" /><br>
            </form>
            <form action="DispatchServlet">
                <input type="submit" value="Click here to buy book" name="btAction" />
            </form>
            <br/>
        </body>-->
    </body>
</html>
