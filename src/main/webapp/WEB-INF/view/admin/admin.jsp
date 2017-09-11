<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<nav class="navbar navbar-inverse">
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<c:url value='/logout' />"><span class="glyphicon glyphicon-user"></span><c:out value="Wyloguj sie"/></a></li>
		</ul>
	</div>
</nav>

<div class="col-sm-2">
</div>
<div class="col-sm-8 well">
<h3> PANEL ADMINISTRATORA </h3>
	<p></p>
	<div class="well admin-created">
		<h2>Konta utworzone</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>#</th>
				<th>Login</th>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>e@mail</th>
				<th>Data urodzenia</th>
				<th>Aktywuj</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${createdUsers}" var="user" >
				<tr>
					<c:url var="action" value="/panelAdmin/ACTIVE/${user.id}" />
					<form id="disableForm"  method="POST" action="${action}">
						<td>${user.id}</td>
						<td>${user.nickname}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.dateOfBirth}</td>
						<td><input class="center" type="submit" value="V"/></td>
					</form>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	</div>

	<p></p>
	<div class="well admin-active">
		<h2>Konta aktywne</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>#</th>
				<th>Login</th>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>e@mail</th>
				<th>Data urodzenia</th>
				<th>Zablokuj</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${activeUsers}" var="user" >
				<tr>
					<c:url var="action" value="/panelAdmin/DISABLE/${user.id}" />
					<form id="disableForm"  method="POST" action="${action}">
						<td>${user.id}</td>
						<td>${user.nickname}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.dateOfBirth}</td>
						<td><input class="center" type="submit" value="X"/></td>
					</form>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	<div class="well admin-disabled">
	<h2>Konta nie aktywne</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>#</th>
				<th>Login</th>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>e@mail</th>
				<th>Data urodzenia</th>
				<th>Aktywuj</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${disabledUsers}" var="user" >
				<tr>
					<c:url var="action" value="/panelAdmin/ACTIVE/${user.id}" />
					<form id="disableForm"  method="POST" action="${action}">
						<td>${user.id}</td>
						<td>${user.nickname}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.dateOfBirth}</td>
						<td><input class="center" type="submit" value="V"/></td>
					</form>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</div>
<div class="col-sm-2">
</div>



