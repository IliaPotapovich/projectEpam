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
           <input type="text" name="findUserId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([0-9]{1,5})" required>
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
           <input type="text" name="findUserByLogin" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([A-Za-zА-Яа-я0-9]{3,15})" required>
           <div class="input-group-append">
             <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
         </form>
         </div>

         <div class="col-3">
          </div>
         </div>
     </div>


<jsp:include page="../common/footer.jsp"/>