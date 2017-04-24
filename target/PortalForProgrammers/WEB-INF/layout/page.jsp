<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Portal For Programmers</title>
	</head>
	<body>
		<div id="header">
			<t:insertAttribute name="header" />
		</div>
		<div id="content">
			<t:insertAttribute name="body" />
		</div>
		<div id="menu">
			<t:insertAttribute name="menu" />
		</div>
		<div id="footer">
			<t:insertAttribute name="footer" />
		</div>
	</body>
</html>

