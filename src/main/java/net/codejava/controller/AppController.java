package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.mailSender.SendMail;
import net.codejava.model.SendMessage;
import net.codejava.services.SendMessageService;

@Controller
public class AppController {

	@Autowired
	private SendMessageService service; 
	
	@Autowired
	private SendMail sendMail;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
//		List<SendMessage> listProducts = service.listAll();
//		model.addAttribute("listProducts", listProducts);
		System.out.println("inside main controller");
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		SendMessage sendMessage = new SendMessage();
		model.addAttribute("product", sendMessage);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMessage(@ModelAttribute("sendMessage") SendMessage sendMessage) {
		
		service.save(sendMessage);
		List<SendMessage> listProducts = service.listAll();
		sendMail.sendEmail("shubham.s.kamboj@gmail.com", "New Record Found on my profile",listProducts.toString());
		return "index";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		SendMessage sendMessage = service.get(id);
		mav.addObject("product", sendMessage);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
