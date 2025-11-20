<!-- Imports -->
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ibm.bamoe.demos.model.Deposit"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Banking Deposits</title>
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
            <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.1s">Banking Deposit Validation</h4>
            <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                <li class="breadcrumb-item active text-primary">Banking Deposits</li>
            </ol>    
        </div>
    </div>
    <!-- Header End -->

    <!-- Title Start -->
    <div class="container">
        <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 800px;">
            <p class="mb-0">Show below are the results of invoking the Banking Deposit rules, including the deposit validation results as well as information recorded by the rules engine.</p>
        </div>
    </div>
    <!-- Title End -->

    <!-- Bank Deposit Submission Start -->
    <div class="container py-4">
        <div class="row g-5">
            <div class="col-lg-6 fadeInLeft animated" data-animation="fadeInLeft" data-delay="1s" style="animation-delay: 1s;">
                <div class="bg-secondary rounded p-5">
                    <h4 class="text-white mb-4">Bank Deposit Results</h4>
                        <form>
                            <a href="#" class="text-start text-white d-block mb-2">Deposit Results</a>
                            <div class="row g-3">
                                <%
                                    // List the updated facts
                                    out.print(request.getAttribute("deposit"));
                                    out.print("<br/>");
                                %>
                            </div>
                            
                            <br/>
                            
                            <a href="#" class="text-start text-white d-block mb-2">Rule Execution Results</a>
                            <div class="row g-3">
                                <%
                                    out.print("Started On: " + request.getAttribute("startedOn"));
                                    out.print("<br/>");
                                    out.print("Completed On: " + request.getAttribute("completedOn"));
                                    out.print("<br/>");
                                    out.print("Execution Duration: " + request.getAttribute("executionDuration"));
                                    out.print("<br/>");
                                    out.print("Fired Rule Count: " + request.getAttribute("firedRuleCount"));
                                    out.print("<br/>");

                                    // List the rules that fired
                                    out.print("Rules Fired: ");
                                    out.print("<br/>");

                                    List<String> rulesFired = (ArrayList<String>) request.getAttribute("rulesFired");
                                    for (String firedRule : rulesFired) {

                                        out.print(firedRule);
                                        out.print("<br/>");
                                    }
                                %>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Bank Deposit Submission End -->

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
    </body>

</html>