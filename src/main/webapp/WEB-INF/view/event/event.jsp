<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${event ne null}" >
    ${event.description} <br/>
    Data: ${event.eventDate} <br/>
    od: ${event.startTime} <br/>
    do ${event.stopTime} <br/>
</c:if>

<c:if test="${status eq 'CREATED'}" >

    <c:if test="${joinInfo ne null}" >
        ${joinInfo}
    </c:if>

    LISTA UCZESNIKOW:
    <c:set var="participantCount" value="${fn:length(participants)}" />
    <c:if test="${participantCount eq 0}" >
        JEST PUSTA! DOLACZ DO WYDARZENIA
    </c:if>

    <c:forEach items="${participants}" var="user">
        ${user.nickname}
    </c:forEach>

    <c:url var="action" value="/eventInfo/${event.id}/join" />
    <sf:form action="${action}" method="post">
        <input type="submit" value="${bttnValue}">
    </sf:form>
</c:if>

<c:if test="${status eq 'DURING'}" >
    WYDARZENIE JEST W TRAKCIE,
    ZAPRASZAMY DO OBSERWOWANIA
</c:if>

<c:if test="${status eq 'AFTER'}" >
    <c:choose>
        <c:when test="${sport.teamSport eq false}" >
            <sf:form if="singleGame" action="addGame" method="post" modelAttribute="individalGameForm">
                <div>
                    <sf:label path="opponentName" > WYBIERZ PRZECIWNIKA </sf:label>
                    <sf:select path="oponentName" items="${participantsNames}" />
                </div>
                <div>
                    <sf:label path="loggedInUserResult" value="TWOJ WYNIK"/>
                    <sf:input path="loggedInUserResult" />
                    <sf:errors path="loggedInUserResult" />
                </div>
                <div>
                    <sf:label path="opponentResult" value="TWOJ WYNIK"/>
                    <sf:input path="opponentResult"/>
                    <sf:errors path="opponentResult" />
                </div>
                <input type="submit" value="DODAJ GRE"/>
            </sf:form>
        </c:when>
        <c:otherwise>
            <sf:form id="teamGame" action="addGame" method="post" modelAttribute="addTeamSport">
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
                //MOZNA DODAC TYLKO JEDNA OCENE ZA JEDEN POJEDYNEK ? -> ROZKMIN
                //OCENA ID-USER-OCENIACZ
                        ID-USER-OCENIANY
                        ID-EVENT
            </sf:form>
        </c:otherwise>
    </c:choose>
</c:if>

<c:if test="${status eq 'CLOSED'}" >
    -> wyswietlone dane
        -> jesli team Game -> oceny przeciwnika, malejaco.
        -> jesli single Game -> wyniki meczow
</c:if>



	