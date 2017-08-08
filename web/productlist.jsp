<%-- 
    Document   : productlist.jsp
    C<%--reated on : Mar 7, 2017, 9:09:25 PM
    Author     : Myo Thuzar Win
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.ProductEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- *** LOGIN MODAL END *** -->

<div id="heading-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <h1>Category with left sidebar</h1>
            </div>
            <div class="col-md-5">
                <ul class="breadcrumb">
                    <li><a href="index.html">Home</a>
                    </li>
                    <li>Product List by Category</li>
                </ul>

            </div>
        </div>
    </div>
</div>

<div id="content">
    <div class="container">

        <div class="row">


            <!-- *** LEFT COLUMN ***
                _________________________________________________________ -->

            <div class="col-sm-3">

                <!-- *** MENUS AND FILTERS ***
_________________________________________________________ -->
                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Categories</h3>
                    </div>

                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked category-menu">
                            <li>
                                <a href="productcategory.action?cid=1">Sports & Action Cameras </a>

                            </li>
                            <li>
                                <a href="productcategory.action?cid=2">Point & Shoot Cameras </a>

                            </li>
                            <li>
                                <a href="productcategory.action?cid=3">Drones </a>

                            </li>
                            <li>
                                <a href="productcategory.action?cid=4">Security & Gadget Camera </a>

                            </li>
                            <li>
                                <a href="productcategory.action?cid=5">Instant Camera </a>

                            </li>
                            <li>
                                <a href="productcategory.action?cid=6"> Mirrorless Cameras </a>

                            </li>
                            <li>
                                <a href="bestproduct.action"> Best Products </a>

                            </li>


                        </ul>

                    </div>
                </div>

                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Brands</h3>
                        <a class="btn btn-xs btn-danger pull-right" href="#"><i class="fa fa-times-circle"></i> <span class="hidden-sm">Clear</span></a>
                    </div>

                </div>

                <!-- *** MENUS AND FILTERS END *** -->

                <div class="banner">
                    <a href="shop-category.html">
                        <img src="img/banner.jpg" alt="sales 2014" class="img-responsive">
                    </a>
                </div>
                <!-- /.banner -->

            </div>
            <!-- /.col-md-3 -->

            <!-- *** LEFT COLUMN END *** -->

            <!-- *** RIGHT COLUMN ***
                _________________________________________________________ -->

            <div class="col-sm-9">

                <p class="text-muted lead">We offer wide selection of the best products.</p>

                <div class="row products">

                    <c:forEach var="product" items="${requestScope.productlist}">
                        
 
                        <div class="col-md-4 col-sm-6">
                            <div class="product">
                                <div class="image">
                                    <a href="productdetail.action?pid=${product.getProductid()}">${product.getProductname()}">
                                        <img src="${product.getPhotopath()}" alt="" class="img-responsive image1">
                                    </a>
                                </div>
                                <!-- /.image -->
                                <div class="text">
                                    <h3><a href="productdetail.action?pid=${product.getProductid()}">${product.getProductname()}</a></h3>

                                    <p class="price">$  <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}" /> </p>
        
       
                                    <p class="buttons">
                                        <a href="productdetail.action?pid=${product.getProductid()}" class="btn btn-default">View detail</a>
                                        <a href="productdetail.action?pid=${product.getProductid()}" class="btn btn-template-main"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </p>
                                </div>
                                <!-- /.text -->
                            </div>
                            <!-- /.product -->
                        </div>

                    </c:forEach>
                   
                                   
                    
                        
                    <!--
                    <input name="rate" id="rate" type="hidden" value="${ratecount}" />
                    -->

                    <!-- /.col-md-4 -->
                </div>
                <!-- /.products -->

            </div>
            <!-- /.col-md-9 -->

            <!-- *** RIGHT COLUMN END *** -->

        </div>

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->

<%@include file="footer.jsp" %>




