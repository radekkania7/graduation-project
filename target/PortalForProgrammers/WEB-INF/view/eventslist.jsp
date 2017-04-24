<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1> events list </h1>

<h2>Filtruj eventy</h2>
//miasto
//sport
//data

<c:forEach items="${events}" var="event">
	id ${event.id}
	id ${event.description}
</c:forEach>

