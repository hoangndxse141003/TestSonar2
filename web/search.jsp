<%-- 
    Document   : search
    Created on : Feb 24, 2021, 10:52:45 AM
    Author     : Admin
--%>


<%@page import="hoangndx.errors.UpdateErrorDTO"%>
<%@page import="hoangndx.errors.LoginErrorDTO"%>
<%--<%@page import="hoangndx.login.LoginDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Manage</title>
    </head>

    <body>
      
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <h1>Account Manage</h1>
        <form action="DispatchServlet">
            Search <input type="text" name="txtSearch" 
                          value="${param.txtSearch}" placeholder="Type here to search"/><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test ="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>

                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>

                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" 
                                                 value="${param.txtSearch}"/>
                                    </c:url>                            
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearch}" />
                                    
                                    <%
                                        UpdateErrorDTO error = (UpdateErrorDTO)session.getAttribute("UPDATEERROR");
                                        if (error != null) {
                                            if (error.getErrorpassword()!= null) {
                                    %>

                                    <font color="red">
                                    <%=error.getErrorpassword()%>
                                    </font>
                                    <%
                                                session.removeAttribute("UPDATEERROR");
                                            }
                                        }

                                    %>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>




        </c:if>          
        <c:if test ="${empty result}">
            <h2>
                no record is matched!!
            </h2>
        </c:if>


    </c:if>
        <a href="AdminPage.jsp"> Back To Admin Page</a>

    <%--<%
        Cookie[] cookies = request.getCookies();
        if(cookies!= null){
            Cookie lastCookie = cookies[cookies.length-1];
    %>
                Welcome,<%= lastCookie.getName() %>
    <%
        }
    %>
    
    
    <% 
        String searhValue = request.getParameter("txtSearch");
        if ( searhValue != null) {//first time to load searh page
            List<LoginDTO> result = 
                    (List<LoginDTO>)request.getAttribute("SEARCHRESULT");
            //out.println("abc"+result.size());
            if ( result != null) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                    </thead>
                    <tbody>
                       <%
                           int count = 0;
                           for(LoginDTO dto : result) {
                               String urlRewriting = "DispatchServlet" 
                                                        + "?btAction=delete"
                                                        + "&pk=" + dto.getUsername()
                                                        + "&lastSearchValue=" + searhValue;                                 
                               %>
                    <form action="DispatchServlet">
                    
                               <tr>
                            <td>
                            <%= ++count %>
                            .</td>
                            <td>
                            <%= dto.getUsername() %> 
                            <input type="hidden" name="txtUsername" 
                                   value="<%= dto.getUsername() %>" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" 
                                   value="<%= dto.getPassword() %>" />
                            
                            </td>
                            <td>
                            //<%= dto.getFullName() %>

                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                       <%
                                       if (dto.isRole()){
                                          %>
                                          checked="checked"
                                       <% 
                                       }//end if role is true
                                       %>
                                       />
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="<%= searhValue %>" />
                                </td>
                            </tr>
                        </form>    
                            <%
                               }
                           %> 
                        </tbody>
                    </table>

        <%
                } else {
                    %> 
                    <h2>
                        No record is matched!!!
                    </h2>
        <%
                }
            }//end if search value has inputed
        %> --%>
</body>
</html>
