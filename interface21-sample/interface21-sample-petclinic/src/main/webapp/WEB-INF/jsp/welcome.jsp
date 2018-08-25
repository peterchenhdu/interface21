<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<H2><fmt:message key="welcome"/></H2>
<P>
    <A href="<c:url value="/findOwners.htm"/>"><fmt:message key="find.owner"/></A>
<P>
    <A href="<c:url value="/vets.htm"/>"><fmt:message key="display.all.veterinarians"/></A>
<P>
    <A href="<c:url value="/html/petclinic.html"/>"><fmt:message key="tutorial"/></A>
<P>
    <A href="<c:url value="/doc/Spring-MVC-step-by-step.html"/>"><fmt:message key="documentation"/></A>

