<%-- 
    Document   : shoppingcart.jsp
    Created on : Mar 5, 2017, 11:23:19 AM
    Author     : myothuzar
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.ProductEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>

<%
%>


<script>

    function couponFunction() {

        var couponNo = prompt("Enter your coupon no : ");
        document.frm.commonName.value = couponNo;
        document.getElementById('frmsubmit').submit();


    }
</script>
<div id="content">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <p class="text-muted lead">You currently added ${fn:length(requestScope.shoppingcartdetail)} item(s) in your cart.</p>
            </div>


            <div class="col-md-12 clearfix" id="basket">

                <div class="box">


                    <div class="table-responsive"> 
                        <table class="table">
                            <thead>
                                <tr>
                                    <th colspan="2">Product</th>
                                    <th>Quantity</th>
                                    <th>Unit price</th>
                                    <th colspan="2">Total</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="shoppingcart" items="${requestScope.shoppingcartdetail}">
                                    <tr>
                                        <td>
                                            <a href="#">
                                                <img src="${shoppingcart.getImageURL()}" alt="${shoppingcart.getProductname()}">
                                            </a>
                                        </td>
                                        <td>    
                                            <a href="#">${shoppingcart.getProductname()}</a>
                                        </td>
                                        <td>
                                            <input type="text" value="${shoppingcart.getQty()}" class="form-control">
                                        </td>
                                        <td>$ <fmt:formatNumber pattern="#,##0" value="${shoppingcart.getPrice()}" /> </td>

                                        <td>$ <fmt:formatNumber pattern="###,##0" value="${shoppingcart.getPrice()*shoppingcart.getQty()}" /></td>
                                        <td>
                                            <a href="delete.action?qty=0&price=0&productid=${shoppingcart.getProductid()}"><i class="fa fa-trash-o"></i></a>
                                            <a href="update1.action?price=0&productid=${shoppingcart.getProductid()}&qty=${shoppingcart.getQty()}"><i class="fa fa-plus-square-o"></i></a>
                                            <a href="update2.action?price=0&productid=${shoppingcart.getProductid()}&qty=${shoppingcart.getQty()}"><i class="fa fa-minus-square-o"></i></a>

                                        </td>

                                    </tr>
                                </c:forEach>

                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="4">Sub Total</th>
                                    <th colspan="2">$ <fmt:formatNumber pattern="###,###,##0" value="${requestScope.subtotal}" /></th>
                                </tr>
                                <tr>
                                    <th colspan="4">Discount Percentage</th>
                                    <th colspan="2">${requestScope.discountper}%</th>
                                </tr>
                                <tr>

                                </tr>
                                <tr>
                                    <th colspan="4">Discount Amount</th>
                                    <th colspan="2">$ <fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.distamount}"/></th>
                                </tr>
                                <tr>

                                </tr>

                                <tr>
                                    <th colspan="4">Total</th>
                                    <th colspan="2">$ ${requestScope.totalamt}</th>
                                </tr>

                            </tfoot>
                        </table>
                    </div> 

                    <div class="box-footer">
                        <div class="pull-left">
                            <a href="productlist.action" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                        </div>
                        <div class="pull-left" >
                            &nbsp;&nbsp;&nbsp;&nbsp;

                        </div>
                        <div class="pull-left">


                            <form action="discountcustomer.action" method="post" id="frmsubmit" name="frmsubmit">   
                                <div class="pull-left">
                                    <input type="text" value="${requestScope.couponcode}" class="form-control" name="txtCouponNo">
                                </div>
                                <div class="pull-left">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="submit" class="btn btn-template-main">Apply Coupon <i class="fa fa-tags"></i>
                                    </button>
                                </div>
                            </form>
                        </div>

                        <form action="OrderAddressInit.action">             
                            <div class="pull-right">
                                <button type="submit" class="btn btn-template-main">Proceed to checkout <i class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </form>
                    </div>


                </div>
                <!-- /.box -->


            </div>
            <!-- /.col-md-9 -->

            <!--    <div class="col-md-3">
                    <div class="box" id="order-summary">
                        <div class="box-header">
                            <h3>Order summary</h3>
                        </div>
                        <p class="text-muted">Shipping and additional costs are calculated based on the values you have entered.</p>

                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>
            <!-- /.col-md-3 -->


        </div>

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->

<%@include file="footer.jsp" %>
