package foross.scctbi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import foross.scctbi.data.repository.CategoryRepository;

@Component
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
}
