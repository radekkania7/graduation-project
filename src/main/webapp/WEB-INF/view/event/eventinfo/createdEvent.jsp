<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

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