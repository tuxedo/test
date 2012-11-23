
<%@ page import="test.Sendmail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sendmail.label', default: 'Sendmail')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-sendmail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-sendmail" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list sendmail">
			
				<g:if test="${sendmailInstance?.emailAdd}">
				<li class="fieldcontain">
					<span id="emailAdd-label" class="property-label"><g:message code="sendmail.emailAdd.label" default="Email Add" /></span>
					
						<span class="property-value" aria-labelledby="emailAdd-label"><g:fieldValue bean="${sendmailInstance}" field="emailAdd"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sendmailInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="sendmail.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${sendmailInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sendmailInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="sendmail.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${sendmailInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sendmailInstance?.sendTime}">
				<li class="fieldcontain">
					<span id="sendTime-label" class="property-label"><g:message code="sendmail.sendTime.label" default="Send Time" /></span>
					
						<span class="property-value" aria-labelledby="sendTime-label"><g:formatDate date="${sendmailInstance?.sendTime}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${sendmailInstance?.id}" />
					<g:link class="edit" action="edit" id="${sendmailInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
