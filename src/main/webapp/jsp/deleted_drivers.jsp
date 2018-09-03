<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/deleted_drivers.jsp"/>
<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/archives.jsp">
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

<c:forEach var="list" items="${driverList}">
<br/>
    <tr>
      <th scope="row"><c:out value="${list.driverId}"/></th>
      <td><c:out value="${list.driverName}"/></td>
      <td><c:out value="${list.status}"/></td>
      <td><c:out value="${list.experience}"/></td>
    </tr>
</c:forEach>

  </tbody>
</table>
</div>
</div>

<jsp:include page="footer.jsp"/>