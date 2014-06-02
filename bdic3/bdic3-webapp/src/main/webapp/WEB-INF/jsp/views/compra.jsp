<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<form:form id="pedidoForm" action="/bdic3/product/comprar" modelAttribute="pedidoVO" method="POST">
	<form:hidden path="idProduto" value="${idProduto}" />
	<form:hidden path="idCliente" value="1" />
	<form:hidden path="latitude" value="123" />
	<form:hidden path="longitude" value="321" />
	<form:hidden path="formaPagamento" value="CARTAO_CREDITO" />
	
    <div class="form-group">
        <label for="quantidade">Quantidade:</label>
        <form:input type="text" path="quantidade" width="30px" maxlength="3" />
    </div>
    <div class="form-group">
        <label for="numeroCartao">Número Cartão:</label>
        <form:input type="text" path="numeroCartao" />
        </br>
        <label for="quantidadeParcelas">Parcelas:</label>
        <form:input type="text" path="quantidadeParcelas" maxlength="2" />
    </div>
    <button type="submit" class="btn btn-primary">Confirmar</button>
</form:form>