<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
	<form method="POST" action="uploadfile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file">
 
		Name: <input type="text" name="name">
 
		<input type="submit" value="Upload"> Press here to upload the file!
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>	
</body>
</html>