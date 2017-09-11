<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="well col-sm-12 ticket">
<c:if test="${event ne null}" >
    <div class="col-sm-3">
        <c:set var="path" value="${sportIconPath}" />
        <img class="img-circle" src="<c:url value="${path}"/>"
                 alt="football" height="65" width="65"/>
    </div>
    <div class="well col-sm-9 ticket">
        <div class="right-text">
            <div class="ticket">SPORT: ${sportName}</div>
            <div class="ticket">OPIS:${event.description}</div>
            <div class="ticket">MIASTO: ${event.town}</div>
            <div class="ticket">ADRES: ${event.address}</div>
            <div class="ticket">Data: ${event.eventDate}</div>
            <div class="ticket">Od godziny: ${event.startTime} do: ${event.stopTime}</div>
        </div>
    </div>

    <c:if test="${status eq 'CREATED'}">
        <div class="well col-sm-12 leave">
                WOLNE MIEJSCA: ${freePlaces}
        </div>
    </c:if>
</c:if>
</div>

<c:if test="${status eq 'CREATED'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/createdEvent.jsp" %>
</c:if>

<c:if test="${status eq 'DURING'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/duringEvent.jsp"%>
</c:if>

<c:if test="${status eq 'AFTER'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/afterEvent.jsp" %>
</c:if>

<c:if test="${status eq 'CLOSED'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/closedEvent.jsp" %>
</c:if>



	