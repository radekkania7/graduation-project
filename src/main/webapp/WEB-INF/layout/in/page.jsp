<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<html>
	<head>
		<title> Life active </title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		
	<div class="container">
		<div id="menu" class="col-md-3">
			<t:insertAttribute name="menu" />
		</div>
		<div id="content" class="col-md-9">
			<t:insertAttribute name="body" />
		</div>
		<div id="footer">
			<t:insertAttribute name="footer" />
		</div>
	</div>
	</body>
</html>

