<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav nav-list">
  <li class="nav-header">Report Management</li>
  <c:forEach items="${categorys}" var="category">
  	<li><a href="<c:url value="/reportDefinitions/category/${category.id}" />">${category.name }</a></li>
  </c:forEach>
</ul>
