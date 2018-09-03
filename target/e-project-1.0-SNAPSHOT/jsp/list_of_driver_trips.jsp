<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/list_of_driver_trips.jsp"/>

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

<jsp:include page="footer.jsp"/>