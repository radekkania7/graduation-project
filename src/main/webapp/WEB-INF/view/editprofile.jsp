<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="file" >
    <br />
    <input type="submit" value="Dodaj zdjecie">
</form>

<c:if test="${not empty message}">
    ${message}
</c:if>
