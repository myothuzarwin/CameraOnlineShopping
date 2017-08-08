<%-- 
    Document   : testing3
    Created on : Jul 28, 2017, 11:35:30 PM
    Author     : myothuzar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityCollection.CommentEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <div>
                        <c:forEach var="comment" items="${requestScope.showcomment}">

                        <div class="col-md-4 col-sm-6">
                            
                            <h1>${comment.getCustomername()}::${comment.getComment()}</h1>  
                            
                            
                            
                            
                        </div>
                    </c:forEach>
                                    </div>
    </body>
</html>
