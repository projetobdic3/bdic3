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

<%-- Emppty dust.js template --%>

<h1>Contestacao - 4a Etapa</h1>

<c:if test="${not empty mensagemErro}">
	<h3>${mensagemErro}</h3>
</c:if>

<table class="table table-striped">
	<thead>
		<tr>
			<td>CPF</td>
			<td>Nome</td>
			<td>Valor</td>
			<td>Data</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="contestacao" items="${contestacoes}">
			<tr>
				<td>${contestacao.cpfCliente}</td>
				<td>${contestacao.nomeCliente}</td>
				<td>${contestacao.valorTransacao}</td>
				<td>${contestacao.dataTransacao}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>