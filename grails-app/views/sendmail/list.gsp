
<%@ page import="test.Sendmail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sendmail.label', default: 'Sendmail')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-sendmail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-sendmail" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="emailAdd" title="${message(code: 'sendmail.emailAdd.label', default: 'Email Add')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'sendmail.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="content" title="${message(code: 'sendmail.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="sendTime" title="${message(code: 'sendmail.sendTime.label', default: 'Send Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${sendmailInstanceList}" status="i" var="sendmailInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${sendmailInstance.id}">${fieldValue(bean: sendmailInstance, field: "emailAdd")}</g:link></td>
					
						<td>${fieldValue(bean: sendmailInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: sendmailInstance, field: "content")}</td>
					
						<td><g:formatDate date="${sendmailInstance.sendTime}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${sendmailInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
