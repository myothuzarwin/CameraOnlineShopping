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




<section>
    <!-- *** HOMEPAGE CAROUSEL ***
_________________________________________________________ -->

    <div class="home-carousel">



        <div class="dark-mask"><h3 align="center">Top 10 Best-seller Products</h3></div>

        <div class="container">
            <div class="homepage owl-carousel"> 
                <c:forEach var="bestproduct" items="${requestScope.bestproductlist}">
                    <div class="item">
                        <div class="row">
                            <div class="col-sm-4 right">
                                <h1>${bestproduct.getProductname()}</h1>


                            </div>
                            <div class="col-sm-4">
                                <img class="img-responsive" src="${bestproduct.getPhotopath()}" alt="">
                            </div>
                            <div class="col-sm-4 left">
                                <h3>${bestproduct.getDescription()}</h3>


                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>

            <!-- /.project owl-slider -->
        </div>
    </div>

    <!-- *** HOMEPAGE CAROUSEL END *** -->
</section>




<%@include file="footer.jsp" %>