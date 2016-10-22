package foross.scctbi.web.controller;

import java.util.Date;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foross.scctbi.data.model.Category;
import foross.scctbi.data.repository.CategoryRepository;
import foross.scctbi.web.common.AjaxUtils;

@Controller
@RequestMapping(value="/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
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
		return "/category/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		Iterable<Category> categorys = categoryRepository.findAll();
		model.addAttribute("categorys", categorys);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String editNew(Model model) {

		Category category = new Category();
		model.addAttribute("category", category);
		return "/category/edit";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result,Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "/category/edit";
		}

		if (category.getId() != null) {
			result.addError(new ObjectError("category", "id should be null"));
			return "/category/edit";
		}

		try {
			category.setCreateDate(new Date());
			categoryRepository.save(category);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",
					"saving Category '" + category.getName() + "' failed.");
			return "/category/edit";
		}

		String message = "Category '" + category.getName() + "' saved successfully";
		// if (AjaxUtils.isAjaxRequest(request)) {
		// model.addAttribute("message", message);
		// return "/reportDefinitions/edit";
		// }

		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/category";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {

		Category category = categoryRepository.findOne(id);
		model.addAttribute("category", category);
		return "/category/edit";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String update(@PathVariable Long id,
			@Valid @ModelAttribute("category") Category categoryForm,
			BindingResult result) {

		if (result.hasErrors()) {
			return "/category/edit";
		}

		Category category = categoryRepository.findOne(id);
		if (category == null) {
			result.addError(new ObjectError("category", "category #" + id
					+ " not found"));
			return "/category/edit";
		}

		category.setName(categoryForm.getName());
		category.setDescription(categoryForm.getDescription());
		category.setCreateDate(new Date());
		categoryRepository.save(category);

		return "redirect:/category";
	}


	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable Long id, Model model,
			RedirectAttributes redirectAttrs) {

		Category category = categoryRepository.findOne(id);
		if (category == null) {
			redirectAttrs.addFlashAttribute("errorMessage", "category #" + id
					+ " not found");
			return "redirect:/category";
		}

		try {
			categoryRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "delete Category '"
					+ category.getName() + "' failed.");
			return "redirect:/category";
		}

		String message = "Category '" + category.getName() + "' deleted";

		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/category";
	}

	@ExceptionHandler
	public @ResponseBody String handle(Exception e) {
		e.printStackTrace();
		return "exception caught!";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void show(@PathVariable Long id, Model model) {

		Category category = categoryRepository.findOne(id);
		model.addAttribute("category", category);
		// TODO:
	}
}
