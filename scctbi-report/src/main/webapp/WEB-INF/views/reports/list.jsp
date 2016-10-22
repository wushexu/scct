<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<body>
  <div class="col-md-2">
      <c:import url="/WEB-INF/views/reports/menu.jsp" />
  </div>

  <div id="main-content" class="col-md-10">

    <div class="row">
      <div class="col-md-12">

        <div>
          <c:import url="/WEB-INF/views/common/_message.jsp" />

        </div>

      </div>
      <!--/col-->
    </div>
    <!--/row-->
  </div>
  <!--/col-->
</body>
</html>