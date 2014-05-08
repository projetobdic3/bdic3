<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.*"
	import="br.ita.bdic3.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Efetuar Transacao</title>
</head>
<body>
	<!-- Testando Fetch -->
	<!-- Testando Fetch3 -->
	<h1>Efetuar Transacao</h1>

	<%
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("bdic3PU");
		EntityManager em = emf.createEntityManager();
		try {
			Transacao t = new Transacao();
			t.setValor(1274);

			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			em.getTransaction().rollback();
		}
	%>

</body>
</html>