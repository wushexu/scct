<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<body>
  <div class="col-md-2">
<%--     <div class="well sidebar-nav">
      <ul class="nav nav-list">
        <li class="nav-header">Reports</li>
        <c:forEach items="${reports}" var="report">
          <li><a
            href="${saikuUrl}/#reports/${report.code}"
            target="dimemsionalReportFrame"><c:out
                value="${report.name}" /></a></li>
        </c:forEach>
      </ul>
    </div> --%>
    <div class="sidebar-nav">
      <div class="panel-group" id="menu_accordion">
        <c:forEach items="${menus }" var="menu" varStatus="varStatus">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#menu_accordion"
                  href="#r${menu.id}"> ${menu.name} <%-- <span class="badge">${fn:length(menu.list)}</span> --%></a>
              </h4>
            </div>
            <div id="r${menu.id }" class="panel-collapse collapse">
              <div class="panel-body">
                <table class="table">
                  <c:forEach items="${menu.list }" var="report">
                    <tr>
                      <td><a
                        href="${saikuUrl}/#reports/${report.code}" target="dimemsionalReportFrame">${report.name}</a>
                      </td>
                    </tr>
                  </c:forEach>
                </table>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>

<script>
  $(document).ready(function() {
/*     $(".sidebar-nav .nav li").not(".nav-header").on("click", function() {
     $(".sidebar-nav .nav li").removeClass("active");
     $(this).addClass("active");
   }); */

	$(".sidebar-nav table a").on("click", function() {
		$(".sidebar-nav table a.active").removeClass("active");
		$(this).addClass("active");
	});
    var url = window.location;
    //$('.sidebar-nav table a[href="'+ url.pathname +'"]').addClass('active');
    var $activeLink=$('.sidebar-nav table a').filter(function() {
      var qm=this.href.indexOf('?');
      var hb1=(qm>=0)? this.href.substr(0,qm):this.href;
      qm=url.href.indexOf('?');
      var hb2=(qm>=0)? url.href.substr(0,qm):url.href;
      return hb1 == hb2;
    });
    if($activeLink.length>0){
    	$activeLink.addClass('active');
    	$('.sidebar-nav .panel-collapse.in').removeClass('in');
    	$activeLink.parents('.panel-collapse').addClass('in');
    }else{
    	$($('.sidebar-nav .panel-collapse')[0]).addClass('in');
    }
  });
</script>

  <div id="main-content" class="col-md-10">

    <div class="row">
      <div
        class="embed-responsive embed-responsive-4by3 saiku-container">
        <iframe name="dimemsionalReportFrame"
          class="embed-responsive-item" src=""></iframe>
      </div>
    </div>

  </div>
  <!--/col-->
</body>
</html>