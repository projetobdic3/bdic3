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

<h1>Contestacao - 3a Etapa</h1>

<c:if test="${not empty mensagemErro}">
	<h3>${mensagemErro}</h3>
</c:if>

<form:form id="validacaoForm" action="/bdic3/contestacao/contestacao4" modelAttribute="contestacaoVO" method="POST">
	Nome do Cliente: ${contestacaoVO.nomeCliente}
	<form:hidden path="nomeCliente" value="${contestacaoVO.nomeCliente}" />
	<br>
	CPF do Cliente: ${contestacaoVO.cpfCliente}
	<form:hidden path="cpfCliente" value="${contestacaoVO.cpfCliente}" />
	<br>
	Valor da Transacao: ${contestacaoVO.valorTransacao}
	<form:hidden path="valorTransacao" value="${contestacaoVO.valorTransacao}" />
	<br>
	Data da Transacao: ${contestacaoVO.dataTransacao}
	<form:hidden path="dataTransacao" value="${contestacaoVO.dataTransacao}" />
	<br>
	<label for="dataInicial">Data Inicial</label>
	<form:input path="dataInicial" />
	<label for="dataFinal">Data Final</label>
	<form:input path="dataFinal" />
	<br>
	<label for="valorInicial">Valor Inicial</label>
	<form:input path="valorInicial" />
	<label for="valorFinal">Valor Final</label>
	<form:input path="valorFinal" />
	<br>
	<button type="submit" class="btn btn-primary">Validar Transacao</button>
</form:form>