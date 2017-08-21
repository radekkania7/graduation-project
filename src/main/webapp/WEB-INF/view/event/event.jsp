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
    <%@ include file="/WEB-INF/view/event/eventinfo/createdEvent.jsp" %>
</c:if>

<c:if test="${status eq 'DURING'}" >
    WYDARZENIE JEST W TRAKCIE,
    ZAPRASZAMY DO OBSERWOWANIA
</c:if>

<c:if test="${status eq 'AFTER'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/afterEvent.jsp" %>
</c:if>

<c:if test="${status eq 'CLOSED'}" >
    <%@ include file="/WEB-INF/view/event/eventinfo/closedEvent.jsp" %>
    -> wyswietlone dane
        -> jesli team Game -> oceny przeciwnika, malejaco.
        -> jesli single Game -> wyniki meczow
</c:if>



	