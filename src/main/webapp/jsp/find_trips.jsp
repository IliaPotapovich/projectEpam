<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/find_trips.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/trip_find.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>
<br/><br/>

<div class="container">
  <div class="row">
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="tripId" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="privateNumberOfUser" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="userName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="phoneNumber" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="inWay" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="markOfTrip" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="costOfRoute" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="taxiId" bundle="${ rb }"/></th>
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
      <td><c:out value="${trip.customerId}"/></td>
      <td><c:out value="${trip.customerName}"/></td>
      <td><c:out value="${trip.customerPhone}"/></td>
      <td><c:out value="${trip.inWay}"/></td>
      <td><c:out value="${trip.markOfTrip}"/></td>
      <td><c:out value="${trip.price}"/></td>
      <td><c:out value="${trip.taxi.id}"/></td>
      <td><c:out value="${trip.startTripDate}"/></td>
      <td><c:out value="${trip.finishTripDate}"/></td>

    </tr>
</c:forEach>

  </tbody>
</table>
</div>
</div>

<jsp:include page="footer.jsp"/>