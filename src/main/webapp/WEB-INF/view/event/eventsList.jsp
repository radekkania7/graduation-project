<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

EVENTS LIST

<sf:form modelAttribute="findEventForm" method="post">
    <div>
        <sf:label path="town"> WPISZ NAZWE MIASTA</sf:label>
        <sf:input path="town" />
        <sf:errors path="town" />
    </div>
    <div>
        <sf:label path="sport"> WYBIERZ SPORT</sf:label>
        <sf:select path="sport" items="${sportNames}" />
        <sf:errors path="sport" />
    </div>
    <div>
        <sf:label path="date">WYBIERZ DATE</sf:label>
        <sf:input path="date" type="date"/>
        <sf:errors path="date" />
    </div>
    <input type="submit" value="SZUKAJ WYDARZEN" />
</sf:form>

<c:choose>
    <c:when test="${events ne null}">
        <c:forEach items="${events}" var="event">
            przejdz do wydarzeninia:  <a href="<c:url value='/eventInfo/${event.id}' />"> Link <br/>
        </c:forEach>
    </c:when>
</c:choose>