<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>
<html>
<head>

    <title><fmt:message key="login.title" bundle="${rb}"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<style type="text/css">
body {

    	background:url('${request.getContextPath}/project/background/backgroundStart.jpg?auto=yes&bg=555&fg=333&text=First slide');

    }
 #navbar {


    	background-color: orange;
    	text-indent:2em;
        border-radius:1em/2em

       }

      #navbar a {
                font-size: large;
               color: #000000;
       }
       #navbar .navbar-brand {
                       font-size: xx-large;
                      color: #000000;
              }
       #navbar #showTaxiForm .dropdown-item {
        margin:none;

       outline: none;
       border-color:orange;
       background-color: orange;
       font-size: large;
       color: #000000;
              }

              #navbar #showTaxiForm .dropdown-item:hover {
                background-position: 0 0px;
                 cursor: pointer;

              }

h1{
   text-align: center
     }
      #mainOrderTaxi{
                   color:#000000;
                   font-size: xx-large;
                   background-color: white;
                   width: 100%;
                   height:80px;

                   }
</style>


</head>


<body>


<div class="container">
    <nav class="navbar navbar-light" id = "navbar">
        <a class="navbar-brand" href="${request.getContextPath}/project"><fmt:message key="taxi" bundle="${ rb }"/></a>
        <a class="nav-link active" href="${request.getContextPath}/project"><fmt:message key="main" bundle="${ rb }"/></a>

<form name="showTaxiForm" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="show_list_of_working_taxi"/>
<button class="dropdown-item" ><fmt:message key="ourTaxi" bundle="${ rb }"/></button>
</form>

<div class="dropdown">

  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <fmt:message key="changeLang" bundle="${ rb }"/>
  </button>

 <form name="Language" action="${request.getContextPath}/project/controller" method="post" >
 <input type="hidden" name="command" value="set_language"/>

  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">

    <button class="dropdown-item" type="input" name="lang" value="ru_RU">
    <fmt:message key="login.ruLanguage" bundle="${ rb }"/>
    </button>
    <button class="dropdown-item" type="input" name="lang" value="en_US">
    <fmt:message key="login.enLanguage" bundle="${ rb }"/>
    </button>
    <button class="dropdown-item" type="input" name="lang" value="be_BY">
    <fmt:message key="login.beLanguage" bundle="${ rb }"/>
    </button>

  </div>
 </form>
</div>

<form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="enter_in_user_room"/>
<button class="dropdown-item" ><fmt:message key="privateRoom" bundle="${ rb }"/></button>
</form>

     <a class="nav-link" href="${request.getContextPath}/project/jsp/about_company.jsp"><fmt:message key="aboutCompany" bundle="${ rb }"/></a>

    </nav>
    </div>

<br/>


<c:choose>
<c:when test="${language.equals('ru_RU')}">
        <c:set var="pictLang" scope="session" value="ru"/>
</c:when>
<c:when test="${language.equals('en_US')}">
        <c:set var="pictLang" scope="session" value="en"/>
</c:when>
<c:when test="${language.equals('be_BY')}">
        <c:set var="pictLang" scope="session" value="be"/>
</c:when>
<c:when test="${language==null}">
        <c:set var="pictLang" scope="session" value="ru"/>
</c:when>
</c:choose>

