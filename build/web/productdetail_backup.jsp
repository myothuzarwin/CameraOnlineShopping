<%-- 
    Document   : productdetail
    Created on : Mar 1, 2017, 2:41:03 PM
    Author     : myothuzar
--%>

<%@ page import="java.util.*, javax.naming.InitialContext" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<link href="css/rating.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link href="css/ratestar.css" rel="stylesheet">


<div id="content">
     <%@ include file = "rating.jsp" %> 
     
    <div class="container">

        <div class="row">

            <!-- *** LEFT COLUMN ***
            _________________________________________________________ -->

            <div class="col-md-12">

                <p class="lead">Built purse maids cease her ham new seven among and. Pulled coming wooded tended it answer remain me be. So landlord by we unlocked sensible it. Fat cannot use denied excuse son law. Wisdom happen suffer common the appear ham beauty
                    her had. Or belonging zealously existence as by resources.
                </p>
                <p class="goToDescription"><a href="#details" class="scroll-to text-uppercase">Scroll to product details, material & care and sizing</a>
                </p>
                

                <div class="row" id="productMain">
                    <div class="col-sm-6">
                        <div id="mainImage">

                            <img src=" ${myProductDetail.getPhotopath()}" alt="" class="img-responsive">
                        </div>

                        <div class="ribbon sale">
                            <div class="theribbon">SALE</div>
                            <div class="ribbon-background"></div>
                        </div>
                        <!-- /.ribbon -->

                        <div class="ribbon new">
                            <div class="theribbon">NEW</div>
                            <div class="ribbon-background"></div>
                        </div>
                        <!-- /.ribbon -->

                    </div>
                    <div class="col-sm-6">
                        <div class="box">

                            <form action="Addtocart.action" method="post" id="frmLogin" name="frmLogin">
                                <div class="sizes">

                                    <h3>Available Qty    ${myProductDetail.getQty()}</h3>

                                </div>

                                <p class="price">$ <fmt:formatNumber pattern="#,##0" value="${myProductDetail.getPrice()}" /></p>
                                <div class="sizes"><h3>Rate: 
                                        <fmt:formatNumber pattern="#.0" value="<%=request.getAttribute("ratecount")%>" /></h3>
                                </div>
                                <p class="text-center">
                                    <button type="submit" class="btn btn-template-main"><i class="fa fa-shopping-cart"></i> Add to cart</button>
                                </p>
                                <input name="formstatus" id="formstatus" type="hidden" value="addtocart" />

                                <input name="productid" id="productid" type="hidden" value="${myProductDetail.getProductid()}" />
                                <input name="productname" id="productname" type="hidden" value="${myProductDetail.getProductname()}" />
                                <input name="qty" id="qty" type="hidden" value="${myProductDetail.getQty()}" />
                                <input name="price" id="price" type="hidden" value="${myProductDetail.getPrice()}" />
                                <input name="photopath" id="photopath" type="hidden" value="${myProductDetail.getPhotopath()}" />

                            </form>
                        </div>


                    </div>
                              

                    <a href="rating.jsp?pid=${myProductDetail.getProductid()}">Leave a review</a> 
                    &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp  
                    <a href="viewcomment.action?pid=${myProductDetail.getProductid()}">View Review</a>

                    <form accept-charset="UTF-8" action="rating.action?pid=${myProductDetail.getProductid()}" method="post">

                        <select name="rate">
                            <option value="1">Normal</option>
                            <option value="2">Good</option>
                            <option value="3">Better</option>
                            <option value="4">Best</option>
                            <option value="5">Excellent</option>
                        </select>

                        <!--  <input type="button" name="Submit" value="Rate" onclick="submit()" /> -->

                        <button type="submit" class="btn btn-template-main" id="btnLogin" name="btnLogin" value="Rate" onclick="submit()" ><i class="fa fa-sign-in"></i> Rate </button>
                    </form>
                    
                    
                   
                    
                   

                </div>

                <div class="box" id="details">
                    <p>
                    <h4>Product details</h4>
                    <p>${myProductDetail.getProductname()}</p>


                    <h4>Description</h4>
                    <p>
                        ${myProductDetail.getDescription()}

                    </p>
                </div>



            </div>

            <!-- *** RIGHT COLUMN END *** -->

        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->
<script type="text/javascript">
                            function submit() {
                                var e = document.getElementsByName("rate");
                                var strUser = e.options[e.selectedIndex].value;



                                document.getElementsByName("rate")[0].value = strUser;
//document.getElementsByName('ddlViewBy')[0].elements = strUser;


                                document.form[0].submit();
                            }
</script>


<%@include file="footer.jsp" %>

