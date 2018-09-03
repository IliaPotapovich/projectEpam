<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>


<form action="/project/uploadBackground" method="post" enctype="multipart/form-data">

    <input type="file" name="image"><br/>
    <input type="submit" value="Download Back"><br/>
</form>

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>

<form action="/project/uploadAvatar" method="post" enctype="multipart/form-data">

    <input type="file" name="image"><br/>
    <input type="submit" value="Download Avatar"><br/>
</form>

<jsp:include page="footer.jsp"/>