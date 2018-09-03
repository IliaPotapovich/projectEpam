<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/list_of_admins.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/admin_start_page.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>
<br/><br/>
<div class="container">
  <div class="row">
                    ${passChanged}
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="adminName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="password" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="changePassword" bundle="${ rb }"/></th>


    </tr>
  </thead>
  <tbody>

  <br/>

<c:forEach var="admin" items="${adminList}">
<br/>
    <tr>
      <th scope="row"><c:out value="${admin.name}"/></th>
      <td><c:out value="${admin.password}"/></td>
      <td><button class="btn btn-outline-warning btn-lg" type="input" id="button-addon2" onclick="location.href='${request.getContextPath}/project/jsp/change_admin_password.jsp';"><fmt:message key="change" bundle="${rb}"/></button>
</td>
    </tr>
</c:forEach>

  </tbody>
</table>
</div>
</div>

<jsp:include page="footer.jsp"/>