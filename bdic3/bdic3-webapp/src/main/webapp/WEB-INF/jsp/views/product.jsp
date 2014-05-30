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


<c:if test="${not empty produtos}">
	<div class="row">
		<c:forEach var="produto" items="${produtos}">
		<div class="col-sm-6 col-md-4">
			<div class="thumbnail">
				<img data-src="produto.png" alt="Imagem Ilustrativa">
				<div class="caption">
					<h3>${produto.nome}</h3>
					<p>${produto.precoNormal}</p>
					<p>
						<a href="/bdic3/product/${produto.id}" class="btn btn-primary" role="button">Comprar</a> 
					</p>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</c:if>

	<%-- <table class="table">
		<thead>
			<th>Nome</th>
			<th>Preço</th>
		</thead>
		<c:forEach var="produto" items="${produtos}">
			<tr onclick="window.location.href='/bdic3/product/${produto.id}'">
				<td>${produto.nome}</td>
				<td>${produto.precoNormal}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${produtos.size() > 15}">
		<ul class="pager">
			<li><a href="#">Next</a></li>
		</ul>
	</c:if> --%>