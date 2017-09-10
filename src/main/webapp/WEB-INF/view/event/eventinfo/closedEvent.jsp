<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${event.eventSport.teamSport == false}" >
        //jezli uzytkownik bral udzial podkresl jego wyniki.
        <c:forEach items="${event.eventGames}" var="game">
            <div id="game">
                <div id="desc"> ${game.desc} </div>
                <div> ${game.hostUser.nickname} ${game.hostResult} </div>
                <div> ${game.guestResult} ${game.guestUser.nickname} </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>

        <c:if test="${isParticipate}">
            <div>
                <div> TWOJA OCENA ZA TO WYDARZENIE: ${loggedInUserAvg} </div>
            </div>
        </c:if>

        OCENY ZA WYDARZENIE
        <c:forEach items="${userWithMarksAvg}" var="user">
            <c:if test="${user.key ne loggedInUserName}">
                <div> ${user.key} : ${user.value != null ? user.value : "brak ocen"} <div>
            </c:if>
        </c:forEach>
    </c:otherwise>
</c:choose>