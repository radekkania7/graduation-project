<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="POST">
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
	<div>
		<input type="submit" value="Utwórz Wydarzenie" />
	</div>
</form>

&{eventTown}