<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/find_driver.jsp"/>
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
      <th scope="col"><fmt:message key="privateNumberOfDriver" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="driverName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="isBanned" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.experience" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="lock" bundle="${ rb }"/></th>
    </tr>
  </thead>
  <tbody>

  <br/>

    <tr>
      <th scope="row"><c:out value="${driver_id}"/></th>
      <td><c:out value="${driver_name}"/></td>
      <td><c:out value="${driver_status}"/></td>
      <td><c:out value="${driver_experience}"/></td>
       <td><c:choose>
                   <c:when test="${driver_status==false}">

                  <form name="changeLockForm" action="${request.getContextPath}/project/controller" method="post" >
                  <input type="hidden" name="command" value="change_driver_lock_status"/>
                  <input type="hidden" name="taxiDriverIdChoose" value="${driver_id}"/>
                  <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="block" bundle="${rb}"/></button>
                  </form>
                   </c:when>

                   <c:otherwise>
                 <form name="changeLockForm" action="${request.getContextPath}/project/controller" method="post" >
                 <input type="hidden" name="command" value="change_driver_lock_status"/>
                 <input type="hidden" name="taxiDriverIdChoose" value="${driver_id}"/>
                 <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="unblock" bundle="${rb}"/></button>
                  </form>
                  </c:otherwise>
              </c:choose>

          </td>
    </tr>

  </tbody>
</table>
</div>
</div>

<jsp:include page="footer.jsp"/>