<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/trip_find.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/admin_start_page.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/><br/><br/>



<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${tripIsNotExistByThisId}<br/>
<form name="findTripById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_trip_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="findTripById" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="tripFindId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>





<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${taxiIsNotExistByThisId}<br/>
<form name="findTaxiById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_taxi_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="findTaxiById" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="taxiFindId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>






<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${tripListIsNotExist}<br/>
     <form name="findFullListOfTrips" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_trips"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="showFullListOfTrips" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>
<br/>




<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${activeTripListIsNotExist}<br/>
     <form name="findActiveListOfTrips" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_active_trips"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="showActiveTrips" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>
<br/>



<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${finishedTripListIsNotExist}<br/>
     <form name="findFinishedListOfTrips" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_finished_trips"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="showFinishedTrips" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>
<br/>

<jsp:include page="footer.jsp"/>