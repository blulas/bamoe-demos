<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>IBM BAMOE - Demo Web Application</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;0,900;1,400;1,700;1,900&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

         <!-- Navbar & Hero Start -->
         <div class="container-fluid nav-bar sticky-top px-0 px-lg-4 py-2 py-lg-0">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a href="" class="navbar-brand p-0">
                        <img src="img/bamoe.png" alt="IBM Business Automation Manager Open Editions">
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mx-auto py-0">
                            <a href="index.jsp" class="nav-item nav-link active">Home</a>
                            <a href="about.jsp" class="nav-item nav-link">About</a>
                            <a href="deposits.jsp" class="nav-item nav-link">Banking Deposits</a>
                            <a href="driver.jsp" class="nav-item nav-link">Driving History</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar & Hero End -->

        <!-- Header Start -->
        <div class="container-fluid bg-breadcrumb mb-5">
            <div class="container text-center py-5" style="max-width: 900px;">
                <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.1s">About IBM BAMOE Demo Web Application</h4>
                <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-primary">About</li>
                </ol>    
            </div>
        </div>
        <!-- Header End -->

        <!-- About Start -->
        <div class="container-fluid overflow-hidden about py-5">
            <div class="container">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 800px;">
                    <h1 class="display-5 text-capitalize mb-3">About IBM BAMOE Demo Web Application</h1>
                    <p class="mb-0">This is a sample web-based application, written in Java Server Pages and deployed to Apache Tomcat 10.  The main purpose of this sample application is to show how rules and decision models can eithe be embedded or executed remotely, using IBM Business Automation Manager Open Editions v9.3.0.</p>                    </p>

                <div class="row g-5">
                    <div class="fadeInLeft" data-wow-delay="0.2s">
                            <div class="row g-4">
                                <div class="col-lg-6">
                                    <div class="about-item-inner border p-4">
                                        <div>
                                            <a href="https://tomcat.apache.org/index.html">
                                                <img src="img/tomcat.jpeg" class="img-fluid w-50 h-50" alt="Icon">
                                            </a>
                                        </div>
                                        <h5 class="mb-3">Powered by Apache Tomcat 10</h5>
                                        <p class="mb-0">This sample JSP application, hosted by Apache Tomcat 10, executes rules & decision models using multiple execution patterns.</p>
                                        <p></p>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="about-item-inner border p-4">
                                        <div>
                                            <a href="https://www.ibm.com/docs/en/ibamoe/9.3.x?topic=overview">
                                                <img src="img/bamoe.png" class="img-fluid h-50 w-50" alt="Icon">
                                            </a>
                                        </div>
                                        <h5 class="mb-3">Powered By IBM BAMOE v9.3.0</h5>
                                        <p class="mb-0">IBM Business Automation Manager Open Editions, v9.3.0 provides stateless rules & decision models which can either be embedded or executed remotely.</p>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="about-item-inner border p-4">
                                        <div>
                                            <a href="https://www.ibm.com/docs/en/ibamoe/9.3.x?topic=overview">
                                                <img src="img/system-info.jpg" class="img-fluid h-50 w-50" alt="Icon">
                                            </a>
                                        </div>
                                        <h5 class="mb-3">System Information</h5>

                                        <table>
                                            <%

                                            final String[] propertyNames = { "Runtime", "Vendor", "Version", "OS", "TZ", "Language" };
                                            final String[] properties = { "java.runtime.name", "java.vm.vendor", "java.runtime.version", "user.timezone", "user.language", "os.name" };

                                            for (int i = 0; i < properties.length; i++) {

                                                String name = propertyNames[i];
                                                String pname = properties[i];
                                                String pvalue = System.getProperty(pname);
                                                %>
                                                <tr>
                                                <td><b><%= name %></b></td>
                                                <td><%= pvalue %></td>
                                                </tr>
                                            <% } %>
                                        </table>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-secondary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   
        
    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
    &lt;/div&gt;
</body>

</html>