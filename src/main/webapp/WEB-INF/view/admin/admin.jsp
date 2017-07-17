<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

PANEL ADMINISTRATORA


NOWE KONTA -> ZATWIERDZ

<c:forEach items="${createdUsers}" var="user">
	${user.id} ${user.nickname}
</c:forEach>


LISTA UZYTKOWNIKOW ZABLOKUJ/ ODBLOKUJ

