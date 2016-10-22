<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

        <div id="formsContent">
          <h2>Category</h2>

          <form:form id="form" method="post" modelAttribute="category"
            class="form-horizontal" role="form" acceptcharset="UTF-8">
            
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
                <form:label class="col-lg-2 control-label" path="name"> 
				Name 
                </form:label>
                <div
                  class="col-lg-6">
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
                Description 
                </form:label>
                <div class="col-lg-6">
                  <form:input path="description" class="form-control"
                    placeholder="description" />
                </div>
                <form:errors path="description" class="has-error" />
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
