<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1> EVENT W SZCZEGOLE </h1>

OPIS: ${event.description} <br/>
AUTOR: //TODO <br/>
DATA:${event.eventDate} <br/>
LIMIT MIEJSC: ${event.playersLimit} <br/>
MIASTO: ${event.eventTown.name} <br/>
LOKALIZACJA: //TODO <br/>
SPORT: ${event.eventSport.name} <br/>

LISTA UCZESTNIKOW: <br/>

<c:forEach items="${event.eventUsers}" var="user"> 
		${user.firstName}  ${user.lastName} "POKAZ PROFIL" <br/>
</c:forEach>

DOLACZ DO WYDARZENIA.
//TODO

<form method="POST" action="/eventinfo/${event.id}">
	<input type="submit" value="join"> JoinTheEvent
</form>