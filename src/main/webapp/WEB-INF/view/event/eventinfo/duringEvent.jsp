<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-sm-12 well custom-alert">
    <div> TO WYDARZNIE WLASNIE TRWA! </div>
</div>

<div class="col-sm-12 well">
<c:choose>
    <c:when test="${isParticipate ne true}">

            <div>
                ZAPRASZAMY DO OBSERWOWANIA.
            </div>
            <div> LISTA UCZESTNIKOW: </div>
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
                    <c:set var="counter" value="1"/>
                    <c:forEach items="${participants}" var="user">
                        <tr>
                            <th> ${counter}</th>
                            <th> ${user.nickname} </th>
                            <th>
                                <c:set var="url" value="/user/${user.nickname}" />
                                <c:if test="${user.nickname eq loggedInUser}" >
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
    </c:when>
    <c:otherwise>

            <div style="color: darkolivegreen" > JESTES UCZESTNIKIEM! </div>
            <div style="font-weight: bold" > LISTA POZOSTALYCH UCZESTNIKOW </div>
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
                    <c:set var="counter" value="1"/>
                    <c:forEach items="${participants}" var="user">
                        <tr>
                            <th> ${counter}</th>
                            <th> ${user.nickname} </th>
                            <th>
                                <c:set var="url" value="/user/${user.nickname}" />
                                <c:if test="${user.nickname eq loggedInUser}" >
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
    </c:otherwise>
</c:choose>

</div>