<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>MENU</h1>

<div>
	<a href="<c:url value='/' />" class="btn btn-success custom-width">Twoj profil</a> <br/>
</div>
<div>
	<a href="<c:url value='/edit' />" class="btn btn-success custom-width">Edytuj profil</a> <br/>
</div>
<div>
	<a href="<c:url value='/events' />" class="btn btn-success custom-width">Szukaj wydarzen</a> <br/>
</div>
<div>
	<a href="<c:url value='/createNewEvent' />" class="btn btn-success custom-width">Utworz wydarzenie</a> <br/>
</div>