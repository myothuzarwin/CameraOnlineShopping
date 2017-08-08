<%-- 
    Document   : deliverymethod
    Created on : Mar 7, 2017, 11:21:26 PM
    Author     : myothuzar
--%>

<%@include file="header.jsp" %>
<div id="content">
    <div class="container">

        <div class="row">

            <div class="col-md-12">
                <div class="box">
                    <h2 class="text-uppercase"> Delivery Method</h2>


                    <p class="text-muted">Please choose the ONE Delivery Method .</p>

                    <hr>

                    <form action="deliverymethod.action" id="frmDelivery" name="frmDelivery"  method="post">

                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="delivery" name="delivery" type="radio" value="ideliverysg" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">iDelivery SG</span>
                            </label>
                        </div>

                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="delivery" name="delivery" type="radio" value="parcelexpress" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Parcel Express</span>
                            </label>
                        </div>


                        <div class="form-group">
                            <label class="custom-control custom-radio">
                                <input id="delivery" name="delivery" type="radio" value="ups" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">UPS Delivery</span>
                            </label>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-template-main" id="btndeliverymethod" name="btndeliverymethod" value="Continue to Choose Payment method"  ><i class="fa fa-sign-in"></i> Continue to Choose Payment method</button>
                            <input name="formstatus" id="formstatus" type="hidden" value="delivery" />
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
