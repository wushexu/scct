package foross.scctbi.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.JndiConnectionProvider;
import org.pentaho.reporting.libraries.base.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foross.scctbi.data.model.Category;
import foross.scctbi.data.model.ReportDefinition;
import foross.scctbi.data.repository.CategoryRepository;
import foross.scctbi.data.repository.ReportRepository;
import foross.scctbi.service.ReportService;
import foross.scctbi.web.common.AjaxUtils;

@Controller
@RequestMapping("/reportDefinitions")
public class ReportDefinitionsController {

	@Autowired
	ReportRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	ReportService reportService;

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		boolean isAjaxRequest = AjaxUtils.isAjaxRequest(request);
		if (isAjaxRequest) {
			model.addAttribute("ajaxRequest", isAjaxRequest);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		list(model);
		return "/reportDefinitions/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		setCategorys(model);
		Iterable<ReportDefinition> reports = repository.findAll();
		model.addAttribute("reports", reports);
	}

	@RequestMapping(value = "/category/{cid}", method = RequestMethod.GET)
	public String listByCategory(@PathVariable Long cid, Model model) {
		setCategorys(model);
		Iterable<ReportDefinition> reports = repository
				.findByCategoryId(new Category(cid));
		model.addAttribute("reports", reports);
		model.addAttribute("cid", cid);
		return "/reportDefinitions/list";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String editNew(Long cid, Model model) {
		ReportDefinition report = new ReportDefinition();
		report.setCategoryId(new Category(cid));
		model.addAttribute("report", report);
		setCategorys(model);
		return "/reportDefinitions/edit";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("report") ReportDefinition report,
			BindingResult result, @RequestParam MultipartFile reportFile,
			Model model, RedirectAttributes redirectAttrs, Long cid) {
		setCategorys(model);
		if (result.hasErrors()) {
			return "/reportDefinitions/edit";
		}

		if (report.getId() != null) {
			result.addError(new ObjectError("report", "id should be null"));
			return "/reportDefinitions/edit";
		}

		if (cid == null || cid == 0) {
			result.addError(new ObjectError("report", "please select a category"));
			return "/reportDefinitions/edit";
		}

		ReportDefinition existed = repository.findByCode(report.getCode());
		if (existed != null) {
			result.addError(new ObjectError("report", "report with code '"
					+ report.getCode() + "' was existed"));
			return "/reportDefinitions/edit";
		}

		try {
			byte[] content = reportFile.getBytes();
			int contentSize = content.length;
			if (contentSize == 0) {
				result.addError(new ObjectError("content",
						"please select report file(.prpt)"));
				return "/reportDefinitions/edit";
			}

			report.setContent(content);
			report.setContentSize(contentSize);
			report.setCategoryId(new Category(cid));
		} catch (IOException e1) {
			e1.printStackTrace();
			result.addError(new ObjectError("report.content",
					"error in reading report file(.prpt)"));
			return "/reportDefinitions/edit";
		}

		try {
			reportService.validateReportDefinition(report);
		} catch (Exception e1) {
			e1.printStackTrace();
			result.addError(new ObjectError("report.content",
					"Unable to parse report, make sure it is a correct .prpt file"));
			return "/reportDefinitions/edit";
		}

		try {
			report.setLastModifiedDate(new DateTime());
			repository.save(report);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",
					"saving Report '" + report.getCode() + "' failed.");
			return "/reportDefinitions/edit";
		}

		String message = "Report '" + report.getCode() + "' saved successfully";
		// if (AjaxUtils.isAjaxRequest(request)) {
		// model.addAttribute("message", message);
		// return "/reportDefinitions/edit";
		// }

		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/reportDefinitions/category/"+cid;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		ReportDefinition report = repository.findOne(id);
		model.addAttribute("report", report);
		setCategorys(model);
		return "/reportDefinitions/edit";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String update(@PathVariable Long id, Model model,
			@Valid @ModelAttribute("report") ReportDefinition reportForm,
			BindingResult result, @RequestParam MultipartFile reportFile,
			Long cid) {
		setCategorys(model);
		if (result.hasErrors()) {
			return "/reportDefinitions/edit";
		}

		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			result.addError(new ObjectError("report", "report #" + id
					+ " not found"));
			return "/reportDefinitions/edit";
		}

		if (cid == null || cid == 0) {
			result.addError(new ObjectError("report", "please select a category"));
			return "/reportDefinitions/edit";
		}
		try {
			byte[] content = reportFile.getBytes();
			int contentSize = content.length;
			if (contentSize > 0) {
				report.setContent(content);
				report.setContentSize(contentSize);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			result.addError(new ObjectError("report.content",
					"error in reading report file(.prpt)"));
			return "/reportDefinitions/edit";
		}

		try {
			reportService.validateReportDefinition(report);
		} catch (Exception e1) {
			e1.printStackTrace();
			result.addError(new ObjectError("report.content",
					"Unable to parse report, make sure it is a correct .prpt file"));
			return "/reportDefinitions/edit";
		}
		report.setCategoryId(new Category(cid));
		report.setCode(reportForm.getCode());
		report.setName(reportForm.getName());
		report.setDescription(reportForm.getDescription());
		report.setMemo(reportForm.getMemo());
		report.setLastModifiedDate(new DateTime());
		repository.save(report);

		return "redirect:/reportDefinitions/category/"+cid;
	}

	@RequestMapping(value = "/{id}/download")
	public String download(@PathVariable Long id, HttpServletResponse response,
			RedirectAttributes redirectAttrs) {

		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			redirectAttrs.addFlashAttribute("errorMessage", "report #" + id
					+ " not found");
			return "redirect:/reportDefinitions";
		}

		byte[] fileContent = report.getContent();
		if (fileContent == null) {
			fileContent = new byte[0];
		}

		response.setContentType("application/octet-stream");
		response.setContentLength(fileContent.length);

		String fileName = report.getCode();
		try {
			fileName = URLEncoder.encode(report.getCode(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + ".prpt\"");

		try {
			org.apache.commons.io.IOUtils.write(fileContent,
					response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable Long id, Model model,
			RedirectAttributes redirectAttrs) {

		ReportDefinition report = repository.findOne(id);
		if (report == null) {
			redirectAttrs.addFlashAttribute("errorMessage", "report #" + id
					+ " not found");
			return "redirect:/reportDefinitions";
		}

		try {
			repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "delete Report '"
					+ report.getCode() + "' failed.");
			return "redirect:/reportDefinitions";
		}

		String message = "Report '" + report.getCode() + "' deleted";

		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/reportDefinitions";
	}

	@ExceptionHandler
	public @ResponseBody String handle(Exception e) {
		e.printStackTrace();
		return "exception caught!";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void show(@PathVariable Long id, Model model) {

		ReportDefinition report = repository.findOne(id);
		model.addAttribute("report", report);
		// TODO:
	}

	private void setCategorys(Model model) {
		Iterable<Category> categorys = categoryRepository.findAll();
		model.addAttribute("categorys", categorys);
	}

	@RequestMapping(value = "/jndiTest", method = RequestMethod.GET)
	public void jndiTest(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final String jndi = req.getParameter("ds");
		if (jndi == null || StringUtils.isEmpty(jndi)) {
			resp.setContentType("text/plain");
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			final PrintWriter writer = resp.getWriter();
			writer.print("Error: Parameter 'ds' is missing.");
			writer.flush();
			return;
		}

		final String user = req.getParameter("user");
		final String pass = req.getParameter("password");

		JndiConnectionProvider con = new JndiConnectionProvider(jndi, user,
				pass);
		try {
			final Connection connection = con.createConnection(null, null);
			connection.close();
			resp.setContentType("text/plain");
			resp.setStatus(HttpServletResponse.SC_OK);
			final PrintWriter writer = resp.getWriter();
			writer.print("Success.");
			writer.flush();
		} catch (SQLException e) {
			resp.setContentType("text/plain");
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			final PrintWriter writer = resp.getWriter();
			writer.print("Error: Failed to query datasource: " + e.getMessage());
			e.printStackTrace(writer);
			writer.flush();
		}
	}
}
