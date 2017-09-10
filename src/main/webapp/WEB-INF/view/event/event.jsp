<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${event ne null}" >
    <div>Opis:${event.description}</div>
    <div>Adres: ${event.address}</div>
    <div>Data: ${event.eventDate}</div>
    <div>od: ${event.startTime}</div>
    <div>do: ${event.stopTime}</div>
    <div>sport: ${sportName}</div>
</c:if>

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



	