package foross.scctbi.service;

import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foross.scctbi.data.model.ReportDefinition;
import foross.scctbi.data.repository.ReportRepository;

@Component
public class ReportService {

	@Autowired
	ReportRepository repository;

	public void validateReportDefinition(ReportDefinition report) {

		try {
			parseReport(report);
		} catch (ResourceException e) {
			throw new RuntimeException(e);
		}
	}

	public MasterReport parseReport(ReportDefinition report)
			throws ResourceException {

		byte[] reportContent = report.getContent();
		ResourceManager resourceManager = new ResourceManager();
		Resource directly = resourceManager.createDirectly(reportContent,
				MasterReport.class);
		return (MasterReport) directly.getResource();
	}

}
