<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/list_of_car.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/taxi_find.jsp">
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
      <th scope="col"><fmt:message key="privateNumberOfCar" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="foto" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.carModel" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.ownerId" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.yearOfManuf" bundle="${ rb }"/></th>

    </tr>
  </thead>
  <tbody>


  <c:forEach var="carList" items="${carsList}">
  <br/>

    <tr>
      <th scope="row"><c:out value="${carList.carId}"/></th>
      <td>
      <div id="photo">
         <img src="${request.getContextPath}/project/avatar/${carList.imageCarId}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle" data-toggle="modal" data-target="#exampleModal">
      </div>
      </td>
      <td><c:out value="${carList.model}"/></td>
      <td><c:out value="${carList.ownerId}"/></td>
      <td><c:out value="${carList.yearOFManufacture}"/></td>
    </tr>

  </c:forEach>
  </tbody>
</table>
</div>
</div>




<jsp:include page="footer.jsp"/>