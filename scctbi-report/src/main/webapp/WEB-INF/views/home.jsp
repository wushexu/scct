<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="decorator" content="no-navbar" />
  </head>

  <body>  
     <div class="row">
        <div class="col-md-4">
          <h3> 
              SCCT BI<br />
          </h3>
          <ul class="nav">
            <li><a href="<c:url value="/reports" />">Regular Reports</a></li>
            <li><a href="<c:url value="/dimensional" />">Dimensional Reports</a></li>
            <li><a href="<c:url value="/dimensional/analysis" />">Dimensional Analysis</a></li>
            <%-- <li><a href="<c:url value="/reportDefinitions" />">Report Manager</a></li>
            <li><a href="<c:url value="/category" />">Category Manager</a></li> --%>
          </ul>
        </div>
     </div>

 </body>
</html>