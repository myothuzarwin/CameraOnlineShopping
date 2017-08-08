


<%-- 
    Document   : newcustomer
    Created on : Mar 7, 2017, 10:17:35 PM
    Author     : Myo Thuzar Win
--%>

<%@include file="header.jsp" %>
<!-- *** LOGIN MODAL END *** -->

<div id="heading-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <% if (request.getAttribute("loginmsg") != null) { %>
                <div class="alert alert-error">
                    <center>
                        Login information is incorrect!
                    </center>
                </div>

                <% }%>

                <ul class="breadcrumb">

                    <li><a href="index.html">Sign in - Online Shopping Application</a>
                    </li>
                    <li>Sign in</li>
                </ul>

            </div>
        </div>
    </div>
</div>

<div id="content">
    <div class="container">

        <div class="row">

            <div class="col-md-12">

                <div class="box">
                    <h2 class="text-uppercase">New account</h2>
                    <p class="lead">Not our registered customer yet?</p>
                    <p>With registration with us new world of cameras, fantastic discounts and much more opens to you! The whole process will not take you more than a minute!</p>

                    <form action="singnup.action" method="post" id="frmSignup" name="frmSignup" autocomplete="off" onsubmit="return Validate()">
                        <div class="form-group">
                            <label for="customer_name">Name</label>
                            <input type="text" class="form-control" name="customer_name" id="customername">
                        </div>

                        <div class="form-group">
                            <label for="birthday">Birthday</label>
                            <input type="text" name="birthday" id="datepicker" class="form-control datepicker" data-date-format="dd/mm/yyyy">
                        </div>

                        <div class="form-group">
                            <label for="gender"> What is your gender? </label>
                            <br><br>
                            <input type="radio"  name="gender" value="male"> Male

                            <input type="radio"  name="gender" value="female"/> Female
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" name="address" id="address">
                        </div>

                        <div class="form-group">
                            <label for="postal_code">Postal Code</label>
                            <input type="text" class="form-control" name="postal_code" id="postal_code">
                        </div>

                        <div class="form-group">
                            <label for="phone_no">Phone No</label>
                            <input type="text" class="form-control" name="phone_no" id="phone_no">
                        </div>

                        <div class="form-group">
                            <label for="e_mail">Email</label>
                            <input type="email" class="form-control" name="e_mail" id="e_mail" >
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password">
                        </div>
                        <div class="form-group">
                            <label for="password">Re-enter Password</label>
                            <input type="password" class="form-control" name="confirmpassword" id="confirmpassword">
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-template-main" value="Sign Up" id="btnSignup" onclick="signup()"><i class="fa fa-user-md"></i> Register</button> 
                            <input name="formstatus" id="formstatus" type="hidden" value="signup" />
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
        $('#frmSignup').validate({// initialize the plugin
            rules: {
                email: {
                    required: true,
                    email: true
                },
                postal_code: {
                    required: true,
                    minlength: 5
                }
            }
        });

        $('#datepicker').datepicker({
            format: 'dd/mm/yyyy',
            todayBtn: 'linked'
        });



    };
</script>

<script type="text/javascript">
    function signup() {
        var tmpcustomername = document.getElementById("customername").value;
        var tmpbirthday = document.getElementById("datepicker").value;
        var tmpgender_m = document.getElementById("gender_m").value;
        var tmpgender_f = document.getElementById("gender_f").value;
        var tmpaddress = document.getElementById("address").value;
        var tmppostal_code = document.getElementById("postal_code").value;
        var tmpphone_no = document.getElementById("phone_no").value;
        var tmpe_mail = document.getElementById("e_mail").value;
        var tmppassword = document.getElementById("password").value;
        if (tmpcustomername === "" || tmpcustomername === null || tmpbirthday === "" || tmpbirthday === null ||
                tmpgender_m === "" || tmpgender_m === null || tmpgender_f === "" || tmpgender_f === null ||
                tmpaddress === "" || tmpaddress === null || tmppostal_code === "" || tmppostal_code === null ||
                tmpphone_no === "" || tmpphone_no === null || tmpe_mail === "" || tmpe_mail === null ||
                tmppassword === "" || tmppassword === null)
        {
            alert("Please fill required fields");
        } else {

            document.getElementById('frmSignup').submit();
        }
    }

    function Validate() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmpassword").value;
        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;

        }
        document.getElementById('frmSignup').submit();
    }



</script>

<%@include file="footer.jsp" %>