<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util"%>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld"%>


<c:if test="${not empty produtos}">
	<table class="table">
	    <thead>
	    	<th>Nome</th>
	    	<th>Valor</th>
	    </thead>
	    <c:forEach var="produto" items="${produtos}">
			<tr>
	    		<td>${produto.nome}</td>
	    		<td>${produto.precoNormal}</td>
	    	</tr>    
	    </c:forEach>
	</table>
	<ul class="pager">
	 	<li><a href="#">Next</a></li>
	</ul>
</c:if>