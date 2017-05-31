<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<sf:form method="POST" modelAttribute="sport">
	<sf:input type="hidden" path="id"/>
	<div>	
		<sf:label path="name">Miasto</sf:label>
		<sf:input path="name" />
		<sf:errors path="name" />
	</div>
	<div>
		<input type="submit" value="Dodaj miasto" />
	</div>
</sf:form>