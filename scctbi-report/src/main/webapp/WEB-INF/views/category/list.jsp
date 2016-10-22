<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<body>
  <div class="col-md-2">
    <div class="well sidebar-nav">
      <c:import url="/WEB-INF/views/category/menu.jsp" />
    </div>
  </div>

  <div id="main-content" class="col-md-10">

    <div class="row">
      <div class="col-md-12">

        <div>
          <h2>Category</h2>
          <c:import url="/WEB-INF/views/common/_message.jsp" />
          
          <p class="text-right">
            <a href="<c:url value="/category/new" />" >New</a>
          </p>
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Create Date</th>
              <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categorys}" var="category">
              <tr>
                <td>${category.name}</td>
                <td>${category.description}</td>
                <td><fmt:formatDate value="${category.createDate}" pattern="yyyy-MM-dd"/></td>
                <td><a
                  href="<c:url value="/category/${category.id}/edit" />">Edit</a>
                  <a
                  href="<c:url value="/category/${category.id}/delete" />"
                  data-confirm="Are You Sure?" data-method="post">Delete</a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>

        </div>


      </div>
      <!--/col-->
    </div>
    <!--/row-->
  </div>
  <!--/col-->
</body>
</html>