<%@ page import="test.Sendmail" %>



<div class="fieldcontain ${hasErrors(bean: sendmailInstance, field: 'emailAdd', 'error')} required">
	<label for="emailAdd">
		<g:message code="sendmail.emailAdd.label" default="Email Add" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="emailAdd" required="" value="${sendmailInstance?.emailAdd}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sendmailInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="sendmail.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${sendmailInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sendmailInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="sendmail.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>

	
	<textarea id="content" name="content" cols="10" rows="6">${sendmailInstance?.content}</textarea>
	
</div>

<div class="fieldcontain ${hasErrors(bean: sendmailInstance, field: 'sendTime', 'error')} required">
	<label for="sendTime">
		<g:message code="sendmail.sendTime.label" default="Send Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="sendTime" precision="day"  value="${sendmailInstance?.sendTime}"  />
</div>

