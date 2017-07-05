<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h3>INFORMACJE</h3>
<div> nazwaUzytkownika: ${nickname} </div>
<div> imie: ${user.firstName} ${user.lastName} </div>

<h3> FOTO </h3>

<img src="/portal_content/img/anonymous.png" />

<h3> TWOJE SPORTY </h3>
<c:forEach items="${user.userSports}" var="sport"> 
	<a href="<c:url value='/usersport/${user.id}/${sport.id}' />" class="btn btn-success custom-width">Pokaz statystyki</a> <br/>
</c:forEach>

<h3> DZISIEJSZE WYDARZENIA </h3>

<c:forEach items="${today}" var="event">
	<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>

<h3> NADCHODZACE WYDARZENIA </h3>
<c:forEach items="${upcoming}" var="event">
		${event.description} 
		${event.eventDate}
		${event.startTime}
		${event.stopTime}
		<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>

<h3> HISTORIA WYDARZEN </h3>
<c:forEach items="${history}" var="event">
		${event.description} 
		${event.eventDate}
		${event.startTime}
		${event.stopTime}
		<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>