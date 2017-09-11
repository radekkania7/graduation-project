<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="pl">
<div class="well">
        <div class="title">
            <h3><c:out value="WYSZUKAJ WYDARZENIA"/></h3>
        </div>
        <sf:form modelAttribute="findEventForm" method="post">
            <div>
                <sf:label path="town"> WPISZ NAZWE MIASTA</sf:label>
                <sf:input class="input form-control" path="town" />
                <div class="error">
                <sf:errors  path="town"/>
            </div>
            </div>
            <div>
                <sf:label path="sport"> WYBIERZ SPORT</sf:label>
                <sf:select class="input form-control" path="sport" items="${sportNames}" />
                <div class="error">
                    <sf:errors path="sport" />
                </div>
            </div>
            <div>
                <div>
                    <sf:label path="date">WYBIERZ DATE</sf:label>
                </div>
                <div>
                    <sf:input class="input form control" path="date" type="date"/>
                </div>
                <div class="error">
                    <sf:errors path="date" />
                </div>
            </div>
            <input class="center" type="submit" value="SZUKAJ WYDARZEN" />
        </sf:form>
</div>

<c:choose>
    <c:when test="${fn:length(foundedEvents) > 0}">
        <div class="well">
            <c:forEach items="${foundedEvents}" var="event">
            przejdz do wydarzeninia:  <a href="<c:url value='/eventInfo/${event.id}' />"> Link <br/>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="well">
            <c:out value="BRAK WYNIKÓW. WYBIERZ INNE WARUNKI!" />
        </div>
    </c:otherwise>
</c:choose>
</html>
