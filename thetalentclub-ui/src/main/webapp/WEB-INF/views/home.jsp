<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Talent Club Home Page</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/indiependant.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/fontawesome/css/font-awesome.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/indiependant.js"></script>


<script type="text/javascript">

var artistId;
var artistName;

function closeModal() {
	$("#successModal").hide();
}

function validateBudget(){
	var budgetNumeric=$("#budgetUpper").val()*1;
		var pattern = /^\d*$/;
		var flag=false;
 		if (pattern.test(budgetNumeric)){
   			flag=true;
 			} else {
   			alert('Not a valid number');
   			$("#budgetUpper").focus();
   			flag=false;
 			}
 		//alert('bbb');	
 		return flag; 
}

function budgetTrimmer(){
	var budgetVal=$("#budgetUpper").val();
	if(budgetVal.length > 9)
		{
			$("#budgetUpper").val($("#budgetUpper").val().substr(0,9))
		}
	//alert($("#budgetUpper").val().length);	

}

$(document).ready(function() {
  
	$("#search_artist").click(function() {
		if($("#budgetUpper").val() == "") {
				$("#budgetLower").val("-1");
				$("#budgetUpper").attr('disabled','disabled');
		} else {
			$("input").removeAttr('disabled');
		}
		budgetTrimmer();
		if(validateBudget())
		  {
		 	$("form[id='skillLocModel']").submit();	    
		  }	
    });
	
	$(document).on("click", "#quotationForm", function () {
		$("#modalFormContent").show();
		$("#quote-request-success").hide();
	      var artistId = $(this).data('artistid');
	      //alert('in onclick quotation form'+artistId)
	      $("#quote-artist-id").val(artistId);
		  var artistName= $(this).data('artistname');
		  //alert('in onclick quotation form'+artistName)
		  $("#myModal #artistName").html('');
		  $("#myModal #artistName").append(artistName);
	});
	
	
	$(".dropdown-menu li a").click(function(){
		var selText = $(this).text();
		$(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
	});

	$("#requestQuote").click(function() {
		
		var artistId = $("#quote-artist-id").val();
		var requestorName = $("#requestorName").val();
		var requestorEmail = $("#requestorEmail").val();
		var requestorPhone = $("#requestorPhone").val();
		var eventLocation = $("#eventLocation").val();
		
		$.post("<%=request.getContextPath()%>/artist/request-quotation", 
				"artistId="+artistId+"&requestorName="+requestorName+"&requestorEmail="+requestorEmail
					+"&requestorPhone="+requestorPhone+"&eventLocation="+eventLocation, 
					function(data) {
					   	if(data.status) {
							$("#quote-request-success").show();
							$("#modalFormContent").hide();
							
						} else {
							$("#quote-request-failure").show();
							$("#modalFormContent").hide();
						}
		   			}	
		);
	});
});	 

	  $(document).ready(function()
	   {

	   	$("#budgetUpper").keypress(function (e) {
     		//if the letter is not digit then display error and don't type anything
     		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        		//display error message
        		$("#errmsg").html("Digits Only").show().fadeOut(2000);
               		return false;
    		}
   		});
		  	             
           $("#blankRow").css('height',$(".navbar-brand").css('height'));
                
	   });	 
	   
	$(document).ready(function() {
 
  var owl = $("#owl-demo");
 
  owl.owlCarousel({
      items : 4, //10 items above 1000px browser width
      itemsDesktop : [1000,3], //5 items between 1000px and 901px
      itemsDesktopSmall : [900,2], // betweem 900px and 601px
      itemsTablet: [600,1], //2 items between 600 and 0
      itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option
  });
 
  // Custom Navigation Events

  $(".play").ready(function(){
    owl.trigger('owl.play',5000); //owl.play event accept autoPlay speed as second parameter
  })
  
 
});
	   
	</script>
	
	
	<style>
	#owl-demo .item{
  background: none;
  padding: 10px 0px;
  margin: 10px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  
}
.customNavigation{
  text-align: center;
}
//use styles below to disable ugly selection
.customNavigation a{
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}



div.thumbnail:hover {
	border-color: #bc357b;
}

div.artist-profile-wrapper {
	padding: 2px;
}

span.artist-name-span {
	font-size: 1.5em; 
	line-height: 100%; 
	color: #ffffff;
	align:center;
}

div.artist-detail-wrapper {
	padding: 2px 0;
}

.flR {
	float:right; 
}

div.price-indicator {
	margin-top: 2px;
}

.price-indicator-color {
	color:#c12e2a;
}

.iLB {
	display: inline-block;
}

span.skill-set {
	color: #666; 
	font-size: 0.85em; 
	font-style: italic;
}

div.image-separator {
	margin-bottom: 4px; 
	border-bottom: 1px solid #ccc;
}

.artist-action > button {
	width: 100%;
}

.glyphicon {
    font-size: 30px;
    color:#fff;
}
	</style>
