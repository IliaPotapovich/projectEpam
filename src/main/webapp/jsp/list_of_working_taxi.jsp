<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>
<fmt:setBundle basename="messages" var="ms"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/list_of_working_taxi.jsp"/>

<br/><br/>

   <c:choose>
        <c:when test="${taxiWorkingList==null}">
                 <h2><fmt:message key="taxiListIsNotExist" bundle="${ ms }"/></h2>
        </c:when>
        <c:otherwise>
                  <h2><fmt:message key="youCanSeeTaxiWorkingList" bundle="${ ms }"/></h2>
         </c:otherwise>
   </c:choose>



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
    </tr>
  </thead>
  <tbody>









  <c:forEach var="list" items="${taxiWorkingList}">
  <br/>

    <tr>
      <th scope="row"><c:out value="${list.id}"/></th>
      <td>

        <div id="photo">
            <img src="${request.getContextPath}/project/avatar/${list.taxiCar.imageCarId}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle">
        </div>

      </td>
      <td><c:out value="${list.taxiDriver.driverName}"/></td>
      <td><c:out value="${list.taxiDriver.experience}"/></td>
      <td><c:out value="${list.taxiCar.model}"/></td>
      <td><c:out value="${list.taxiCar.yearOFManufacture}"/></td>
    </tr>

  </c:forEach>






  </tbody>
</table>
</div>
</div>

<jsp:include page="footer.jsp"/>