package foross.scctbi.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportDataFactoryException;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.HtmlReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.engine.classic.core.parameters.DefaultParameterContext;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterContext;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterDefinitionEntry;
import org.pentaho.reporting.engine.classic.core.parameters.ReportParameterDefinition;
import org.pentaho.reporting.engine.classic.core.util.ReportParameterValues;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foross.scctbi.data.model.Category;
import foross.scctbi.data.model.ReportDefinition;
import foross.scctbi.data.repository.CategoryRepository;
import foross.scctbi.data.repository.ReportRepository;
import foross.scctbi.data.vo.FieldParams;
import foross.scctbi.data.vo.MenuVo;
import foross.scctbi.service.ReportService;
import foross.scctbi.web.common.AjaxUtils;
import foross.scctbi.web.common.ControllerUtil;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	ReportRepository repository;

	@Autowired
	ReportService reportService;

	@Autowired
	private CategoryRepository categoryRepository;

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		boolean isAjaxRequest = AjaxUtils.isAjaxRequest(request);
		boolean noLayout = isAjaxRequest
				|| "false".equals(request.getParameter("_layout"));
		model.addAttribute("layout", !noLayout);
		model.addAttribute("hideSidebar",
				"false".equals(request.getParameter("_sidebar")));
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("reports/error");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {

		loadReports(model);
		return "reports/list";
	}

	private void loadReports(Model model) {
		setMenu(model);
		Iterable<ReportDefinition> reports = repository.findAll();
		model.addAttribute("reports", reports);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id,
			@ModelAttribute("layout") boolean layout, WebRequest webRequest,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws ReportDataFactoryException {
		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			model.addAttribute("errorMessage", "report #" + id + " not found");
			return "reports/error";
		}
		try {
			showParameter(layout, model, report);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exception", e);
			return "reports/error";
		}
		return "reports/show";
	}

	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
	public String showByCode(@PathVariable String code,
			@ModelAttribute("layout") boolean layout, WebRequest webRequest,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws ReportDataFactoryException {
		ReportDefinition report = repository.findByCode(code);
		if (report == null) {
			model.addAttribute("errorMessage", "report " + code + " not found");
			return "reports/error";
		}
		try {
			showParameter(layout, model, report);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exception", e);
			return "reports/error";
		}
		return "reports/show";
	}

	/**
	 * 增加根据code 进行查看 报表信息
	 * 
	 */
	@RequestMapping(value = "/code/{code}/body")
	public String showContentByCode(@PathVariable String code,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ReportDefinition report = repository.findByCode(code);
		if (report == null) {
			model.addAttribute("errorMessage", "report " + code + " not found");
			return "reports/error";
		}
		openContent(request, response, report, model);
		return null;
	}

	@RequestMapping(value = "/{id}/body")
	public String showContent(@PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			model.addAttribute("errorMessage", "report #" + id + " not found");
			return "reports/error";
		}
		return openContent(request, response, report, model);
	}

	@RequestMapping(value = "/{id}/export/{type}")
	public String export(@PathVariable Long id, HttpServletResponse response,
			RedirectAttributes redirectAttrs, @PathVariable Long type,
			HttpServletRequest request) throws ParseException,
			ResourceException, ReportProcessingException, IOException {

		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			redirectAttrs.addFlashAttribute("errorMessage", "report #" + id
					+ " not found");
			return "redirect:/reports";
		}

		String fileName = report.getCode();
		try {
			fileName = URLEncoder.encode(report.getCode(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		MasterReport mr = reportService.parseReport(report);

		setupReportParameters(mr, request);

		response.setContentType("application/octet-stream");
		ServletOutputStream stream = response.getOutputStream();

		if (type == 1) {
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + ".xls\"");
			ExcelReportUtil.createXLS(mr, stream);
		}
		if (type == 2) {
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + ".pdf\"");
			PdfReportUtil.createPDF(mr, stream);
		}
		if (type == 3) {
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + ".html\"");
			HtmlReportUtil.createStreamHTML(mr, stream);
		}
		stream.flush();
		stream.close();

		return null;
	}

	/**
	 * 按钮显示
	 * 
	 * @param model
	 */
	private void setMenu(Model model) {
		Iterable<Category> categorys = categoryRepository.findAll();
		List<MenuVo> menus = new ArrayList<MenuVo>();
		for (Category category : categorys) {
			MenuVo menuVo = new MenuVo();
			menuVo.setId(category.getId());
			menuVo.setName(category.getName());
			menuVo.setDescription(category.getDescription());
			menuVo.setList(repository.findByCategoryId(category));
			menus.add(menuVo);
		}
		model.addAttribute("menus", menus);
	}

	/**
	 * 显示报表内容
	 * 
	 */
	private String openContent(HttpServletRequest request,
			HttpServletResponse response, ReportDefinition report, Model model) {

		request.setAttribute("_layout", false);

		MasterReport mr = null;
		try {
			mr = reportService.parseReport(report);
		} catch (Exception e1) {
			e1.printStackTrace();
			model.addAttribute("errorMessage", "Error in parsing report.");
			return "reports/error";
		}

		try {
			setupReportParameters(mr, request);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",
					"Error in setup report parameters.");
			return "reports/error";
		}

		try {
			response.setContentType("text/html");
			ServletOutputStream stream = response.getOutputStream();
			HtmlReportUtil.createStreamHTML(mr, stream);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error in building report.");
			return "reports/error";
		}

		return null;
	}

	private void setupReportParameters(MasterReport mr,
			HttpServletRequest request) throws ParseException {

		ParameterContext parameterContext = null;
		try {
			parameterContext = new DefaultParameterContext(mr);
		} catch (ReportProcessingException e) {
			e.printStackTrace();
		}
		ReportParameterDefinition paramDefinition = mr.getParameterDefinition();
		ParameterDefinitionEntry[] paramList = paramDefinition
				.getParameterDefinitions();
		ReportParameterValues parameterValues = mr.getParameterValues();
		for (ParameterDefinitionEntry entry : paramList) {
			String paramName = entry.getName();
			String paramValue = request.getParameter(paramName);
			if (null != paramValue && !"".equals(paramValue)) {
				String format = entry
						.getParameterAttribute(
								"http://reporting.pentaho.org/namespaces/engine/parameter-attributes/core",
								"data-format", parameterContext);
				Object obj = ControllerUtil.createByclz(paramValue,
						entry.getValueType(), format);
				parameterValues.put(paramName, obj);
			}
			if (entry.getValueType().isArray()) {
				String[] paramValues = request.getParameterValues(paramName);
				parameterValues.put(paramName, paramValues);
			}
		}
	}

	private void showParameter(boolean layout, Model model,
			ReportDefinition report) throws ReportDataFactoryException {
		if (!layout) {
			loadReports(model);
		}
		model.addAttribute("report", report);
		MasterReport mr = null;
		try {
			mr = reportService.parseReport(report);
		} catch (Exception e1) {
			model.addAttribute("errorMessage", "Error in parsing report.");
			throw new RuntimeException(e1);
		}
		ReportParameterDefinition paramDefinition = mr.getParameterDefinition();
		ParameterContext parameterContext = null;
		try {
			parameterContext = new DefaultParameterContext(mr);
		} catch (ReportProcessingException e) {
			e.printStackTrace();
		}
		List<FieldParams> paramList = ControllerUtil.conversionParamList(
				paramDefinition.getParameterDefinitions(), parameterContext);
		model.addAttribute("paramList", paramList);
		model.addAttribute("parameterContext", parameterContext);
	}
}
