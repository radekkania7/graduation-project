<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

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
			<sf:options items="${sports}" multiple="true" itemValue="id" itemLabel="name"/>
		</sf:select>
		<sf:errors path="eventSport" />
	</div> 
	<div>
		<sf:label path="eventTown" >Miasto</sf:label>
		<sf:select path="eventTown"> 
			<sf:options items="${towns}" multiple="true" itemValue="id" itemLabel="name"/>
		</sf:select>
		<sf:errors path="eventTown" /> 
	</div>
	<div>
		<label for="eventTime" >eventTime</label>
   		<input type="datetime-local" name="eventTime">
	</div>
	<div>
		<input type="submit" value="Utwórz Wydarzenie" />
	</div>
</sf:form>

	<%-- 
	<div>
		<label for="eventSport" > Sport </label>
		<select>
			<c:forEach items="${sports}" var="sport">
				<option value="value">${sport.name}</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<label for="eventTown" > Miasto </label>
		<select>
			<c:forEach items="${towns}" var="town">
				<option value="value">${town.name}</option>
			</c:forEach>
		</select>
	</div>
	--%>
