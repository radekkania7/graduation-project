<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h3> UTWORZ NOWE WYDARZENIE </h3>

<sf:form method="POST" modelAttribute="eventForm">
    <div>
        <sf:label path="sport"> WYBIERZ SPORT </sf:label>
        <sf:select path="sport" >
            <sf:options items="${sportNames}" />
        </sf:select>
        <sf:errors path="sport" />
    </div>
    <div>
        <sf:label path="town" > WPISZ MIASTO </sf:label>
        <sf:input path="town" />
        <sf:errors path="town" />
    </div>
    <div>
        <sf:label path="address" > ADRES OBIEKTU SPORTOWEGO </sf:label>
        <sf:input path="address" />
        <sf:errors path="address" />
    </div>
    <div>
        <sf:label path="date"> DATA WYDARZENIA </sf:label>
        <sf:input path="date" type="date" />
        <sf:errors path="date"/>
    </div>
    <div>
        <sf:label path="timeStart"> GODIZNA OD </sf:label>
        <sf:input path="timeStart" type="time" />
        <sf:errors path="timeStart"/>
    </div>
    <div>
        <sf:label path="timeEnd"> DO</sf:label>
        <sf:input path="timeEnd" type="time" />
        <sf:errors path="timeEnd"/>
    </div>
    <div>
        <sf:label path="participantLimit" > ILOSC MIEJSC </sf:label>
        <sf:input path="participantLimit" />
        <sf:errors path="participantLimit" />
    </div>
    <div>
        <input type="submit" value="DODAJ WYDARZENIE" />
    </div>
</sf:form>

