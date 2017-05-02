package com.elderstudios.controller;

import com.elderstudios.domain.ProductEntry;
import com.elderstudios.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	protected ProductService productService;

	private static final String PRODUCT_FORM = "product";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayProduct( Model model ) {

		model.addAttribute(ENTRIES_KEY, productService.findAll());
		model.addAttribute(FORM_KEY, new ProductEntry());

		return PRODUCT_FORM;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeProduct(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) ProductEntry form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, productService.findAll());
			return PRODUCT_FORM;
		}

		productService.save(form);

		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteEntry (Model model, @PathVariable Long id) {

		productService.delete (id);

		return "redirect:/";
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
	public String editEntry (Model model, @PathVariable Long id) {
		model.addAttribute (FORM_KEY, productService.findOne (id));
		return PRODUCT_FORM;
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
	public String editSaveProduct (Model model,
									 @PathVariable Long id,
									 @Valid @ModelAttribute(FORM_KEY) ProductEntry form,
									 BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, productService.findAll());
			return PRODUCT_FORM;
		}

		ProductEntry existing = productService.findOne (id);

		existing.setName (form.getName());
		existing.setPrice (form.getPrice());
		existing.setDescription (form.getDescription());
		existing.setComment(form.getComment());

		productService.save (existing);

		return "redirect:/";
	}


}