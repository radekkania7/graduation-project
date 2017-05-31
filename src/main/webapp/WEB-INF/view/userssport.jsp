<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

${usersport.sport.name}

<c:forEach items="${events}" var="event">
	<c:choose>
		<c:when test="${usersport.sport.teamSport == false}" >
			<c:set var="games" value="${event.eventGames}" />
			<c:forEach items="${games}" var="game">
				<c:if test="${game.hostUser.nickname eq nameofuser || game.guestUser.nickname eq nameofuser}" >
					${game.desc} <br/>
					${game.hostUser.nickname} ${game.hostResult} : ${game.guestResult} ${game.guestUser.nickname}
					<br/><br/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
			yyy
		</c:otherwise>
	</c:choose>
</c:forEach>