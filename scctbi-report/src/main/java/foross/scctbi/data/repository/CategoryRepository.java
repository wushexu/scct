package foross.scctbi.data.repository;

import org.springframework.data.repository.CrudRepository;

import foross.scctbi.data.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
}
