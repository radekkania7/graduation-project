<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1>Strona domowa</h1>
<h2>nazwaUzytkownika: ${username}</h2>
<h2>imie: ${user.firstName} </h2>
<h2>nazwisko: ${user.lastName} </h2>

<h2> UPRAWIANE SPORTY </h2>

<c:forEach items="${user.userSports}" var="sport">
	Sport: ${sport.sport.name}
	Punkty: ${sport.points}
</c:forEach>

<h2> Nadchodzace wydarzenia uzytkownika </h2>

<c:forEach items="${user.userEvents}" var="event">
	<c:set var="isDone" value="${event.done}" />
	<c:if test="${isDone == false}">
		${event.description} <br/>
		${event.eventDate} <br/>
	</c:if>
</c:forEach>

<h2> HISTORIA GIER </h2>

<c:forEach items="${user.userEvents}" var="event">
	<c:set var="isDone" value="${event.done}" />
	<c:if test="${isDone == true}">
		${event.description} <br/>
		${event.eventDate} <br/>
	</c:if>
</c:forEach>

<h2> RANKINGI </h2>


<h2> wczytywanie profilowki xd </h2>

	<form method="POST" action="upload" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>	
	
<h2>Foto</h2>

