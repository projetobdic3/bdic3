<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util"%>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld"%>

<%-- Emppty dust.js template --%>

<h1>Exibição dos Dados</h1>

<c:if test="${not empty mensagemErro}">
	<div class="alert alert-danger">${mensagemErro}</div>
</c:if>

<div class="alert alert-success">Fraude Confirmada com Sucesso!</div>