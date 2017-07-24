<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<h1> register form </h1>

<c:if test="${info ne null}" >
	"${info}"
</c:if>

<sf:form method="POST" modelAttribute="user" >
	<sf:input type="hidden" path="id"/>
	<div>	
		<sf:label path="nickname">Login</sf:label>
		<sf:input path="nickname" />
		<sf:errors path="nickname" />
	</div>
	<div>
		<sf:label path="firstName" >Imie</sf:label>
		<sf:input path="firstName" />
		<sf:errors path="firstName" />
	</div>
	<div>
		<sf:label path="lastName" >Nazwisko</sf:label>
		<sf:input path="lastName" />
		<sf:errors path="lastName" />
	</div>
	<div>
		<sf:label path="email">Email</sf:label>
		<sf:input path="email"/>
		<sf:errors path="email" />
	</div>
	<div>
		<sf:label path="password">Haslo</sf:label>
		<sf:password path="password"/>
		<sf:errors path="password" />
	</div>
	<div>
		<sf:label path="confirmPassword">Potwierdz haslo</sf:label>
		<sf:password path="confirmPassword"/>
		<sf:errors path="confirmPassword" />
	</div>
	<div>
		<sf:label path="dateOfBirth">Wybierz date urodzenia</sf:label>
		<sf:input path="dateOfBirth" type="date" />
		<sf:errors path="dateOfBirth"/>
	</div>
	<div>
		<input type="submit" value="Zarejestruj" />
	</div>
</sf:form>