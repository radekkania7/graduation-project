<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<%-- 
DADAJ ZDJECIE //TODO
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="file" >
    <br />
    <input type="submit" value="Dodaj zdjecie">
</form>
--%>

DODAJ SPORTY, KTORE BEDA BRANE POD UWAGE W STATYSTYKACH
<sf:form modelAttribute="sportForm" action="/edit/addsport" method="POST" >
	<div>
		<sf:label path="sportName" >Sport</sf:label>
		<sf:select path="sportName"> 
			<sf:options items="${sports}" />
		</sf:select>
		<sf:errors path="sportName" />
	</div> 
</sf:form>


