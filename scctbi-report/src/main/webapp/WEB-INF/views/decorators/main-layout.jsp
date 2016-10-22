<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html>

<c:import url="/WEB-INF/views/decorators/head.jsp"/>

<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/common/navbar.jsp"/>

        <div class="row container">
           <decorator:body />
        </div>
    </div>
    
</body>
</html>
