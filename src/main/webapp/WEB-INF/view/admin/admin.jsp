<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3> PANEL ADMINISTRATORA </h3>

<c:if test="${info ne null}">
	${info}
</c:if>

<h4> NOWE KONTA -> ZATWIERDZ </h4>
<c:forEach items="${createdUsers}" var="user">
	<c:url var="action" value="/panelAdmin/ACTIVE/${user.id}" />
	<sf:form id="activateForm" method="POST" action="${action}">
		${user.id} ${user.nickname} 
		<input type="submit" value="AKTYWUJ KONTO"/>
	</sf:form>
</c:forEach>

<h4> LISTA UZYTKOWNIKOW AKTYWNYCH -> ZABLOKUJ </h4>
<c:forEach items="${activeUsers}" var="user" >
	<c:url var="action" value="/panelAdmin/DISABLE/${user.id}" />
	<form id="disableForm"  method="POST" action="${action}">
		${user.id} ${user.nickname} 
		<input type="submit" value="ZABLOKUJ KONTO"/>
	</form>
</c:forEach>

<h4> LISTA UZYTKOWNIKOW ZABLOKOWANYCH -> AKTYWUJ </h4>
<c:forEach items="${disabledUsers}" var="user">
	<c:url var="action" value="/panelAdmin/ACTIVE/${user.id}" />
	<form id="activate" method="POST" action="${action}">
		${user.id} ${user.nickname} 
		<input type="submit" value="AKTYWUJ KONTO"/>
	</form>
</c:forEach>