<%@ include file="/WEB-INF/jsp/stdJspIncludes.jsp" %>

<!DOCTYPE HTML>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Welcome Page</title>

    <%-- I N S E R T       C S S     B U N D L E     --%>
    <jwr:style src="/id/bootstrap.css"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/bootstrap-3.3.4/dist/assets/html5shiv.js"></script>
    <script src="${contextPath}/resources/bootstrap-3.3.4/dist/assets/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<%-- S T A N D A R D       H E A D E R  --%>
<%@ include file="/WEB-INF/jsp/stdHeader.jsp" %>

<h2>welcome.jsp</h2>

<br/>

Hello ${userInfo.username} <br/>


<br/>
<br/>
<a href="${contextPath}/angularPage1">Angular Page #1</a> <br/>
<br/>
<br/>
Database time is ${currentTime}
<br/>
<br/>

<%-- S T A N D A R D       F O O T E R  --%>
<%@ include file="/WEB-INF/jsp/stdFooter.jsp" %>

<%-- L O A D    J A V A S C R I P T     B U N D L E   (jquery, bootstrap, angular)  --%>
<jwr:script src="/id/jquery.bootsrap.angular.js" />

</body>
</html>
