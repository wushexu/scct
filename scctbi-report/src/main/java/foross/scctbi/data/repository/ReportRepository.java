package foross.scctbi.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import foross.scctbi.data.model.Category;
import foross.scctbi.data.model.ReportDefinition;

public interface ReportRepository extends CrudRepository<ReportDefinition, Long> {

	ReportDefinition findByCode(String reportCode);
	
	List<ReportDefinition> findByCategoryId(Category category);
}
