<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12 custom-alert-3 well" >
    WYDARZENIE ARCHIWALNE
</div>

<c:choose>
    <c:when test="${event.eventSport.teamSport == false}" >
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

        <c:if test="${isParticipate}">
            <div class="col-sm-12 well">
                <div> TWOJA OCENA ZA TO WYDARZENIE: ${loggedInUserAvg} </div>
            </div>
        </c:if>

        <div class="col-sm-12 well">
            <div class="title">AKTUALNE NOTY GRACZY </div>
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