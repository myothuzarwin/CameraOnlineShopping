<%-- 
    Document   : paymentmethod.jsp
    Created on : Mar 7, 2017, 5:12:43 PM
    Author     : myothuzar
--%>
<%@include file="header.jsp" %>

<div id="content">
    <div class="container">

        <div class="row">

            <div class="col-md-12">
                <div class="box">
                    <h2 class="text-uppercase"> Payment Method</h2>


                    <p class="text-muted">Please choose the ONE Payment Method .</p>

                    <hr>
                    <form action="paymentmethod.action" id="frmCheckout" name="frmCheckout"  method="post">

                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="paypal" name="payment" type="radio" value="paypal" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Paypal</span>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="master_visa" name="payment" type="radio" value="master_visa" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Master & Visa</span>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="COD" name="payment" type="radio" value="COD" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Cash On Delivery</span>
                            </label>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-template-main" id="btnpaymentmethod" name="btnpaymentmethod" value="Continue to Order Review"  ><i class="fa fa-sign-in"></i> Continue to Order Review</button>
                            <input name="formstatus" id="formstatus" type="hidden" value="paymentmethod" />
                        </div>

                    </form>
                </div>
            </div>

        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->
</div>
<!-- /#content -->
<%@include file="footer.jsp" %>








