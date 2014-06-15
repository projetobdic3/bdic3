<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util"%>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:if test="${empty suspeitasFraude}">
	<h2>Nenhum resultado encontrado!</h2>
</c:if>
<c:if test="${not empty suspeitasFraude}">
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Possiveis Fraudes</div>
  <!-- Table -->
  <table class="table">
  <thead>
  	<tr>
	  	<th>id</th>
	  	<th>Latitude</th>
	  	<th>Longitude</th>
	  	<th>Data</th>
	  	<th>Valor</th>
	  	<th>Cidade</th>
  	</tr>
  </thead>
  <tbody>
  <c:forEach var="suspeitaFraude" items="${suspeitasFraude}">
	  <tr>
		  <td>${suspeitaFraude.cli_id}</td>
		  <td>${suspeitaFraude.loc_latitude}</td>
		  <td>${suspeitaFraude.loc_longitude}</td>
		  <td>${suspeitaFraude.data}"</td>
		  <td>${suspeitaFraude.valor}</td>
		  <td>${suspeitaFraude.localidade}</td>
		</tr>
	</c:forEach>
  </tbody>
  </table>
</div>
</c:if>


