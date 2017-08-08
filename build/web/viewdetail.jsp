<%-- 
    Document   : viewdetail
    Created on : Mar 30, 2017, 5:41:51 PM
    Author     : myothuzar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.OrderdetailEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>

<%    // ArrayList<ProductEntity>  pList = ArrayList<ProductEntity> request.getAttribute("productlist");
%>


<div id="content">
    <div class="container">

        <div class="row">
            <div class="col-md-12">

            </div>


            <div class="col-md-12 clearfix" id="basket">

                <div class="box">



                    <div class="table-responsive"><table class="table">
                            <thead>
                                <tr>
                                    <th colspan="2">Product</th>
                                    <th>Quantity</th>
                                    <th>Unit price</th>


                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="orderdetail" items="${requestScope.orderdetail}">
                                    <tr>

                                        <td>
                                            <a href="#">
                                                <img src="${orderdetail.getImageURL()}" alt="${orderdetail.getProductname()}">
                                            </a>
                                        </td>
                                        <td>    <a href="#">${orderdetail.getProductname()}</a>
                                        </td>

                                        <td>
                                            ${orderdetail.getQuantity()}
                                        </td>
                                        <td>$ <fmt:formatNumber pattern="#,##0" value="${orderdetail.getPrice()}" /></td>



                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>

                    </div>
                    <!-- /.table-responsive -->




                </div>
                <!-- /.box -->


            </div>
            <!-- /.col-md-9 -->


        </div>

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->

<%@include file="footer.jsp" %>
