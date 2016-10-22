<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:if test="${layout}">
	<html>
<body>

<c:if test="${!hideSidebar}">
  <div class="col-md-2">
      <c:import url="/WEB-INF/views/reports/menu.jsp" />
  </div>
</c:if>

<c:if test="${!hideSidebar}">
  <div id="main-content" class="col-md-10">
</c:if>
<c:if test="${hideSidebar}">
  <div id="main-content" class="col-md-12">
</c:if>

</c:if>
<script>
	$(document).ready(function() {
		// binds form submission and fields to the validation engine

		$("input[validate='true']").addClass("validate[required]");
		$("select[validate='true']").addClass("validate[required]");
		$("#reportParamsForm").validationEngine({
			scroll : false
		});
		$("input.datepicker").datepicker({
			format : 'yyyy-mm-dd',
			clearBtn: true,
			autoclose: true,
			todayHighlight: true
		});
		$("select.multipled").multipleSelect();
		//showReport auto height
		var paramDiv = $("#rowId").height();
		var navbarDiv = $(".navbar").height();
		var bodyHeight = $(document).height()-36;
		$("#showReport").height(bodyHeight-(paramDiv+navbarDiv));
	});
	
	var showMgr = {
		exportDownload:function(type,id){
			var path = '<c:url value="/" />reports/'+id+'/export/'+type;
			if($('#reportParamsForm').length == 0){
				$("body").append('<form id="tempId" method="post" ></form>');
				$('#tempId').attr("action", path).submit();
			}else{
				//$('#reportParamsForm').attr("target", '');
				var formAction=$('#reportParamsForm').attr("action");
				$('#reportParamsForm').attr("action", path).submit();
				$('#reportParamsForm').attr("action", formAction);
			}
		}
	};
	
</script>
<div class="row">
	<div class="col-md-12">
		<div class="row" id="rowId" style="padding-bottom: 10px;">
			<nav class="navbar navbar-default report-title-bar" role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">${report.name }</a>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Export ... <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a
										href="javascript:showMgr.exportDownload(1,${report.id});">Excel</a></li>
									<li><a
										href="javascript:showMgr.exportDownload(2,${report.id});">Pdf</a></li>
									<li><a
										href="javascript:showMgr.exportDownload(3,${report.id});">Html</a></li>
								</ul></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
			<c:import url="/WEB-INF/views/common/_message.jsp" />

			<c:if var="isShow"
				test="${paramList!=null && fn:length(paramList)>0 }">
				<form id="reportParamsForm" action="<c:url value="/reports/${report.id}/body"/>" method="get"
                   target="report-body" class="form-inline" role="form">
                  <fieldset>
					<c:forEach items="${paramList}" var="field">
						<c:if var="isSelect" test="${field.selects!=null && fn:length(field.selects)>0 }">
							<c:if test="${field.renderType == 'checkbox' }" var="isCheck">
								<div class="checkbox">
									<c:forEach items="${field.selects }" var="select">
										<label>
											<input type="checkbox" name="${field.name }" value="${select.key }"/> ${select.text}
										</label> 
									</c:forEach>
								</div>
							</c:if>
							<c:if test="${!isCheck}">
								<div class="form-group">
   									<label>${field.label}</label>
		         					<select multiple="multiple" name="${field.name }" class="multipled">
										<c:forEach items="${field.selects }" var="select">
											<option value="${select.key }">${select.text }</option>
										</c:forEach>
									</select>
								</div>
							</c:if>
						</c:if>
						<c:if test="${!isSelect }">
							<c:if test="${field.type == 'Date' }" var="isDate">
	         					<div class="form-group">
    								<label>${field.label}</label>
	         						<input type="text" name="${field.name}"<c:if test="${ field.defaultValue!=null}"> value="${field.defaultValue}"</c:if> class="form-control datepicker" validate="${field.validate}">
								</div>
							</c:if>
							<c:if test="${!isDate && !field.hidden }">
							  <c:if test="${ field.name=='per_page'}">
                                <c:set var="per_page_param" value="${field}" />
                              </c:if>
                              <c:if test="${field.name=='page'}">
                                <c:set var="page_param" value="${field}" />
                              </c:if>
                              <c:if test="${ field.name!='per_page' && field.name!='page'}">
	         					<div class="form-group">
   									<label>${field.label} </label>
	         					   	<input type="text" name="${field.name}"<c:if test="${ field.defaultValue!=null}"> value="${field.defaultValue}"</c:if> class="form-control" validate="${field.validate}">
								</div>
                              </c:if>
							</c:if>
						</c:if>
					</c:forEach>
                    <c:if test="${per_page_param!=null }">
                      <div class="form-group paginate">
                          <label class="paginate">${per_page_param.label} </label>
                          <input type="text" min="1" name="${per_page_param.name}" value="${per_page_param.defaultValue}" class="form-control">
                      </div>
                    </c:if>
                    <c:if test="${page_param!=null }">
                      <div class="form-group paginate">
                          <label class="paginate">${page_param.label} </label>
                          <input type="number" min="1" name="${page_param.name}" value="${page_param.defaultValue}" class="form-control">
                      </div>
                    </c:if>
                    <div class="form-group">
					    <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                  </fieldset>
				</form>
			</div>
			<div id="showReport" class="report-container" >
				<iframe name="report-body" class="report-container" style="height: 100%;width: 100%;"  src=""></iframe>
			</div>
		</c:if>
		<c:if test="${!isShow}">
			</div>
			<div class="embed-responsive embed-responsive-4by3 report-container">
				<iframe class="embed-responsive-item" src="<c:url value="/reports/${report.id}/body"/>"></iframe>
			</div>
		</c:if>
</div>
<!--/col-->
</div>
<!--/row-->
<c:if test="${layout}">
	</div>
	<!--/col-->
	</body>
	</html>
</c:if>
