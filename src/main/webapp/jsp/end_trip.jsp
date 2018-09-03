<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/end_trip.jsp"/>
<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/confirm_trip_end.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>
<br/><br/>



<br/><br/>


<div class="container">
<div class="row">
<h4><fmt:message key="thankForTrip" bundle="${rb}"/></h4>


    </div>
</div>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<div class="container">
<div class="row">
<div class="col-3">
    </div>
    <div class="col">
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/start_page.jsp';"><fmt:message key="inMainMenu" bundle="${ rb }"/></button>
    </div>
    <div class="col-3">
     </div>
    </div>
</div>
<br/>





<jsp:include page="footer.jsp"/>