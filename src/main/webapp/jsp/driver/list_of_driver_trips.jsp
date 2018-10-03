<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>

        <div id="imageBack">
        <nav class="navbar navbar-light" id= "back">
            <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
              <a class="navbar-brand" href="javascript:;" onclick="parentNode.submit();">
                   <input type="hidden" name="command" value="forward"/>
                   <input type="hidden" name="page" value="/jsp/driver/taxi_start_page.jsp"/>
                <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
              </a>
              </form>
            </nav>
        </div>
<br/><br/>

<div class="container">
  <div class="row">
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="tripId" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="userName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="costOfRoute" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="markOfTrip" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="driverName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="privateNumberOfCar" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.carModel" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="startTrip" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="finishTrip" bundle="${ rb }"/></th>


    </tr>
  </thead>
  <tbody>

  <br/>

<c:forEach var="trip" items="${tripsList}">
<br/>
    <tr>
      <th scope="row"><c:out value="${trip.tripId}"/></th>
      <td><c:out value="${trip.customerName}"/></td>
      <td><c:out value="${trip.price}"/></td>
      <td><c:out value="${trip.markOfTrip}"/></td>
      <td><c:out value="${trip.taxi.taxiDriver.driverName}"/></td>
      <td><c:out value="${trip.taxi.taxiCar.carId}"/></td>
      <td><c:out value="${trip.taxi.taxiCar.model}"/></td>
      <td><c:out value="${trip.startTripDate}"/></td>
      <td><c:out value="${trip.finishTripDate}"/></td>
    </tr>
</c:forEach>

  </tbody>
</table>
</div>
</div>

<jsp:include page="../common/footer.jsp"/>