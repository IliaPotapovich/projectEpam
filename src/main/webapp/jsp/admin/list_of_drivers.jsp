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
                   <input type="hidden" name="page" value="/jsp/admin/taxi_find.jsp"/>
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
      <th scope="col"><fmt:message key="privateNumberOfDriver" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="driverName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="isBanned" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.experience" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="lock" bundle="${ rb }"/></th>

    </tr>
  </thead>
  <tbody>

  <br/>

<c:forEach var="list" items="${driverList}">
<br/>
    <tr>
      <th scope="row"><c:out value="${list.driverId}"/></th>
      <td><c:out value="${list.driverName}"/></td>
      <td><fmt:message key="${list.status}" bundle="${rb}"/></td>
      <td><c:out value="${list.experience}"/></td>
       <td><c:choose>
                         <c:when test="${list.status==false}">

                        <form name="changeLockForm" action="${request.getContextPath}/project/controller" method="post" >
                        <input type="hidden" name="command" value="change_driver_lock_status"/>
                        <input type="hidden" name="taxiDriverIdChoose" value="${list.driverId}"/>
                        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="block" bundle="${rb}"/></button>
                        </form>
                         </c:when>

                         <c:otherwise>
                       <form name="changeLockForm" action="${request.getContextPath}/project/controller" method="post" >
                       <input type="hidden" name="command" value="change_driver_lock_status"/>
                       <input type="hidden" name="taxiDriverIdChoose" value="${list.driverId}"/>
                       <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="unblock" bundle="${rb}"/></button>
                        </form>
                        </c:otherwise>
                    </c:choose>

                </td>
    </tr>
</c:forEach>

  </tbody>
</table>
</div>
</div>

<jsp:include page="../common/footer.jsp"/>