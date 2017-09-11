<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<html lang="pl">
<div class="well">
    <div class="title">
        <h3> UTWORZ NOWE WYDARZENIE </h3>
    </div>
    <sf:form method="POST" modelAttribute="eventForm">
        <div>
            <sf:label path="sport"> WYBIERZ SPORT </sf:label>
            <sf:select  class="input form-control" path="sport" items="${sportNames}" />
            <div class="error">
                <sf:errors path="sport" />
            </div>
        </div>
        <div>
            <sf:label path="town" > WPISZ MIASTO </sf:label>
            <sf:input class="input form-control" path="town" />
            <div class="error">
                <sf:errors path="town" />
            </div>
        </div>
        <div>
            <sf:label path="address" > ADRES OBIEKTU SPORTOWEGO </sf:label>
            <sf:input class="input form-control" path="address" />
            <div class="error">
                <sf:errors path="address" />
            </div>
        </div>
        <div>
            <sf:label path="date"> DATA WYDARZENIA </sf:label>
            <sf:input class="input form-control" path="date" type="date" />
            <div class="error">
                <sf:errors path="date"/>
            </div>
        </div>
        <div>
            <sf:label path="timeStart"> GODIZNA OD </sf:label>
            <sf:input class="input form-control" path="timeStart" type="time" />
            <div class="error">
                <sf:errors path="timeStart"/>
            </div>
        </div>
        <div>
            <sf:label path="timeEnd"> DO</sf:label>
            <sf:input class="input form-control" path="timeEnd" type="time" />
            <div class="error">
                <sf:errors path="timeEnd"/>
            </div>
        </div>
        <div>
            <sf:label path="participantLimit" > ILOSC MIEJSC </sf:label>
            <sf:input class="input form-control" path="participantLimit" />
            <div class="error">
                <sf:errors path="participantLimit" />
            </div>
        </div>
        <div>
            <sf:label path="description" > WPISZ INFORMACJE NA TEMAT WYDARZENIA </sf:label>
            <sf:input class="input form-control" path="description" />
            <div class="error">
                <sf:errors path="description" />
            </div>
        </div>
        <div>
            <input class="center" type="submit" value="DODAJ WYDARZENIE" />
        </div>
    </sf:form>
</div>
</html>

