<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<c:if test="${not empty erro}">
	<h1>${erro}</h1>
</c:if>

<form:form id="pedidoForm" action="/bdic3/product/comprar" modelAttribute="pedidoVO" method="POST">
	<form:hidden path="idProduto" value="${idProduto}" />
	<form:hidden path="idCliente" value="1" />
	<%-- <form:hidden path="latitude" value="123" />
	<form:hidden path="longitude" value="321" /> --%>
	<form:hidden path="formaPagamento" value="CARTAO_CREDITO" />
	
    <label for="quantidade">Quantidade:</label>
    <form:input path="quantidade" value="1" />
    
    </br>
    </br>
    <label for="numeroCartao">Número Cartão:</label>
    <form:input path="numeroCartao" value="123" />
    
    </br>
    </br>
    <label for="quantidadeParcelas">Parcelas:</label>
	<form:select path="quantidadeParcelas">
  		<form:option value="0" label="Selecione"/>
  		<form:option value="1" label="1"/>
  		<form:option value="2" label="2"/>
  		<form:option value="3" label="3"/>
  		<form:option value="4" label="4"/>
		<form:option value="5" label="5"/>
	</form:select>
	
    </br>
    <label for="cidade">Cidade:</label>
    <form:select path="cidade" id="cidade">
    	<form:option value="" label="Selecione"></form:option>
        <form:options items="${cidades}" itemLabel="nome" itemValue="nome"></form:options>
    </form:select>
	
	</br>
    <button type="submit" class="btn btn-primary">Confirmar</button>
</form:form>

