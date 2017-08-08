<%-- 
    Document   : orderaddress.jsp
    Created on : Mar 7, 2017, 2:18:21 PM
    Author     : myothuzar
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityCollection.CustomerEntity"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="header.jsp" %>


<c:set var="customer" value="${requestScope.customer}" scope="request"/> 

<div id="content">
    <div class="container">

        <div class="row">

            <div class="col-md-12">
                <div class="box">
                    <h2 class="text-uppercase"> Receiver address </h2>


                    <p class="text-muted">Please fill the following information.</p>

                    <hr>

                    <form action="orderaddress.action" method="post" id="frmCheckout" name="frmCheckout" autocomplete="off">
                        <div class="form-group">
                            <label for="receivername"> Receiver Name: </label>
                            <input type="text" class="form-control" id="receivername" name="receiver_name" value="${requestScope.customer.getCustomername()}">
                        </div>

                        <div class="form-group">
                            <label for="address"> Address: </label>
                            <input type="text" class="form-control" id="address" name="address"
                                   value="${customer.getAddress()}"  
                                   >
                        </div>
                        <div class="form-group">
                            <label for="postalcode">Postal Code: </label>
                            <input type="text" class="form-control" id="postalcode" name="postalcode"
                                   value="${customer.getPostalcode()}"        
                                   >
                        </div>
                        <div class="form-group">
                            <label for="telephoneno"> Telephone No: </label>
                            <input type="text" class="form-control" id="telephoneno" name="telephoneno"
                                   value="${customer.getPhoneno()}"        
                                   >
                        </div>
                        <div class="form-group">
                            <label for="email">Email: </label>
                            <input type="email" class="form-control" id="email" name="email"
                                   value="${customer.getEmailaddress()}"       
                                   >
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-template-main" id="btnreceiveraddress" name="btnreceiveraddress" value="Continue to delivery method" onclick="receiveraddress()" ><i class="fa fa-sign-in"></i> Continue to delivery method</button>
                            <input name="formstatus" id="formstatus" type="hidden" value="receiveraddress" />
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


<script>

    window.onload = function () {
        $('#frmCheckout').validate({// initialize the plugin
            rules: {
                email: {
                    required: true,
                    email: true
                },
                postalcode: {
                    required: true,
                    minlength: 5
                }
            }
        });
    };



</script>

<script type="text/javascript">
    function receiveraddress() {
        var tmpreceivername = document.getElementById("receivername").value;
        //    var tmpcompanyname = document.getElementById("company").value;
        var tmpaddress = document.getElementById("address").value;
        var tmppostalcode = document.getElementById("postalcode").value;
        var tmptelephoneno = document.getElementById("telephoneno").value;
        var tmpemail = document.getElementById("email").value;
        if (tmpreceivername == "" || tmpreceivername == null ||
                tmpaddress == "" || tmpaddress == null || tmppostalcode == "" || tmppostalcode == null ||
                tmptelephoneno == "" || tmptelephoneno == null || tmpemail == "" || tmpemail == null)
        {
            alert("Please fill required fields");
        } else {

            document.getElementById('frmCheckout').submit();
        }
    }
</script>

<%@include file="footer.jsp" %>
