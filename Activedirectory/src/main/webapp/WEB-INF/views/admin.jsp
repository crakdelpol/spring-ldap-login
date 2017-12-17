<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Security</title>
    <style type="text/css">
        #titleId {
            color: red;
            font-size: 34px;
            margin: 36px 0px 15px 0px;
        }
        #messageId {
            font-size: 24px;
            margin: 15px 0px 15px 0px;
        }
        #welcomeMsgId {
            font-size: 20px;
            color: green;
        }
        #welcomeTextId {
            text-transform: capitalize;
        }
        #logoutLink {
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div id="welcomeMessage" align="center">
    <div id="titleId" class="">${welcomeTitle}</div>
    <div id="messageId" class="">${messageObj}</div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div id="welcomeMsgId">
            <span id="welcomeTextId">Welcome: ${pageContext.request.userPrincipal.name}</span> | <span id="logoutId"><a id="logoutLink" href="${pageContext.servletContext.contextPath}/logout">Logout</a></span>
        </div>
    </c:if>
</div>
</body>

</html>