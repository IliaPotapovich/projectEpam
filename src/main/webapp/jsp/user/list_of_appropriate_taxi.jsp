<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>
<fmt:setBundle basename="messages" var="ms"/>



<jsp:include page="../common/header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/user/list_of_appropriate_taxi.jsp"/>

        <div id="imageBack">
        <nav class="navbar navbar-light" id= "back">
            <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
              <a class="navbar-brand" href="javascript:;" onclick="parentNode.submit();">
                   <input type="hidden" name="command" value="forward"/>
                   <input type="hidden" name="page" value="/jsp/user/order_with_taxi_choose.jsp"/>
                <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
              </a>
              </form>
            </nav>
        </div>

                                       <h3>${youCanSeeTaxiAppropriateList}</h2>
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
      <input type="hidden" name="command" value="choose_taxi"/>

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

<jsp:include page="../common/footer.jsp"/>