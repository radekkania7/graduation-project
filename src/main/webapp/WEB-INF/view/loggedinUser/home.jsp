<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div id="homepage">
    <div id="incoming">
        <c:if test="${fn:length(incomingEvents) > 0}" >
            <div> NADCHODZACE WYDARZENIA </div>
            <c:forEach items="${incomingEvents}" var="event">
                <div>${event.description} ${event.eventDate} ${event.startTime}
                    <c:url var="link" value="/eventInfo/${event.id}" />
                    <a href="${link}" class="btn btn-success custom-width">pokaz wydarzenie</a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <div id="during">
        <c:if test="${fn:length(duringEvents) > 0}" >
            <div> W TRAKCIE </div>
            <c:forEach items="${duringEvents}" var="event">
                <div>${event.description} ${event.eventDate} ${event.startTime}
                    <c:url var="link" value="/eventInfo/${event.id}" />
                    <a href="${link}" class="btn btn-success custom-width">pokaz wydarzenie</a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <div id="toEvaluate">
        <c:if test="${fn:length(eventsToEvaluate) > 0}" >
            <div> OCEN PRZECIWNIKOW </div>
            <c:forEach items="${eventsToEvaluate}" var="event">
                <div>${event.description} ${event.eventDate} ${event.startTime}
                    <c:url var="link" value="/eventInfo/${event.id}" />
                    <a href="${link}" class="btn btn-success custom-width">pokaz wydarzenie</a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <div id="archive">
        <c:if test="${fn:length(eventsToEvaluate) > 0}" >
            <div> HISTORIA </div>
            <c:forEach items="${historyEvents}" var="event">
                <div>${event.description} ${event.eventDate} ${event.startTime}
                    <c:url var="link" value="/eventInfo/${event.id}" />
                    <a href="${link}" class="btn btn-success custom-width">pokaz wydarzenie</a>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