</head>
<body>

	<div class="container-fluid"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/backgrnd.jpg);background-size:cover;background-repeat:no-repeat;background-position:center center;width:100%;z-index:-99;">

		<sf:form action="${pageContext.request.contextPath}/artist/search-results" method="GET" commandName="skillLocModel">
			<div id="hiddenParameters" class="row">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<sf:hidden path="budgetLower" value="0" />
					<sf:hidden path="pageNum" value="0" />
				</div>
			</div>
			
			
			<!-- Menu Bar Starts -->
			<%@include file="menu.jsp"%>
			<div class="row" id="blankRow">
				<div class="col-lg-12 col-xs-12 text-center"></div>
			</div>

			<div class="row" style="margin-top:150px">
				<div class="col-lg-12 col-xs-12 text-center">
					<div class="container" style="height: 100%;">
						<div class="row ">
							<div class=" col-lg-12 col-xs-12 text-center">
								<p style="font-size: 34px;" class="uxp-align-center">
									<span style="font-weight: normal;"><span
										style="color: rgb(255,255,255);">Seeking </span><span
										style="color: rgb(255, 20, 8);">Talent</span><span
										style="color: rgb(255,255,255);">, </span><span
										style="color: rgb(255, 20, 8);">Book </span><span
										style="color: rgb(255,255,255);">here!</span> </span>
								</p>
								<br>
								<p class="uxp-align-Center"
									style="font-family: Pt Serif; font-size: 18px; line-height: normal;">
									<font color="#e2e1e9">Fulfil all your talent needs.</font><span
										style="color: rgb(226, 225, 233);">Whatever the
										occasion,<br /> find the talent of your choice here!</span>
								</p>
							</div>
						</div>

						<br> <br>
						<div class="row ">
							<div class=" col-lg-12 text-center">
								<div class="row ">
									<div class=" col-lg-3 col-xs-12 text-center">
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<h4>
													<font color="white">I Am Looking For A</font>
												</h4>
											</div>
										</div>
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<sf:select path="skillId"
													cssClass="btn btn-default dropdown-toggle btn-select"
													cssStyle="width:75%;height:30px;display:inline">
													<sf:option value="">Type of Artist/Talent</sf:option>
													<sf:options items="${skills}" itemLabel="skillName"
														itemValue="skillName" />
												</sf:select>
											</div>
										</div>
									</div>
									<div class=" col-lg-3 col-xs-12 text-center">
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<h4>
													<font color="white">To Perform In</font>
												</h4>
											</div>
										</div>
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<sf:select path="locationId"
													cssClass="btn btn-default dropdown-toggle btn-select"
													cssStyle="width:75%;height:30px;display:inline">
													<sf:option value="">Select Location</sf:option>
													<sf:options items="${locations}" itemLabel="locationName"
														itemValue="locationId" />
												</sf:select>
											</div>
										</div>
									</div>
									<div class=" col-lg-3 col-xs-12 text-center">
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<h4>
													<font color="white">With a Budget of</font>
												</h4>
											</div>
										</div>
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<input id="budgetUpper" type="number" name="budgetUpper"
													placeholder="BUDGET" class="form-control input-sm"
													style="font-family: PT Sans; color: black; width: 75%; height:30px; display: inline">
													
											</div>
										</div>
										<div class="row ">
										<span id="errmsg" style="color:red"></span>
									</div>
									</div>

									<div class=" col-lg-3 col-xs-12 text-center" >
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center"><h4>
													<font color="white">Click to Go!</font>
												</h4></div>
										</div>
										<div class="row ">
											<div class="col-lg-12 col-xs-12 text-center">
												<input id="search_artist" type="button" class="btn"
													value="GO!"
													style="background-color: #ff1408; font-family: PT Sans; color: white; width: 75%; height:30px; display: inline" />
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<!--filter strip end-->
						<br>
						<!--call strip start-->
						<div class="row">
							<div class=" col-lg-12 col-xs-12 text-center">
								<div class="callQuote" style="z-index: 2;">
									<div class="row">
										<div class=" col-lg-12 col-xs-12 text-center">
											
											<p class="wysiwyg-font-type-PtSans" style="font-size: 30px;">
											<span class="glyphicon glyphicon-earphone"></span>
											
												<span style="font-weight: bold; color: rgb(255,255,255);">Call
													us on </span><span style="color: rgb(255, 20, 8);"><span
													style="font-weight: bold;">9987945957</span><span
													style="font-weight: bold;"> </span> </span><span
													style="font-weight: bold; color: rgb(255,255,255);">to
												</span><span style="font-weight: bold; color: rgb(255, 20, 8);">Find</span><span
													style="font-weight: bold; color: rgb(255,255,255);">
													and </span><span
													style="font-weight: bold; color: rgb(255, 20, 8);">Book</span><span
													style="font-weight: bold; color: rgb(255,255,255);">
													Talent</span>
											</p>

										</div>
									</div>
								</div>
							</div>
						</div><!--call strip end-->
						
						
						</div>
						</div>
						</div>
						

						<!-- footerContent -->


	<div class="hidden-xs col-sm-12 col-md-12 col-lg-12" style="padding:0px">
		
	</div>

		</sf:form>
</div>

	



</body>

</html>