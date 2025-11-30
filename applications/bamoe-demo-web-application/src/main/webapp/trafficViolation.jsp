<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Driving History</title>
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
                            <a href="trafficViolation.jsp" class="nav-item nav-link">Traffic Violations</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar & Hero End -->

        <!-- Header Start -->
        <div class="container-fluid bg-breadcrumb mb-5">
            <div class="container text-center py-5" style="max-width: 900px;">
                <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.1s">Traffic Violations</h4>
                <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-primary">Traffic Violations</li>
                </ol>    
            </div>
        </div>
        <!-- Header End -->

        <!-- Title Start -->
        <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 800px;">
            <!--<h1 class="display-5 text-capitalize mb-3">Driving History License Status</h1>-->
            <p class="mb-0">The status of a driver's license can be determined by executing a DMN-based decision model, powered by the IBM BAMOE engine.  The traffic violation decision model can be executed either as either embedded directly by the rules engine or remotely, using the business service remote REST API.</p>
            </p>
        </div>
        <!-- Title End -->

    <!-- Driver Start -->
    <div class="container py-4">
        <div class="row g-5">
            <div class="col-lg-6 fadeInLeft animated" data-animation="fadeInLeft" data-delay="1s" style="animation-delay: 1s;">
                <div class="bg-secondary rounded p-5">
                    <h4 class="text-white mb-4">Process Traffic Violation</h4>
                        <form action="TrafficViolationDecisionModelServlet" method="post">

                            <!-- Determine if we run the rules embedded or remotely -->
                            <input id="embedded" type="radio" name="executionMode" value="embedded" checked>
                            <label for="embedded">Run Embedded</label>
                            <input id="remote"   type="radio" name="executionMode" value="remote">
                            <label for="remote">Run Remotely</label>
                            </br></br>

                            <a href="#" class="text-start text-white d-block mb-2">Traffic Violation</a>
                            <div class="row g-3">
                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-calendar-alt"></span><span class="ms-1">Violation Type: </span>
                                    </div>
                                    <select required name="violationType" class="form-select" aria-label="Speed">
                                        <option selected value="speed">Speed</option>
                                        <option value="parking">Parking</option>
                                        <option value="driving under the influence">Driving Under the Influence</option>
                                    </select>
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-calendar-alt"></span><span class="ms-1">Date of Violation: </span>
                                    </div>
                                    <input name="violationDate" class="form-control" type="date">
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span> <span class="ms-1">Actual Speed: </span>
                                    </div>
                                    <input required name="actualSpeed" class="form-control" type="number" min="1" placeholder="Enter the driver's actual speed" aria-label="Enter the driver's actual speed" style="left: 0px;">
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span> <span class="ms-1">Speed Limit: </span>
                                    </div>
                                    <input required name="speedLimit" class="form-control" type="number" min="1" max="99" placeholder="Enter the speed limit" aria-label="Enter the speed limit" style="left: 0px;">
                                </div>
                                
                                <a href="#" class="text-start text-white d-block mb-2">Driver Information</a>
                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span><span class="ms-1">Driver's Name: </span>
                                    </div>
                                    <input required name="driverName" class="form-control" type="text" placeholder="Enter the driver's name" aria-label="Enter the driver's name">
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span><span class="ms-1">Driver's Age: </span>
                                    </div>
                                    <input required name="driverAge" class="form-control" type="number" min="1" max="99" placeholder="Enter the driver's age" aria-label="Enter the driver's age">
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span><span class="ms-1">Points on Driver's License: </span>
                                    </div>
                                    <input required name="driverPoints" class="form-control" type="number" default=0 min="0" max="99" placeholder="Enter the points on the driver's license" aria-label="Enter the points on the driver's license">
                                </div>
                                
                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span><span class="ms-1">City: </span>
                                     </div>
                                    <input required name="city" class="form-control" type="text" placeholder="Enter the driver's city" aria-label="Enter the driver's city">
                                </div>

                                <div class="input-group">
                                    <div class="d-flex align-items-center bg-light text-body rounded-start p-2">
                                        <span class="fas fa-map-marker-alt"></span><span class="ms-1">State: </span>
                                     </div>
                                    <input required name="state" class="form-control" type="text" placeholder="Enter the driver's state" aria-label="Enter the driver's state">
                                </div>
                            </div>

                            <div class="col-12">
                                <a class="text-start text-white d-block mb-2"></a>
                                <button class="btn btn-light w-100 py-2">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Driver End -->

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