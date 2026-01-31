<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String input=request.getParameter("input");
 if((request.getSession().getAttribute("name")!=null)||("forwardlogin".equalsIgnoreCase(input))){ %>
<div class="navbar">
<h1 id="appname"><%=application.getInitParameter("application_name")%> – Welcome <b><%= session.getAttribute("name") %></b></h1>
    <form action="controller" method="post">
    	<button name="input" value="logout">Logout</button>
    </form>
</div>
<%} else if("frontlogin".equalsIgnoreCase(input)||"frontsignup".equalsIgnoreCase(input)||"login".equalsIgnoreCase(input)||"signup".equalsIgnoreCase(input))
{%>

<div class="navbar">
	<h1 id="appname"><%=application.getInitParameter("application_name")%></h1>
	<form action="controller" method="post">
		<button type="submit" name="input" value="forward">Back</button>
	</form>
</div>
<%}else if("forward".equalsIgnoreCase(input) ||"logout".equalsIgnoreCase(input)){ %>
<div class="navbar">
	<h1 id="appname"><%=application.getInitParameter("application_name")%></h1>
	<div class="nav-buttons">
		<form action="controller" method="post">
			<button type="submit" name="input" value="frontlogin">Login</button>
			<button type="submit" name="input" value="frontsignup">Signup</button>
		</form>
	</div>
</div>
<% }%>