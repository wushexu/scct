<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
                    href="<c:url value="/reports/${report.id}" />?_layout=false"
                    data-remote data-update="main-content">${report.code}</a>
                  </td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
  <!-- data-remote data-update="main-content" ?_layout=false -->
</div>
<script>
	$(document).ready(function() {
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