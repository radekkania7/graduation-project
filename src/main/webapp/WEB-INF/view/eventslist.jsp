<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h3>FILTRUJ WYDARZENIA</h3>

<form method="POST" action="<c:url value = '/findevents' />">
	<div>
		<label for="sportString" > Sport </label>
		<select name="sportString">
			<c:forEach items="${listOfSports}" var="sport">
				<c:set var ="sportName" value = "${sport.name}" />
				<option value="${sportName}">${sport.name}</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<label for="townString" > Miasto </label>
		<select name="townString">
			<c:forEach items="${listOfTowns}" var="town">
				<c:set var ="townName" value = "${town.name}" />
				<option value="${townName}">${town.name}</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<input type="submit" value="Wyszukaj wydarzenia" />
	</div>
</form>

<h3>LISTA WYDARZEN</h3>

<c:forEach items="${events}" var="event">
	${event.description} ${event.startTime} ${event.stopTime} ${event.eventSport.name} ${event.eventTown.name}
	<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>