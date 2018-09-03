<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/list_of_appropriate_taxi_for_reg.jsp"/>
<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/order_taxi_by_registered_user.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

                                       <h2>${taxiListIsNotExist}<br/></h2>
                                       <h2>${youCanSeeTaxiAppropriateList}</h2>
                                       <h4>${chooseAnotherTaxi}</h4>


<div class="container">
  <div class="row">
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="privateNumber" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="foto" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="driverName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.experience" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.carModel" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.yearOfManuf" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="taxi.choose" bundle="${ rb }"/></th>
    </tr>
  </thead>
  <tbody>


  <c:forEach var="list" items="${taxiAppropriateList}">
  <br/>

  <form name="loginForm" action="${request.getContextPath}/project/controller" method="post" >
      <input type="hidden" name="command" value="choose_taxi_for_reg"/>

    <tr>
    <input type="hidden" name="driverIdChoose" value="${list.taxiDriver.driverId}"/>
      <th scope="row"><c:out value="${list.id}"/></th>
      <td>
      <div id="photo">
          <img src="${request.getContextPath}/project/avatar/${list.taxiCar.imageCarId}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle">
            <input type="hidden" name="chooseImageId" value="${list.taxiCar.imageCarId}"/>
      </div>
      </td>
      <td><c:out value="${list.taxiDriver.driverName}"/></td>
      <td><c:out value="${list.taxiDriver.experience}"/></td>
      <td><c:out value="${list.taxiCar.model}"/><br/></td>
      <td><c:out value="${list.taxiCar.yearOFManufacture}"/></td>
      <td><button type="input" id="enterInto" class="btn btn-outline-warning btn-lg" ><fmt:message key="taxi.choose" bundle="${ rb }"/></button></td>
    </tr>
  </form>
  </c:forEach>
  </tbody>
</table>
</div>
</div>


<jsp:include page="footer.jsp"/>