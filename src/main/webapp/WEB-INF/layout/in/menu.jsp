<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pl">
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Logo</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value='/homepage' />">Twój profil</a></li>
				<li><a href="<c:url value='/events' />">Wyszukaj wydarzenia</a></li>
				<li><a href="<c:url value='/createNewEvent' />">Dodaj wydarzenie</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/logout' />"><span class="glyphicon glyphicon-user"></span><c:out value="Wyloguj sie"/></a></li>
			</ul>
		</div>
	</div>
</nav>
</html>