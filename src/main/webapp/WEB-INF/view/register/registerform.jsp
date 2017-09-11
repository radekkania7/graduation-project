<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<c:if test="${info ne null}" >
	"${info}"
</c:if>
	<div class="col-sm-2">
	</div>
	<div class="col-sm-8 well">
		<div class="title">
			<h3>ZAREJESTRUJ KONTO</h3>
		</div>

		<sf:form method="POST" modelAttribute="user" >
			<sf:input type="hidden" path="id"/>
			<div>
				<sf:label path="nickname">Login</sf:label>
				<sf:input class="input form-control" path="nickname" />
				<div class="error">
					<sf:errors path="nickname" />
				</div>
			</div>
			<div>
				<sf:label path="firstName" >Imie</sf:label>
				<sf:input class="input form-control" path="firstName" />
				<div class="error">
					<sf:errors path="firstName" />
				</div>
			</div>
			<div>
				<sf:label path="lastName" >Nazwisko</sf:label>
				<sf:input class="input form-control" path="lastName" />
				<div class="error">
					<sf:errors path="lastName" />
				</div>
			</div>
			<div>
				<sf:label path="email">Email</sf:label>
				<sf:input class="input form-control" path="email"/>
				<div class="error">
					<sf:errors path="email" />
				</div>
			</div>
			<div>
				<sf:label path="password">Haslo</sf:label>
				<sf:password class="input form-control" path="password"/>
				<div class="error">
					<sf:errors path="password" />
				</div>
			</div>
			<div>
				<sf:label path="confirmPassword">Potwierdz haslo</sf:label>
				<sf:password class="input form-control" path="confirmPassword"/>
				<div class="error">
					<sf:errors path="confirmPassword" />
				</div>
			</div>
			<div>
				<sf:label path="dateOfBirth">Wybierz date urodzenia</sf:label>
				<sf:input class="input form-control" path="dateOfBirth" type="date" />
				<div class="error">
					<sf:errors path="dateOfBirth"/>
				</div>
			</div>
			<div>
				<input class="center" type="submit" value="Zarejestruj" />
			</div>
		</sf:form>
	</div>
<div class="col-sm-2">
</div>