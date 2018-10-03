<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/user/confirm_trip_end.jsp"/>

    <div id="imageBack">
    <nav class="navbar navbar-light" id= "back">
        <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
          <a class="navbar-brand" href="javascript:;" onclick="parentNode.submit();">
               <input type="hidden" name="command" value="forward"/>
               <input type="hidden" name="page" value="${previousPage}"/>
            <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
          </a>
          </form>
        </nav>
    </div>

<br/><br/>

<div class="container">
<div class="row">

<div class="col-1">
    </div>

    <div class="col">
     <h5><fmt:message key="evaluateTaxip1" bundle="${rb}"/> ${driver_name} <fmt:message key="evaluateTaxip2" bundle="${rb}"/></h5>
    </div>

    <div class="col-1">
     </div>

    </div>
</div>



<div class="container">
  <div class="row">

    <div class="col-3">
    </div>

    <div class="col">
   <br/><h6>${thanksForEvaluate}</h6><br/>


    <form name="evaluateTaxiWork" action="${request.getContextPath}/project/controller" method="post" >
        <input type="hidden" name="command" value="evaluate_taxi_work"/>
    <select class="custom-select custom-select-lg custom-select-block bg-secondary text-warning" name="mark" size="4">
      <option value="2"><fmt:message key="2" bundle="${ rb }"/></option>
      <option value="3"><fmt:message key="3" bundle="${ rb }"/></option>
      <option value="4"><fmt:message key="4" bundle="${ rb }"/></option>
      <option value="5"><fmt:message key="5" bundle="${ rb }"/></option>

    </select>
<br/><br/>



<div class="container">
  <div class="row">
    <div class="col">
    </div>

    <div class="col">
        </div>

        <div class="col">
               <button type="input" id="register" class="btn btn-primary btn-lg btn-block" ><fmt:message key="confirm" bundle="${ rb }"/></button>
            </div>
</div>
</div>
</form>
    </div>

    <div class="col-3">
     </div>

    </div>
</div>
<br/><br/>
<br/><br/>

<div class="container">
  <div class="row">
    <div class="col-3">

    </div>

    <div class="col">
                                <br/><h6>${justTripEnd}</h6><br/>
<form name="confirmTripEndForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="confirm_trip_end"/>

      <button type="input" id="mainOrderTaxi" class="btn btn-primary btn-lg btn-block bg-light text-black" ><fmt:message key="endTrip" bundle="${ rb }"/></button>

        </form>

        </div>

        <div class="col-5">
            </div>
</div>
</div>

<jsp:include page="../common/footer.jsp"/>