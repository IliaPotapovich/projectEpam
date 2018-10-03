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
                   <input type="hidden" name="page" value="/jsp/admin/admin_start_page.jsp"/>
                <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
              </a>
              </form>
            </nav>
        </div>


<br/><br/>
<br/><br/>
<div class="container">
  <div class="row">
      <div class="col-3">
      </div>
    <div class="col">


                    ${passChanged}
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="adminName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="changePassword" bundle="${ rb }"/></th>


    </tr>
  </thead>
  <tbody>

  <br/>

<c:forEach var="admin" items="${adminList}">
<br/>
    <tr>
      <th scope="row"><c:out value="${admin.name}"/></th>
      <td>
    <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="forward"/>
            <input type="hidden" name="page" value="/jsp/admin/change_admin_password.jsp"/>
      <button class="btn btn-outline-warning btn-lg" type="input" id="button-addon2" ><fmt:message key="change" bundle="${rb}"/></button>
    </form>
</td>
    </tr>
</c:forEach>

  </tbody>
</table>
</div>
      <div class="col-3">
      </div>
</div>
</div>

<jsp:include page="../common/footer.jsp"/>