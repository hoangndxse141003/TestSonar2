<%-- 
    Document   : onlineBookStore
    Created on : Mar 9, 2021, 6:06:59 PM
    Author     : Admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="hoangndx.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>H Store</title>
    </head>
    <body>
        <h1>H Store</h1>
        <c:set var="result" value="${sessionScope.LOADBOOK}"/>
        <c:if test="${not empty result}">
            
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>ID</th>
                            <th>Book</th>
                            <th>Price</th>
                            <th>Add to card</th>                            
                        </tr>
                    </thead>
                    <tbody>                    
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet" name="cbo">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.id}
                                </td>
                                <td>
                                    ${dto.bookname}
                                </td>
                                <td>
                                    ${dto.price}
                                </td>
                                <td>
                                    <input type="submit" value="Add Book To Cart" name="btAction" />
                                    <input type="hidden" name="txtBookname" value="${dto.bookname}" />
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
        <form action="DispatchServlet">
            <input type="submit" value="View Cart" name="btAction" />
        </form>
    </body>
</html>
