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

<h1>Contestacao - 2a Etapa</h1>

<c:if test="${not empty mensagemErro}">
	<h3>${mensagemErro}</h3>
</c:if>

<form:form id="validacaoForm" action="/bdic3/contestacao/contestacao3" modelAttribute="contestacaoVO" method="POST">
	Nome do Cliente: ${contestacaoVO.nomeCliente}
	<form:hidden path="nomeCliente" value="${contestacaoVO.nomeCliente}" />
	<br>
	CPF do Cliente: ${contestacaoVO.cpfCliente}
	<form:hidden path="cpfCliente" value="${contestacaoVO.cpfCliente}" />
	<br>
	<label for="valorTransacao">Valor da Transacao</label>
	<form:input path="valorTransacao" />
	<br>
	<label for="dataTransacao">Data da Transacao</label>
	<form:input path="dataTransacao" />
	<br>
	<button type="submit" class="btn btn-primary">Validar Transacao</button>
</form:form>