<%-- 
    Document   : myorder
    Created on : Mar 30, 2017, 3:23:11 PM
    Author     : myothuzar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.OrderHeaderInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>

<%   // ArrayList<ProductEntity>  pList = ArrayList<ProductEntity> request.getAttribute("productlist");
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
                                    <th>Order Date</th>
                                    <th>Order No</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                    <th>Discount Percentage</th>
                                    <th>Discount Amount</th>
                                    <th>Total Amount</th>


                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="${0}"/>    
                                <c:forEach var="orderhistory" items="${requestScope.orderhistory}">
                                    <tr>

                                        <td>
                                            ${orderhistory.getOrderdate()}
                                        </td>
                                        <td># ${orderhistory.getOrderid()}</td>
                                        <td>${orderhistory.getNo_of_product()}</td>
                                        <td>$ <fmt:formatNumber pattern="###,###,##0" value="${orderhistory.getSubTotal()}" /></td>
                                        <td>${orderhistory.getDiscount_Percentage()}%</td>
                                        <td>$ <fmt:formatNumber type="number" maxFractionDigits="2" value="${orderhistory.getDiscount_Amount()}"/></td>
                                        <td>$ <fmt:formatNumber pattern="###,###,##0.##" value="${orderhistory.getTotal_Amount()}" /></td>


                                        <td><a href="order.action?orderid=${orderhistory.getOrderid()}">View Detail</a></td>


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
