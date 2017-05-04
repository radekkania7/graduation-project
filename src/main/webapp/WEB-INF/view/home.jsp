<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1>Strona domowa</h1>
<h2>nazwaUzytkownika: ${username}</h2>
<h2>imie: ${user.firstName} </h2>
<h2>nazwisko: ${user.lastName} </h2>

<h2> UPRAWIANE SPORTY </h2>

<c:forEach items="${user.userSports}" var="sport">
	Sport: ${sport.sport.name} &nbsp
</c:forEach>

<h2> AKTUALNE WYDARZENIA </h2>

<c:forEach items="${actual}" var="event">
		${event.description} ${event.eventDate} 
		<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>

<h2> HISTORIA WYDARZEN </h2>

<c:forEach items="${history}" var="event">
		${event.description} ${event.eventDate} 
		<a href="<c:url value='/eventinfo/${event.id}' />" class="btn btn-success custom-width">Pokaz wydarzenie</a> <br/>
</c:forEach>

<h2> RANKINGI </h2>

//TODOSIK ;)

<h2> wczytywanie profilowki xd </h2>

	<form method="POST" action="upload" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>	
	
<h2>Foto</h2>

