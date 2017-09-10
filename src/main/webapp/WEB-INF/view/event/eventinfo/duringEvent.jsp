<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>


<div> TO WYDARZNIE WLASNIE TRWA! </div>
<c:choose>
    <c:when test="${isParticipate ne true}">
        <div>
            ZAPRASZAMY DO OBSERWOWANIA.
        </div>
        <div>
            Wydarzenie ma miejsce w ${event.address} w miescie ${event.town}
        </div>
        <div> LISTA UCZESTNIKOW: </div>
        <c:forEach items="${participantsNames}" var="nickname">
            <div> ${nickname} <div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div> JESTES UCZESTNIKIEM! </div>
        <div> LISTA POZOSTALYCH UCZESTNIKOW </div>
        <c:forEach items="${participantsNames}" var="nickname">
            <c:if test="${nickname ne loggedInUser}">
                <div> ${nickname} <div>
            </c:if>
        </c:forEach>
    </c:otherwise>
</c:choose>

