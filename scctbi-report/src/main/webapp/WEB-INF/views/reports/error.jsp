<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<body>
  <div class="row">
    <div class="col-md-8">
      <h3>
        Failed.<br />
      </h3>
      <c:if test="${not empty errorMessage}">
        <div class="has-error">
          <span class="control-label">${errorMessage}</span>
        </div>
      </c:if>
      <c:if test="${empty errorMessage && not empty exception}">
        <div class="has-error">
          <span class="control-label">${exception.message}</span>
        </div>
      </c:if>
<!--
<c:if test="${not empty exception}">
Exception:  ${exception.message}
<c:forEach items="${exception.stackTrace}" var="ste">${ste} 
</c:forEach>
</c:if>
-->
    </div>
  </div>

</body>
</html>