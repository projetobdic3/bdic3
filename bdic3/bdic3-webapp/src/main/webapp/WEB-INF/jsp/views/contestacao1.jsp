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

<h1>Confirmação de Identidade</h1>

<c:if test="${not empty mensagemErro}">
	<div class="alert alert-danger">${mensagemErro}</div>
</c:if>

<form:form 
	id="validacaoForm" 
	action="/bdic3/contestacao/contestacao2"
	modelAttribute="contestacaoVO" 
	method="POST" 
	class="form-horizontal"
	role="form">

	<div class="form-group">
		<label for="nomeCliente" class="col-sm-2 control-label">Nome do Cliente</label>
		<div class="col-sm-10"><form:input path="nomeCliente" class="form-control"	placeholder="Nome do Cliente" /></div>
	</div>

	<div class="form-group">
		<label for="cpfCliente" class="col-sm-2 control-label">CPF do Cliente</label>
		<div class="col-sm-10"><form:input path="cpfCliente" class="form-control"	placeholder="CPF do Cliente" /></div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">Confirmar Identidade</button>
		</div>
	</div>
</form:form>
