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
      <c:import url="/WEB-INF/views/reportDefinitions/menu.jsp" />
    </div>
  </div>

  <div id="main-content" class="col-md-10">

    <div class="row">
      <div class="col-md-12">

        <div>
          <h2>Report</h2>
          <c:import url="/WEB-INF/views/common/_message.jsp" />
          
          <p class="text-right">
            <a href="<c:url value="/reportDefinitions/new" />?cid=${cid}">New</a>
          </p>
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Code</th>
              <th>Name</th>
              <th>Memo</th>
              <th>Category</th>
              <th>Last Modified</th>
              <th>Report File</th>
              <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
              <tr>
                <td><c:out value="${report.code}" /></td>
                <td><c:out value="${report.name}" /></td>
                <td><c:out value="${report.memo}" /></td>
                <td>${report.categoryId.name }</td>
                <td><joda:format pattern="yyyy-MM-dd HH:mm"
                    value="${report.lastModifiedDate}" /></td>
                <td><fmt:formatNumber type="number"
                    maxFractionDigits="1"
                    value="${report.contentSize / 1024}" />K <c:if
                    test="${report.contentSize > 0}">
                    <a
                      href="<c:url value="/reportDefinitions/${report.id}/download" />"
                      data-method="post">Download</a>
                  </c:if></td>
                <td><a
                  href="<c:url value="/reportDefinitions/${report.id}/edit" />">Edit</a>
                  <a
                  href="<c:url value="/reportDefinitions/${report.id}/delete" />"
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