<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h3> INFORMACJE O WYDARZENIU </h3>

OPIS: ${event.description} <br/>
DATA:${event.eventDate} <br/>
POCZATEK: ${event.startTime} <br/>
KONIEC: ${event.stopTime} <br/>
LIMIT MIEJSC: ${event.playersLimit} <br/>
MIASTO: ${event.eventTown.name} <br/>
SPORT: ${event.eventSport.name} <br/>

<h3> UCZESTNICY </h3>

<c:forEach items="${event.eventUsers}" var="user"> 
	<c:choose>
		<c:when test="${user.nickname == nameofuser}">
			${user.firstName}  ${user.lastName} <a href="<c:url value='/' />" class="btn btn-success custom-width">Pokaz profil</a> <br/>
		</c:when>
		<c:otherwise>
			${user.firstName}  ${user.lastName} <a href="<c:url value='/showuser/${user.id}' />" class="btn btn-success custom-width">Pokaz profil</a> <br/>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:set var = "status" value = "${event.status}" />
<c:set var = "isPrincipant" value = "${flag}" />

<c:if test = "${status == 1}">
	<c:choose>
		<c:when test = "${isPrincipant == false}">
			<form method = "POST" action="<c:url value = '/eventinfo/${event.id}' />">
				<input type="submit" value="join"> Dolacz do wydarzenia.
			</form>
		</c:when>
		<c:otherwise>
			<form method = "POST" action="<c:url value = '/eventinfo/${event.id}/unjoin' />">
				<input type="submit" value="join"> Opusc wydzarzenie.
			</form>
		</c:otherwise>
	</c:choose>
</c:if>

<c:set var = "sport" value = "${event.eventSport}" />
<c:set var = "isTeam" value = "${sport.teamSport}" />

<c:if test = "${status == 0 && flag == true}">
	<c:choose>
		<c:when test = "${isTeam == true}">
			
		</c:when>
		<c:otherwise>
			
			<h3> DODAJ WYNIKI ROZEGRANYCH MECZY </h3>
			
			<form method="POST" action="<c:url value = '/eventinfo/${event.id}/addplay' />" >
				<div>
					<label for="opponentName" > Przeciwnik </label>
					<select name="opponentName">
						<c:forEach items="${event.eventUsers}" var="user">
							<c:set var ="name" value = "${user.nickname}" />
							<c:if test = "${name != nameofuser}" >
								<option value="${name}">${name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="descStr" >Opis</label>
					<input name="descStr" />
				</div>
				<div>
					<label for="hostResultStr" >Twoje punkty</label>
					<input name="hostResultStr" />
				</div>
				<div>
					<label for="guestResultStr" >Punkty przeciwnika</label>
					<input name="guestResultStr" />
				</div>
				<div>
					<input type="submit" value="Dodaj rozgrywke" />
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</c:if>
			
<h3> WYNIKI GIER </h3>

<c:forEach items="${event.eventGames}" var="game">
	${game.desc} 
	${game.hostUser.nickname} ${game.hostResult} : ${game.guestResult} ${game.guestUser.nickname} potwierdzone: ${game.confirm}
	<c:set var = "name" value="${game.guestUser.nickname}" />
	<c:if test="${name == nameofuser}" >
		<form method="POST" action="<c:url value = '/eventinfo/${event.id}/game/${game.id}/confirm' />" >
			<input type="submit" value="Potwierdz Wynik">
		</form>
	</c:if>
	<br/>
</c:forEach>