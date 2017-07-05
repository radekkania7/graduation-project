<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="sportname" value="${sport.name}" />
<c:set var="nickname" value="${user.nickname}" />

${nickname}
${sportname}

<c:choose>
	<c:when test="${sport.teamSport == false}" >
		<c:forEach items="${events}" var="event">
			<c:set var="games" value="${event.eventGames}" />
			<c:forEach items="${games}" var="game">
				<c:if test="${game.hostUser.nickname eq nameofuser || game.guestUser.nickname eq nameofuser}" >
					${game.desc} <br/>
					${game.hostUser.nickname} ${game.hostResult} : ${game.guestResult} ${game.guestUser.nickname}
					<br/><br/>
				</c:if>
		</c:forEach>
	</c:forEach>
	</c:when>
	<c:otherwise>
		SREDNIA OCEN: ${avarage}
		ILOSC OCEN: ${markCount}
		<br/> <br/>
		<c:forEach items="${eventsWithAvg}" var="entry">
			<c:set var="event" value="${entry.key}" />
			<c:set var="avg" value="${entry.value}" />
			Data wydarzenia: ${event.eventDate} 
			Opis: wydarzenia ${event.description}
			punkty: ${avg}
			<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>