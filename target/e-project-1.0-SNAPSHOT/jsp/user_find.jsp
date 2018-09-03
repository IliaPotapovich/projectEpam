<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/user_find.jsp"/>

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
         <div class="col-3">
         </div>

         <div class="col">
                                        ${usersNotExists}<br/>
     <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_users"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="find.users" bundle="${ rb }"/><br/></button>
     </form>
         </div>

         <div class="col-3">
          </div>
         </div>
     </div>
<br/><br/>
<br/><br/>



<div class="container">

       <div class="row">
         <div class="col-3">
         </div>

         <div class="col">
                                        ${idIsNotExists}<br/>
     <form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
      <input type="hidden" name="command" value="find_user_by_id"/>

         <div class="input-group mb-3 input-group-lg">

         <div class="input-group-prepend">

                     <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="findUserById" bundle="${ rb }"/><br/></span>
                   </div>
           <input type="text" name="findUserId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" required>
           <div class="input-group-append">
             <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
         </form>
         </div>

         <div class="col-3">
          </div>
         </div>
     </div>

<br/><br/>
<br/><br/>



<div class="container">

       <div class="row">
         <div class="col-3">
         </div>

         <div class="col">
                                        ${loginIsNotExists}<br/>
     <form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
      <input type="hidden" name="command" value="find_user_by_login"/>

         <div class="input-group mb-3 input-group-lg">

         <div class="input-group-prepend">

                     <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="findUserByLogin" bundle="${ rb }"/><br/></span>
                   </div>
           <input type="text" name="findUserByLogin" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" required>
           <div class="input-group-append">
             <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
         </form>
         </div>

         <div class="col-3">
          </div>
         </div>
     </div>


<jsp:include page="footer.jsp"/>