<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1> events list </h1>

<h2>Filtruj eventy</h2>
//TODOSIK -> forumlarz do wyszukiwania eventow.
//miasto
//sport
//data

<c:forEach items="${events}" var="event">
	${event.done} ${event.description} ${event.eventDate} ${event.eventSport.name} ${event.eventTown.name}
	<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>

