<%-- 
    Document   : orderreview.jsp
    Created on : Mar 7, 2017, 5:37:48 PM
    Author     : myothuzar
--%>



<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.ProductEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>s
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>

<script>
    function myFunction() {
        alert("Place Order is Successful");


    }
</script>


<div id="content">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <p class="text-muted lead"><h2>CHECKOUT- ORDER REVIEW</h2></p>
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
                                <c:set var="total" value="${0}"/>

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
                                        <td>$ <fmt:formatNumber pattern="#,##0" value="${shoppingcart.getPrice()}" /></td>
                                        <td>$ <fmt:formatNumber pattern="###,##0" value="${shoppingcart.getPrice()*shoppingcart.getQty()}" /></td>


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
                                    <th colspan="2">$ <fmt:formatNumber pattern="###,###,##0.##" value="${requestScope.totalamt}" /></th>
                                </tr>

                            </tfoot>
                        </table>

                    </div>
                    <!-- /.table-responsive -->

                    <div class="box-footer">
                        <div class="pull-left">
                            <a href="productlist.action" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                        </div>
                        <div class="pull-right">
                            <form action="placeorder.action" method="post" id="frmCheckout" name="frmCheckout" autocomplete="off" >
                                <button type="submit" onclick="myFunction()" class="btn btn-template-main" value="Place an order" id="btnplaceorder"  />Place an Order <i class="fa fa-chevron-right"></i>


                                <input name="formstatus" id="formstatus" type="hidden" value="placeorder" />
                                </button> </form>
                        </div>
                    </div>



                </div>
                <!-- /.box -->


            </div>
            <!-- /.col-md-9 -->

        </div>

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->


<!--    <table>
<c:forEach var="shoppingcart" items="${requestScope.shoppingcartdetail}">
    <tr>
        <td>Product Id: ${shoppingcart.getProductid()}</td>
        <td>Product Name: ${shoppingcart.getProductname()}</td>
        <td>Product Price: ${shoppingcart.getPrice()}</td>
        <td>Product Quantity : ${shoppingcart.getQty()}</td>
        <td>Total Amount: ${shoppingcart.getPrice()*shoppingcart.getQty()} </td>
    </tr>
</c:forEach>
</table>
<form action="CheckoutController" method="post" id="frmCheckout" name="frmCheckout" autocomplete="off" >
<input type="submit" value="Place an order" id="btnplaceorder"  />
<input name="formstatus" id="formstatus" type="hidden" value="placeorder" />
</form>
</div>
</div>
-->
<%@include file="footer.jsp" %>

