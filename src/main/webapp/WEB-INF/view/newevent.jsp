<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h3> UTWORZ NOWE WYDARZENIE </h3>

<sf:form method="POST" modelAttribute="event">
	<sf:input type="hidden" path="id"/>
	<div>	
		<sf:label path="playersLimit">Limit</sf:label>
		<sf:input path="playersLimit" />
		<sf:errors path="playersLimit" />
	</div>
	<div>	
		<sf:label path="description">Opis</sf:label>
		<sf:input path="description" />
		<sf:errors path="description" />
	</div>
	<div>
		<sf:label path="eventSport" >Sport</sf:label>
		<sf:select path="eventSport"> 
			<sf:options items="${listOfSports}" multiple="true" itemValue="id" itemLabel="name"/>
		</sf:select>
		<sf:errors path="eventSport" />
	</div> 
	<div>
		<sf:label path="eventTown" >Miasto</sf:label>
		<sf:select path="eventTown"> 
			<sf:options items="${listOfTowns}" multiple="true" itemValue="id" itemLabel="name"/>
		</sf:select>
		<sf:errors path="eventTown" /> 
	</div>
	<div>
		<label for="eventDate" >Data rozpoczecia</label>
   		<input type="date" name="eventDate">
	</div>
	<div>
		<label for="startTime" >Czas rozpoczecia</label>
   		<input type="time" name="startTime">
	</div>
	<div>
		<label for="stopTime" >Czas zakonczenia</label>
   		<input type="time" name="stopTime">
	</div>
	<div>
		<input type="submit" value="Utwórz Wydarzenie" />
	</div>
</sf:form>