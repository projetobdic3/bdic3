<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<%-- Emppty dust.js template --%>
<form id="validacaoForm" action="/bdic3/hive/pesquisarRelatorio"  method="POST">
 <div class="form-group">
    <label for="valorInicial">Codigo do relatorio:</label>
    <input type="text" id="id" name="id" class="form-control"  placeholder="Entre com o valor  R$" />
  </div>
   <button type="submit" class="btn btn-primary" ">Enviar</button>
</form>