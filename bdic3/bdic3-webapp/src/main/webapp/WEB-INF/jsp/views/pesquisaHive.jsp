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

<h1>Pesquisa Hive</h1>

<c:if test="${not empty mensagemErro}">
	<h3>${mensagemErro}</h3>
</c:if>
<form:form id="validacaoForm" action="/bdic3/hive/pesquisar" modelAttribute="pesquisaHiveVO" method="POST">
  <div class="form-group">
    <label for="valor">Valor</label>
    <form:input path="valor" cssClass="form-control"  placeholder="Entre com o valor R$" />
  </div>
  <div class="form-group">
    <label for="dataInicial">Data Incial</label>
    <form:input path="dataInicial" cssClass="form-control"   placeholder="dd/mm/yyyy" />
  </div>
   <div class="form-group">
    <label for="dataFinal">Data Final</label>
    <form:input path="dataFinal" cssClass="form-control" placeholder="dd/mm/yyyy" />
  </div>
  <div class="form-group">
    <form:select path="localidade" cssClass="form-control">
    <form:option value="NONE" label="--- Select ---"/>
  		<form:options items="${localidades}" /> 
	</form:select> 
  </div>
 
  <button type="submit" class="btn btn-primary">Enviar</button>
</form:form>
