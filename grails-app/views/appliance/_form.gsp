<%@ page import="hekexercise.Appliance" %>



<div class="fieldcontain ${hasErrors(bean: applianceInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="appliance.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${applianceInstance?.name}"/>

</div>

