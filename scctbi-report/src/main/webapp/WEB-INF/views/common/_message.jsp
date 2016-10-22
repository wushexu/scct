<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty message}">
  <div class="has-success">
    <span class="control-label">${message}</span>
  </div>
</c:if>
<c:if test="${not empty errorMessage}">
  <div class="has-error">
    <span class="control-label">${errorMessage}</span>
  </div>
</c:if>
