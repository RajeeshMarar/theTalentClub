<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html lang="en" style="height:100%">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Artist Search Page</title>

      <!-- Bootstrap -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/indiependant.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap-combobox/css/bootstrap-combobox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/jqueryui-range-slider/css/jquery-ui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/fontawesome/css/font-awesome.css">
    
    
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap-combobox/js/bootstrap-combobox.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jqueryui-range-slider/js/jquery-ui.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
      
    <style type="text/css">
            .border-shadow:hover {
        border-color: #bc357b;
        box-shadow: 0 0 2px 0 #bc357b;
        -moz-box-shadow:  0 0 2px 0 #bc357b;
          -webkit-box-shadow: 0 0 2px 0 #bc357b;
      }
      
      div.artist-profile-wrapper {
        padding: 2px;
      }
      
      span.artist-name-span {
        font-size: 1.5em; 
        line-height: 100%; 
        color: #c12e2a;
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

      .truncate {
        width: 100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        display:inline-block;
      }

    </style>

    <script type="text/javascript">
      $(document).ready(function() {


        $(document).on("click", ".quotationForm", function () {
            
                 $(".modal-backdrop").css('z-index',0);
       });
 


          
        $('.combobox').combobox();
        $("#pageNum").val(0);
        $("input[placeholder='Select Skill']").val($("input[placeholder='Select Skill']").val().trim());
        
       
        
        $("#select_location").change(function() {
          //alert('chalo na...');
          if($("#select_location").val()!="") {
            $("form[id='skillLocModel']").submit();
          }
        });
        
        $("#select_skill").change(function() {
          if($("#select_skill").val()!="") {
            $("form[id='skillLocModel']").submit();
          }
        });
        
        $("#slider-range" ).on( "slidechange", function( event, ui ) {
          $("#budgetLower").val(ui.values[ 0 ]);
          $("#budgetUpper").val(ui.values[ 1 ]);
          $("form[id='skillLocModel']").submit();
        });

        $("#slider-range_small_device" ).on( "slidechange", function( event, ui ) {
          $("#budgetLower_small_device").val(ui.values[ 0 ]);
          $("#budgetUpper_small_device").val(ui.values[ 1 ]);
          //$("form[id='skillLocModel']").submit();
        });
        
        $("#slider-range" ).slider({
          range: true,
          min: 0,
          max: 500000,
          <c:choose>
            <c:when test="${budgetLower=='-1'}">
              values: [ 0, 500000 ],
            </c:when>
            <c:otherwise>
              values: [ ${budgetLower}, ${budgetUpper} ],
            </c:otherwise>
          </c:choose>
          slide: function( event, ui ) {
            $( "#amount" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
          }
        });

        $("#slider-range_small_device" ).slider({
          range: true,
          min: 0,
          max: 500000,
          <c:choose>
            <c:when test="${budgetLower=='-1'}">
              values: [ 0, 500000 ],
            </c:when>
            <c:otherwise>
              values: [ ${budgetLower}, ${budgetUpper} ],
            </c:otherwise>
          </c:choose>
          slide: function( event, ui ) {
            $( "#amount_small_device" ).val( "" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
          }
        });

        $("#amount").val( "" + $( "#slider-range" ).slider( "values", 0 ) +
                " - " + $( "#slider-range" ).slider( "values", 1 ) );

        $("#amount_small_device").val( "" + $( "#slider-range_small_device" ).slider( "values", 0 ) +
                " - " + $( "#slider-range_small_device" ).slider( "values", 1 ) );

        $("#sidebar_button").click(function(){
          $("#sidebar_small_device").toggle();
          $(this).find("span.glyphicon").toggleClass("glyphicon-filter glyphicon-remove");
        });

        $("#select_location_small_device").change(function() {
          $("[placeholder='Select Skill']").each(function( index ){
                    $($("[placeholder='Select Location']")[index]).val($($("[placeholder='Select Location']")[index]).val().trim());
                  });
        });

        $("#select_skill_small_device").change(function() {
          $("[placeholder='Select Skill']").each(function( index ){
                    $($("[placeholder='Select Skill']")[index]).val($($("[placeholder='Select Skill']")[index]).val().trim());
                  });
          
        });


        $("#search-asrtist-btn").click(function() {           	
                $("#select_location").val($("#select_location_small_device").val());
                $("input[name=locationId]").val($("#select_location_small_device").val());
                $("#location").val($("#select_location_small_device").val());

                $("#select_skill").val($("#select_skill_small_device").val());
                $("input[name=skillId]").val($("#select_skill_small_device").val());
                $("#skillId").val($("#select_skill_small_device").val());

                $("#budgetLower").val($("#budgetLower_small_device").val());
                $("#budgetUpper").val($("#budgetUpper_small_device").val());
                $("#amount").val($("#amount_small_device").val());  

                $("form[id='skillLocModel']").submit();
          });
      });

      $(function () {
        $('[data-toggle="tooltip"]').tooltip();
      });

    </script>
  </head>

<body style="height:100%">

<div class="" style="height:100%">
  <div class="header" >
    <!-- Menu Bar Starts -->
			<%@include file="menu.jsp"%>
  </div>
  <div class="row" style="position: relative;margin: auto;height:125%;margin-top:70px;">
    <div class="hidden-xs col-sm-2 col-md-2 col-lg-2 sidebar-faltu" style="height: 100%;padding-right:0px">
    </div>  
      <div class="hidden-xs col-sm-2 col-md-2 col-lg-2 sidebar" style="height: 100%;padding-right:0px;position:fixed">
        <div id="formDiv" style="padding-top:0%">
          <form id="skillLocModel" action="search-results" method="GET">            
            <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="form-inline form-group">
                  <select id="select_location" name="locationId" class="combobox form-control">
                    <option value="">Select Location</option>
                    <c:forEach var="locationIter" items="${locations}">
                        <c:choose>
                          <c:when test="${locationIter.locationId == location}">
                          <option value="${locationIter.locationId}" 
                              selected>${locationIter.locationName}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${locationIter.locationId}">
                            ${locationIter.locationName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>                
                  </select>
                </div>
    
              </div>
            </div>
        
            <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="form-inline form-group">
                  <select id="select_skill" name="skillId" class="combobox form-control">
                    <option value="">Select Skill</option>
                    <c:forEach var="skillIter" items="${skills}">
                        <c:choose>
                          <c:when test="${skillIter.skillName == skillId}">
                          <option value="${skillIter.skillName}" selected>
                            ${skillIter.skillName}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${skillIter.skillName}">
                            ${skillIter.skillName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>                
                  </select>
                </div>
    
              </div>
            </div>
        
            <div class="row">
              <div id = "filter_label_div" class="col-xs-12 col-sm-12 col-md-12">           
                <p>
                <label for="amount">FILTER BY PRICE ( <span class='fa fa-rupee fa fa-1x'></span> )</label> 
                </p>
              </div>
            </div>
            
            <div class="row">
              <div id = "sliderDiv" class="col-xs-12 col-sm-12 col-md-12">
                <div id="slider-range"></div>
                <input type="text" id="amount" readonly style="border:0; color:black; font-weight:bold;text-align:center;">
              </div>
            </div>
        
              <div id="hiddenValues" class="row">
              <div class="col-xs-12 col-sm-12 col-md-12">
                <input id="location" type="hidden" value="${location}"> 
                <input id="skillId"  type="hidden" value="${skillId}"> 
                <input id="budgetLower"  name="budgetLower" type="hidden" value="${budgetLower}"> 
                <input id="budgetUpper"  name="budgetUpper" type="hidden" value="${budgetUpper}">
                <input id="pageNumParam" name="pageNum"     type="hidden" value="0">    
                <input id="pageNum"      type="hidden"    value="${pageNum}">
                <input id="budgetLower_small_device" type="hidden" value="${budgetLower}"> 
                <input id="budgetUpper_small_device" type="hidden" value="${budgetUpper}">
                
              </div>
            </div>
          </form> 
        </div>   
      </div>
      
      <div class="col-xs-12 col-sm-10 col-md-10 col-lg-10 main" style="height:100%;overflow-y:scroll;"> 
         <div class="row artistDiv" style="padding-left : 10px;padding-right:10px">
            <c:forEach var="artist" items="${artistList}" varStatus="loopCounter">
              
            <!--new artist box design start here -->  
              <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4" id="artist-id-${artist.artistId}">
                <div class="thumbnail border-shadow">               
                  <div class="artist-profile-wrapper">
                    <div class="artist-name-wrapper" itemprop="name">
                      <span class="artist-name-span truncate" data-toggle="tooltip" data-placement="auto" title="${artist.firstName} ${artist.  lastName}">${artist.firstName} ${artist.lastName}
                      </span>
                    </div>
                    <div class="artist-detail-wrapper">
                      <div class="flR price-indicator">
                        <c:forEach var="priceCounter" begin="1" end="5">
                          <c:choose>
                            <c:when test="${priceCounter le artist.priceIndicator}">
                              <span class="fa fa-rupee fa fa-1x price-indicator-color">
                                <!--  --></span>
                            </c:when>
                            <c:otherwise>
                              <span class="fa fa-rupee fa fa-1x"><!--  --></span>
                            </c:otherwise>  
                          </c:choose> 
                        </c:forEach>  
                      </div>
                      <div class="iLB">
                        <span class="skill-set truncate" style='width:180px' data-toggle="tooltip" data-placement="auto" title="${artist.skillSet}">${artist.skillSet}</span>
                      </div>
                    </div>
                  </div>
                  <div class="separator image-separator"><!-- --></div>
                  <a 
                  href="<%=request.getContextPath()%>/artist/profile"
                    class="" data-holder-rendered="true">                   
                      <img data-src="holder.js/100%x200" alt="100%x200" src="<%=request.getContextPath()%>/resources/images/artist_base_img.jpg" data-holder-rendered="true" style="height: 300px; width: 300px; display: block;">           
                  </a>
                  <div class="separator image-separator"><!-- --></div>
                  <div class="artist-action">
                    <button class="btn btn-danger quotationForm" data-artistId="${artist.artistId}" data-artistName="${artist.firstName} ${artist.lastName}" data-toggle="modal" data-target="#myModal">Request for quote</button>
                  </div>
                </div>
              </div>
            <!--new artist box design end here -->    
            </c:forEach>
            
          </div>
        
          <c:if test="${empty artistList}">
            <div class="alert alert-danger" role="alert">
            <h5>It seems the artist you are searching for is not there at the moment, we'll sound them out, meanwhile you can check out some other  cool artists!!
            </h5>
          </div>
          </c:if>

          

        </div>

        <!--  
        <div class="hidden-xs col-sm-12 col-md-12 col-lg-12" style="background-color:#ccc">
            <footer>main Footer hun</footer>
        </div>  
        -->
        
      </div>

      <div id="sidebar_button" class="btn btn-danger visible-xs hidden-sm hidden-md hidden-lg" style="position: fixed; bottom:30px; right: 25px; width: 60px;height: 60px; border-radius: 50%; box-shadow: 0px 5px 5px rgba(0,0,0,.2),0px 10px 14px rgba(0,0,0,.1),inset 0px 1px 0px rgba(255,255,255,.25),inset 0px -1px 0px rgba(0,0,0,.10);">
        <span class="glyphicon glyphicon-filter" aria-hidden="true" style="font-size:1.6em; left: 1px; top: 13px;"></span>
      </div>

      <div id="sidebar_small_device" class="hidden-sm hidden-md hidden-lg" style="background: #e6e6e6; height: 125%; position:fixed;top:54px;display:none;">
          <div id="formDiv_small_device" style="padding-top:70px;padding-left:10px;padding-right:10px;">
          <form id="skillLocModel_small_device" action="search-results" method="GET">            
            <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="form-inline form-group">
                  <select id="select_location_small_device" name="locationId" class="combobox form-control">
                    <option value="">Select Location</option>
                    <c:forEach var="locationIter" items="${locations}">
                        <c:choose>
                          <c:when test="${locationIter.locationId == location}">
                          <option value="${locationIter.locationId}" 
                              selected>${locationIter.locationName}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${locationIter.locationId}">
                            ${locationIter.locationName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>                
                  </select>
                </div>
    
              </div>
            </div>
        
            <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="form-inline form-group">
                  <select id="select_skill_small_device" name="skillId" class="combobox form-control">
                    <option value="">Select Skill</option>
                    <c:forEach var="skillIter" items="${skills}">
                        <c:choose>
                          <c:when test="${skillIter.skillName == skillId}">
                          <option value="${skillIter.skillName}" selected>
                            ${skillIter.skillName}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${skillIter.skillName}">
                            ${skillIter.skillName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>                
                  </select>
                </div>
    
              </div>
            </div>
        
            <div class="row">
              <div id = "filter_label_div" class="col-xs-12 col-sm-12 col-md-12">           
                <p>
                <label for="amount">FILTER BY PRICE ( <span class='fa fa-rupee fa fa-1x'></span> )</label> 
                </p>
              </div>
            </div>
            
            <div class="row">
              <div id = "sliderDiv" class="col-xs-12 col-sm-12 col-md-12">
                <div id="slider-range_small_device"></div>
                <input type="text" id="amount_small_device" readonly style="border:0; color:black; font-weight:bold;background:#e6e6e6;text-align:center">
              </div>
            </div>
                
            <div class="row">
              <div id="search-artist_small_device" class="col-xs-4">
                  <button id="search-asrtist-btn" type="button" class="btn btn-danger">Search</button>
              </div>
            </div>

          </form> 
        </div> 
      </div>

          

  </div>


	<div class="hidden-xs col-sm-12 col-md-12 col-lg-12" style="padding:0px">
		
	</div>


  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
      <div class="modal-header">
        <div style="display:none" class="alert alert-success alert-dismissible" role="alert" id="quote-request-success">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
          </button>
          Quotation request submitted successfully <br><br>
        </div>
        <div style="display:none" class="alert alert-danger" id="quote-request-failure">Oops! 
          Something went wrong. Please retry later .<br><br><button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
                  
        <h4 class="modal-title" id="myModalLabel">Get Free Quotation for 
          <label id="artistName"></label>
        </h4>
      </div>
      <div class="modal-body">
        <div id="modalFormContent">
              <sf:form commandName="artistQuoteModel">
            <div>
              <input type="hidden" id="quote-artist-id" name="artistId">
              <h4 align="left">Name*</h4>
              <input id="requestorName" class=" form-control input-sm"
                        name="requestorName" required type="text" placeholder="Name">
                      <br />
              <h4 align="left" style="display:inline">Email* </h4><label id="requestorEmailLabel" style="font:-webkit-control;color:red;"></label>
              <input id="requestorEmail" class=" form-control input-sm"
                        name="requestorEmail" required type="text"
                        placeholder="abc@mail.com"> <br />
              <h4 align="left" style="display:inline">Contact Number*</h4><label id="requestorPhoneLabel" style="font:-webkit-control;color:red;"></label>
              <input id="requestorPhone" class=" form-control input-sm"
                        name="requestorPhone" required type="text"
                        placeholder="Phone Number"> <br />
        
              <h4 align="left">Event Location*</h4>
              <input id="eventLocation" class=" form-control input-sm"
                        name="eventLocation" required type="text" placeholder="Location">
              <br />
        
            </div>
                    
            
              </sf:form>
        </div>
          </div>      
          <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button id="requestQuote" type="button" class="btn btn-primary">Submit Request</button>
      </div>
      </div>
    </div>
    <input id="context-path" type="hidden" value="<%=request.getContextPath()%>">         
</div>

</body>
</html>