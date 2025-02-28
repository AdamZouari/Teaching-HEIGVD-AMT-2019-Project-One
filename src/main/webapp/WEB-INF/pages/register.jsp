<!--
=========================================================
* Material Kit - v2.0.6
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit
* Copyright 2019 Creative Tim (http://www.creative-tim.com)
Licensed under MIT (https://github.com/creativetimofficial/material-kit/blob/master/LICENSE.md)


=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="./assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>
        Chillout - Register
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
          name='viewport'/>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <!-- CSS Files -->
    <link href="./assets/css/material-kit.css?v=2.0.6" rel="stylesheet"/>

</head>

<body class="login-page sidebar-collapse">

<div class="page-header header-filter" style="background-image: url('./assets/img/bg8.jpg'); background-size: cover; background-position: top center;">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 ml-auto mr-auto">
                <div class="card card-login">
                    <form class="form" method="post" action="./register">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">Register</h4>
                        </div>
                        <label>
                            Name
                            <input name="name" type="text" class="form-control" placeholder="Name">
                        </label>
                        <label>
                            Username
                            <input name="username" type="text" class="form-control" placeholder="Username">
                        </label>
                        <label>
                            Password
                            <input name="password" type="password" class="form-control" placeholder="Password">
                        </label>
                        <label>
                            Confirm Password
                            <input name="password_repeat" type="password" class="form-control" placeholder="Password">
                        </label>
                        <button class="btn btn-danger btn-block">Register</button>
                        <p>
                            ${requestScope.error}
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<!--   Core JS Files   -->
<script src="./assets/js/core/jquery.min.js" type="text/javascript"></script>
<script src="./assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="./assets/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
<script src="./assets/js/plugins/moment.min.js"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script src="./assets/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="./assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="./assets/js/material-kit.js?v=2.0.6" type="text/javascript"></script>
</body>

</html>
