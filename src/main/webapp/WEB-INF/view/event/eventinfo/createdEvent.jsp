<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="col-sm-12 well">

    LISTA UCZESNIKOW:
    <c:set var="participantCount" value="${fn:length(participants)}" />
    <c:if test="${participantCount eq 0}" >
        <div class="error">
            JEST PUSTA! DOLACZ DO WYDARZENIA
        </div>
    </c:if>

    <c:if test="${participantCount > 0}" >
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Login</th>
                    <th>Zobacz profil</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:set var="counter" value="1"/>
                        <c:forEach items="${participants}" var="user">
                            <th> ${counter}</th>
                            <th> ${user.nickname} </th>
                            <th> </th>
                            <c:set var="counter" value="${counter += 1}" />
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
    </c:if>



    <c:url var="action" value="/eventInfo/${event.id}/join" />
    <sf:form action="${action}" method="post">
        <input class="center" type="submit" value="${bttnValue}">
    </sf:form>

    <c:if test="${joinInfo ne null}" >
        <div class="leave">
            ${joinInfo}
        </div>
    </c:if>

</div>