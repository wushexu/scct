package foross.scctbi.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foross.scctbi.data.model.ReportDefinition;
import foross.scctbi.data.vo.MenuVo;

@Controller
@RequestMapping("/dimensional")
public class DimensionalReportsController {

	@Value("${saiku.repository.path:}")
	private String saikuRepository;

	@Value("${saiku.reports:}")
	private String saikuReportNames;

	@Value("${saiku.url}")
	private String saikuUrl;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {

		loadReports(model);
		return "dimensional/list";
	}

	private void loadReports(String base, File reportsDir,
			List<ReportDefinition> reports) {

		File[] files = reportsDir.listFiles();
		String postfix = ".saiku";
		for (File file : files) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				String path = ("".equals(base)) ? fileName : base + "/"
						+ fileName;
				try {
					loadReports(path, file, reports);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				continue;
			}
			if (!fileName.endsWith(postfix)) {
				continue;
			}

			ReportDefinition rd = new ReportDefinition();
			String code = ("".equals(base)) ? fileName : base + "/" + fileName;
			rd.setCode(code);
			String name = fileName.substring(0,
					fileName.length() - postfix.length());
			String pathAndName = ("".equals(base)) ? name : base + "/" + name;
			rd.setName(pathAndName);
			reports.add(rd);
		}
	}

	private void loadReports(Model model) {
		List<ReportDefinition> reports = new ArrayList<ReportDefinition>();

		if (saikuRepository == null || saikuRepository.equals("")) {
			saikuRepository = getSaikuRepository();
		} else if (!new File(saikuRepository).exists()) {
			saikuRepository = getSaikuRepository();
		}

		boolean evluateReportNames = false;
		if (saikuRepository == null || saikuRepository.equals("")) {
			evluateReportNames = true;
		} else {
			try {
				File reportsDir = new File(saikuRepository);
				loadReports("", reportsDir, reports);
			} catch (Exception e) {
				e.printStackTrace();
				evluateReportNames = true;
			}
		}

		if (evluateReportNames && saikuReportNames != null
				&& !saikuReportNames.equals("")) {
			reports = new ArrayList<ReportDefinition>();

			String[] reportNames = saikuReportNames.split(",");
			for (String reportName : reportNames) {
				ReportDefinition rd = new ReportDefinition();
				rd.setCode(reportName + ".sauku");
				rd.setName(reportName);
				reports.add(rd);
			}
		}

		model.addAttribute("reports", reports);
		model.addAttribute("saikuUrl", saikuUrl);

		setMenu(model, reports);
	}

	private void setMenu(Model model, List<ReportDefinition> reports) {
		String rootCat = "COMMON";
		long catId = 1;
		Map<String, MenuVo> categorys = new HashMap<String, MenuVo>();

		List<MenuVo> menus = new ArrayList<MenuVo>();

		MenuVo menu = new MenuVo();
		menu.setId(catId++);
		menu.setName(rootCat);
		menu.setList(new ArrayList<ReportDefinition>());
		categorys.put(rootCat, menu);
		menus.add(menu);

		for (ReportDefinition report : reports) {
			String reportName = report.getName();
			int slash = reportName.indexOf('/');
			String catName = null;
			if (slash >= 0) {
				catName = reportName.substring(0, slash);
				reportName = reportName.substring(slash + 1);
				report.setName(reportName);
			} else {
				catName = rootCat;
			}
			menu = categorys.get(catName);
			if (menu == null) {
				menu = new MenuVo();
				menu.setId(catId++);
				menu.setName(catName);
				menu.setList(new ArrayList<ReportDefinition>());
				categorys.put(catName, menu);
				menus.add(menu);
			}
			menu.getList().add(report);
		}
		model.addAttribute("menus", menus);
	}

	@RequestMapping(value = "analysis", method = RequestMethod.GET)
	public String analysis(Model model) {
		loadReports(model);
		return "dimensional/saiku";
	}

	/**
	 * 获取saiku项目的文件路径
	 * 
	 * @return
	 */
	private String getSaikuRepository() {
		String resource = DimensionalReportsController.class.getResource("/")
				.getFile();
		File resourceFile = new File(resource);
		if (resourceFile.exists()) {
			String pepositoryPath = resourceFile.getParentFile()
					.getParentFile().getParent();
			pepositoryPath = pepositoryPath
					+ "/saiku/WEB-INF/classes/saiku-repository";
			if (new File(pepositoryPath).exists()) {
				return pepositoryPath;
			}
		}
		return null;
	}
}
