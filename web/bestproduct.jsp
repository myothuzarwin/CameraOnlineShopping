<%-- 
    Document   : login
    Created on : Mar 7, 2017, 9:29:06 PM
    Author     : Myo Thuzar Win
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.ProductEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp" %>



<h1>Hello Everyone</h1>


<div>



    <c:forEach var="bestproduct" items="${requestScope.bestproductlist}">
        <a>
            <img src="${bestproduct.getPhotopath()}">
        </a>
        <h3>${bestproduct.getProductname()}</h3>



    </c:forEach>
</div>

       





<%@include file="footer.jsp" %>