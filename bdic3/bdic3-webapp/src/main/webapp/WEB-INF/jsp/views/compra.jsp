<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<form:form action="/bdic3/pedido" method="post" modelAttribute="pedido">
<%-- <form action="/bdic3/pedido" method="post"> --%>
    <div class="form-group">
        <label for="inputEmail">Quantidade</label>
        <form:input type="text" path="name" />
    </div>
    <div class="form-group">
        <label for="inputPassword">Número Cartão</label>
        <form:input type="text" path="name" />
    </div>
    <button type="submit" class="btn btn-primary">Comprar</button>
</form:form>