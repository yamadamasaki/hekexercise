<%@ page import="hekexercise.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'fromDate', 'error')} required">
	<label for="fromDate">
		<g:message code="reservation.fromDate.label" default="From Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fromDate" precision="day"  value="${reservationInstance?.fromDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'toDate', 'error')} required">
	<label for="toDate">
		<g:message code="reservation.toDate.label" default="To Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="toDate" precision="day"  value="${reservationInstance?.toDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'what', 'error')} required">
	<label for="what">
		<g:message code="reservation.what.label" default="What" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="what" name="what.id" from="${hekexercise.Appliance.list()}" optionKey="id" required="" value="${reservationInstance?.what?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'who', 'error')} required">
	<label for="who">
		<g:message code="reservation.who.label" default="Who" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="who" name="who.id" from="${hekexercise.Person.list()}" optionKey="id" required="" value="${reservationInstance?.who?.id}" class="many-to-one"/>

</div>

