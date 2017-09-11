<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12 well custom-alert-2">
    <div>TO CZAS ABY OCENIC RYWALI!</div>
</div>

<c:choose>
    <c:when test="${event.eventSport.teamSport == false}" >
        <div class="col-sm-12 well" >
            <c:url var="action" value="/eventInfo/${event.id}/addGame" />
            <sf:form id="singleGameForm" modelAttribute="singleGameForm" action="${action}" method="post" >
                <div>
                    <sf:label path="opponentName" > WYBIERZ PRZECIWNIKA </sf:label>
                        <sf:select class="input form-control" path="opponentName" items="${participantsNames}" />
                </div>
                <div>
                    <sf:label path="loggedInUserResult" > TWOJ WYNIK </sf:label>
                    <sf:input class="input form-control" path="loggedInUserResult" />
                    <div class="error">
                        <sf:errors path="loggedInUserResult" />
                    </div>
                </div>
                <div>
                    <sf:label path="opponentResult" > WYNIK PRZECIWNIKA </sf:label>
                    <sf:input class="input form-control" path="opponentResult"/>
                    <div class="error">
                        <sf:errors path="opponentResult" />
                    </div>
                </div>
                <div>
                    <sf:label path="description" > OPIS </sf:label>
                    <sf:input class="input form-control" path="description"/>
                    <div class="error">
                        <sf:errors path="description" />
                    </div>
                </div>
                <input class="center" type="submit" value="DODAJ GRE"/>
            </sf:form>
        </div>

        <c:if test="${gameCreateInfo != null}">
            <div class="col-sm-12 leave well">
                ${gameCreateInfo}
            </div>
        </c:if>

        <c:if test="${validationFail != null}">
            <div class="col-sm-12 error well">
                    ${validationFail}
            </div>
        </c:if>

        <c:forEach items="${event.eventGames}" var="game">
            <div class="col-sm-12 well">
                <div  class> ${game.desc} </div>
                <div id="parent">
                    <div id="wide" class="col-sm-6">
                        <span>${game.hostUser.nickname}</span>
                        <span class="ticket-2">${game.hostResult} </span>
                    </div>
                    -
                    <div id="narrow" class="col-sm-6">
                        <span class="ticket-2">${game.guestResult}</span>
                        <span>${game.guestUser.nickname}</span>
                    </div>
                    <c:if test="${game.guestUser.nickname eq loggedInUserName}">
                        <c:if test="${game.confirm == false}" >
                            <c:url var="confirmAction" value="/eventInfo/${event.id}/confirm/${game.id}" />
                            <sf:form action="${confirmAction}" method="post">
                                <input type="submit" value="POTWIEDZ WYNIK" />
                            </sf:form>
                        </c:if>
                        <c:if test="${game.confirm == true}">
                            <div>
                                WYNIK POTWIERDZONY;
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="col-sm-12 well">
            TWOJA AKTUALNA OCENA ZA WYDARZENIE: ${loggedInUserAvg}
            <c:if test="${loggedInUserAvg == null}">
                Brak ocen!
            </c:if>
        </div>

        <div class="col-sm-12 well">
            <c:url var="action" value="/eventInfo/${event.id}/addMark" />
            <sf:form id="teamGameForm" modelAttribute="teamGameForm" action="${action}" method="post" >
                <div>
                    <sf:label path="participant" > WYBIERZ PRZECIWNIKA </sf:label>
                    <sf:select class="input" path="participant" items="${participantsNames}" />
                </div>
                <div>
                    <sf:label class="input" path="mark"> WYBIERZ OCENE </sf:label>
                    <sf:select class="input" path="mark">
                        <sf:option value="1" />
                        <sf:option value="2" />
                        <sf:option value="3" />
                        <sf:option value="4" />
                        <sf:option value="5" />
                    </sf:select>
                </div>
                <input class="center" type="submit" value="OCEN PRZECIWNIKA" />
            </sf:form>

            <c:if test="${markInfoFail != null}">
                <div class="col-sm-12 error well">
                    ${markInfoFail}
                </div>
            </c:if>

            <c:if test="${markInfoSuccess != null}">
                <div class="col-sm-12 leave well">
                    ${markInfoSuccess}
                </div>
            </c:if>

        </div>
        <div class="col-sm-12 well">
            AKTUALNE NOTY GRACZY
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Login</th>
                        <th>Aktualna nota</th>
                        <th>Zobacz profil</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="counter" value="1"/>
                    <c:forEach items="${userWithMarksAvg}" var="user">
                        <tr>
                            <th> ${counter} </th>
                            <th> ${user.key} </th>
                            <th> ${user.value} </th>
                            <th>
                                <c:set var="url" value="/user/${user.key}" />
                                <c:if test="${user.key eq loggedInUser}" >
                                    <c:set var="url" value="/homepage" />
                                </c:if>
                                <a href="<c:url value='${url}' />">Link do profilu</a>
                            </th>
                            <c:set var="counter" value="${counter += 1}" />
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </c:otherwise>
</c:choose>