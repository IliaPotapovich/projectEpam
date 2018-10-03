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
                   <input type="hidden" name="page" value="/jsp/admin/trip_find.jsp"/>
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
      <th scope="col"><fmt:message key="privateNumber" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="foto" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="privateNumberOfDriver" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="privateNumberOfCar" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="workStatus" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="freeStatus" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="startWork" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="finishWork" bundle="${ rb }"/></th>


    </tr>
  </thead>
  <tbody>


  <c:forEach var="list" items="${taxiAppropriateList}">
      <th scope="row"><c:out value="${list.id}"/></th>
      <td>
       <div id="photo">
         <img src="${request.getContextPath}/project/avatar/${list.taxiCar.imageCarId}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle">
       </div>
      </td>
      <td><c:out value="${list.taxiDriver.driverId}"/></td>
      <td><c:out value="${list.taxiCar.carId}"/><br/></td>
      <td><c:out value="${list.active}"/></td>
      <td><c:out value="${list.free}"/></td>
      <td><c:out value="${list.startWorkDate}"/></td>
      <td><c:out value="${list.finishWorkDate}"/></td>
    </tr>

  </c:forEach>
  </tbody>
</table>

</div>
</div>








<jsp:include page="../common/footer.jsp"/>