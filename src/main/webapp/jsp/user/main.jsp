<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>

<br/><br/>
                    <h2><fmt:message key="login.greetings" bundle="${rb}"/></h2>
                <h3><fmt:message key="main.hello" bundle="${rb}"/> ${name} <fmt:message key="main.hello2" bundle="${rb}"/></h3>
<br/><br/>
              <div class="container">
                <div class="row">
                  <div class="col-4">
                  </div>
                  <div class="col">
                <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
                    <input type="hidden" name="command" value="forward"/>
                    <input type="hidden" name="page" value="/jsp/user/order_taxi_by_registered_user.jsp"/>
                  <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="orderTaxi" bundle="${ rb }"/></button>
                </form>
                  </div>
                  <div class="col-4">
                   </div>
                  </div>
              </div>
              <br/> <br/>

              <div class="container">
                              <div class="row">
                                <div class="col-4">
                                </div>
                                <div class="col">
                                 <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
                                  <input type="hidden" name="command" value="forward"/>
                                  <input type="hidden" name="page" value="/jsp/user/userroom.jsp"/>
                                  <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="main.submitIntoUserRoom" bundle="${ rb }"/></button>
                                 </form>
                                </div>
                                <div class="col-4">
                                 </div>
                                </div>
                            </div>
                            <br/>

<br/><br/>

<jsp:include page="../common/footer.jsp"/>