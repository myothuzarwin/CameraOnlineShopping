<%-- 
    Document   : login
    Created on : Mar 7, 2017, 9:29:06 PM
    Author     : Myo Thuzar Win
--%>

<%@include file="header.jsp" %>

<!-- *** LOGIN MODAL END *** -->

<div id="heading-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="breadcrumb">
                    <% if (request.getAttribute("loginmsg") != null) {%>
                    <div class="alert alert-error">
                        <center>
                            <%= request.getAttribute("loginmsg")%>
                        </center>
                    </div>
                    <% }%>

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
                    <h2 class="text-uppercase">Login</h2>

                    <p class="lead">Already our customer?</p>
                    <p class="text-muted">Please enter user name and password.</p>

                    <hr>

                    <form action="Authenticate" method="post" id="frmLogin" name="frmLogin">
                        <div class="form-group">
                            <label for="userid">User Name</label>
                            <input type="text" class="form-control" id="userid" name="userid">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-template-main" id="btnLogin" name="btnLogin" value="Login" onclick="chkLogin()" ><i class="fa fa-sign-in"></i> Log in</button>
                            <input name="formstatus" id="formstatus" type="hidden" value="chklogin" />
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


<script type="text/javascript">
    function chkLogin() {
        var tmpuserid = document.getElementById("userid").value;
        var tmppassword = document.getElementById("password").value;

        if (tmpuserid == "" || tmpuserid == null || tmppassword == "" || tmppassword == null) {
            alert("Please fill required fields");
        } else {

            document.getElementById('frmLogin').submit();
        }
    }
</script>

<!-- *** GET IT ***

<%@include file="footer.jsp" %>