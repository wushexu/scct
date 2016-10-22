<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

        <div id="formsContent">
          <h2>Report</h2>

          <form:form id="form" method="post" modelAttribute="report"
            class="form-horizontal" role="form"
            enctype="multipart/form-data" acceptcharset="UTF-8">
            
            <c:import url="/WEB-INF/views/common/_message.jsp" />
          
            <s:bind path="*">
              <c:if test="${status.error}">
                <div class="form-group">
                  <form:errors path="" class="control-label has-error" />
                </div>
              </c:if>
            </s:bind>

            <c:if test="${id != null}">
              <form:hidden path="id" />
            </c:if>

            <fieldset>

              <div class="form-group">
                <form:label class="col-lg-2 control-label" path="code"> 
                Code 
                </form:label>
                <div
                  class="col-lg-6<s:bind path="name"><c:if test="${status.error}"> has-error</c:if></s:bind>">
                  <form:input path="code" class="form-control"
                    placeholder="code" />
                </div>
                <form:errors path="code" class="has-error" />
              </div>
            </fieldset>

            <fieldset>

              <div class="form-group">
                <form:label class="col-lg-2 control-label" path="name"> 
				Name 
                </form:label>
                <div
                  class="col-lg-6<s:bind path="name"><c:if test="${status.error}"> has-error</c:if></s:bind>">
                  <form:input path="name" class="form-control"
                    placeholder="name" />
                </div>
                <form:errors path="name" class="has-error" />
              </div>
            </fieldset>

            <fieldset>

              <div class="form-group">
                <form:label class="col-lg-2 control-label"
                  path="description"> 
                Memo <form:errors path="memo" class="has-error" />
                </form:label>
                <div class="col-lg-6">
                  <form:input path="memo" class="form-control"
                    placeholder="memo" />
                </div>
              </div>
            </fieldset>
			
			<fieldset>

              <div class="form-group">
                <form:label class="col-lg-2 control-label"
                  path="categoryId"> 
                Category <form:errors path="categoryId" class="has-error" />
                </form:label>
                <div class="col-lg-6">
                  <select class="form-control" id="cid" name="cid">
                  <c:forEach items="${categorys }" var="category">
                  		<c:if test="${category.id == report.categoryId.id }" var="isCheck">
                  			<option value="${category.id }" selected="selected">${category.name }</option>	
                  		</c:if>
                  		<c:if test="${!isCheck }">
                  			<option value="${category.id }" >${category.name }</option>
                  		</c:if>
                  </c:forEach>
                  </select>
                </div>
              </div>
            </fieldset>
				
            <fieldset>
              <div class="form-group">
                <form:label class="col-lg-2 control-label"
                  path="description"> 
                Report File <form:errors path="content"
                    class="has-error" />
                </form:label>
                <div
                  class="col-lg-6<s:bind path="content"><c:if test="${status.error}"> has-error</c:if></s:bind>">
                  <input type="file" name="reportFile"
                    class="form-control" />
                </div>
              </div>
            </fieldset>

            <p>
              <button type="submit" class="btn btn-primary">Submit</button>
            </p>
          </form:form>

        </div>

      </div>
      <!--/col-->
    </div>
    <!--/row-->
  </div>
  <!--/col-->

</body>
</html>
