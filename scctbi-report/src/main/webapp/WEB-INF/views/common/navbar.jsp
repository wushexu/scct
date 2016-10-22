<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">SCCT-BI</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="<c:url value="/reports" />">Reports</a></li>
            <li><a href="<c:url value="/dimensional" />">Dimensional Reports</a></li>
            <li><a href="<c:url value="/dimensional/analysis" />">Dimensional Analysis</a></li>
            <li><a href="<c:url value="/reportDefinitions" />">Report Management</a></li>
            <li><a href="<c:url value="/category" />">Category Management</a></li>
            <%-- <li><a href="<c:url value="/form" />">User Roles</a></li> --%>
          </ul>
        </div>
  </div>
</div>

<script>
  $(document).ready(function() {
    var url = window.location;
    $('ul.nav a[href="'+ url.pathname +'"]').parent().addClass('active');
    $('ul.nav a').filter(function() {
      return this.href == url;
    }).parent().addClass('active');
  });

/*   $(".nav li").on("click", function() {
    $(".nav li").removeClass("active");
    $(this).addClass("active");
  }); */
</script>
