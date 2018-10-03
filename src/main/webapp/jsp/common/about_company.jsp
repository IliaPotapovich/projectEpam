<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>
<jsp:include page="header.jsp"/>

<br/>
                  <h1><fmt:message key="aboutCompany" bundle="${ rb }"/></h1>

    <div class="container">
      <div class="row">
                     <fmt:message key="textAboutCompany" bundle="${ rb }"/>
        </div>
        </div>















<jsp:include page="footer.jsp"/>
