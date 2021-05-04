<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/index.css">
<title>${title}</title>
</head>
<body>
<div class="elements-tree">
	<!--  ELEMENT LIST -->
	<div class="elements-list">
		<div class="row header">
			<a class="name selected" href="Explorar?orderby=name&ascdesc=asc"><span>Nombre</span></a>
			<a class="date selected" href="Explorar?orderby=updated_at&ascdesc=desc"><span>Fecha modificado</span></a>
		</div>
		
		
		<c:forEach items="${elements}" var="element">
		<div class="row">
		
			
		
			<a class="name" href="Explorar?id=${element.id}">
				
			<c:if test="${element.type == 'carpeta'}">
			  <img class="icon" src="images/icons/folder.png"/>
			</c:if>	
			
			<c:if test="${element.type == 'archivo'}">
			  <img class="icon" src="images/icons/${element.extension}.png"/>
			</c:if>	
			
			<c:if test="${element.type == 'carpeta'}">
				<span class="text">${element.name}</span>
			</c:if>
				
			<c:if test="${element.type == 'archivo'}">
				<span class="text">${element.name}</span>
				<span class="text">.${element.extension}</span>
			</c:if>
			
			</a>
			<div class="date">${element.updated_at}</div>
			<a class="action delete" href="Explorar?idtodelete=${element.parent_id}">Eliminar</a>
		
		</div>
		</c:forEach>
		
		
		
	</div>
	<!-- INSERT FORM -->
	<form action="" method="POST">
		<div>
			Añadir nuevo:
		</div>

		
		<div>
			<label for="carpeta"><input type="radio" id="carpeta" name="newtype" value="carpeta"> Carpeta</label>
			<label for="archivo"><input type="radio" id="archivo" name="newtype" value="archivo"> Archivo</label>
		</div>
		<div>
		
			<!-- COMPLETAR area de texto y botón, pistas en index.css-->
			<input type="text" name="newname">
			<input type="submit" value="Enviar" >		
		</div>
	</form>
</div>

<!-- SEGUNDA COLUMNA -->
<div class="element-content">
	<div class="element-content-area">
		
		<c:if test="${element.type == 'archivo'}">
			<p>${element.content}</p>
		</c:if>
		<!-- AQUI se debe mostrar el contenido de los fichero si es que lo tienen -->

	</div>
</div>



</body>
</html>