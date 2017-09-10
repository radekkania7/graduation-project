<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${event.eventSport.teamSport == false}" >
        <c:url var="action" value="/eventInfo/${event.id}/addGame" />
        <sf:form id="singleGameForm" modelAttribute="singleGameForm" action="${action}" method="post" >
            <div>
                <sf:label path="opponentName" > WYBIERZ PRZECIWNIKA </sf:label>
                <sf:select path="opponentName" items="${participantsNames}" />
            </div>
            <div>
                <sf:label path="loggedInUserResult" > TWOJ WYNIK </sf:label>
                <sf:input path="loggedInUserResult" />
                <sf:errors path="loggedInUserResult" />
            </div>
            <div>
                <sf:label path="opponentResult" > WYNIK PRZECIWNIKA </sf:label>
                <sf:input path="opponentResult"/>
                <sf:errors path="opponentResult" />
            </div>
            <div>
                <sf:label path="description" > OPIS </sf:label>
                <sf:input path="description"/>
                <sf:errors path="description" />
            </div>
            <input type="submit" value="DODAJ GRE"/>
        </sf:form>

        <c:if test="${gameCreateInfo != null}">
            ${gameCreateInfo}
        </c:if>

        <c:forEach items="${event.eventGames}" var="game">
            ${game.desc} <br/>
            ${game.hostUser.nickname} ${game.hostResult} - ${game.guestResult} ${game.guestUser.nickname}
            <c:if test="${game.guestUser.nickname eq loggedInUserName}">
                <c:if test="${game.confirm == false}" >
                    <c:url var="confirmAction" value="/eventInfo/${event.id}/confirm/${game.id}" />
                    <sf:form action="${confirmAction}" method="post">
                        <input type="submit" value="POTWIEDZ WYNIK" />
                    </sf:form>
                </c:if>
                <c:if test="${game.confirm == true}">
                    WYNIK POTWIERDZONY;
                </c:if>
            </c:if>
            <br/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="/eventInfo/${event.id}/addMark" />
        <sf:form id="teamGameForm" modelAttribute="teamGameForm" action="${action}" method="post" >
            <div>
                TWOJA AKTUALNA OCENA ZA WYDARZENIE: ${loggedInUserAvg}
                <c:if test="${loggedInUserAvg == null}">
                    Brak ocen!
                </c:if>
            </div>

            <div>
                <sf:label path="participant" > WYBIERZ PRZECIWNIKA </sf:label>
                <sf:select path="participant" items="${participantsNames}" />
            </div>
            <div>
                <sf:label path="mark"> WYBIERZ OCENE </sf:label>
                <sf:select path="mark">
                    <sf:option value="1" />
                    <sf:option value="2" />
                    <sf:option value="3" />
                    <sf:option value="4" />
                    <sf:option value="5" />
                    <sf:option value="6" />
                </sf:select>
            </div>
            <input type="submit" value="OCEN PRZECIWNIKA" />

            <div>
                <c:if test="${markInfo != null}">
                    ${markInfo}
                </c:if>
            </div>

            AKTUALNA SREDNIA OCEN:
            <c:forEach items="${userWithMarksAvg}" var="user">
                <c:if test="${user.key ne loggedInUserName}">
                    <div> ${user.key} : ${user.value != null ? user.value : "brak ocen"} <div>
                </c:if>
            </c:forEach>
        </sf:form>
    </c:otherwise>
</c:choose>