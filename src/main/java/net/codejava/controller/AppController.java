package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	/*
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		SendMessage sendMessage = new SendMessage();
		model.addAttribute("product", sendMessage);
		
		return "new_product";
	}
	*/
	/*
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMessage(@ModelAttribute("sendMessage") SendMessage sendMessage) {
		
		service.save(sendMessage);
		List<SendMessage> listProducts = service.listAll();
		System.out.println(listProducts.toString());
		try {
			sendMail.sendEmail("shubham.s.kamboj@gmail.com", "New Record Found on my profile",listProducts.toString());
		}catch(Exception e) {
			System.out.println("error:  "+e);
		}
		
		return "redirect:/";	
	}
	*/
	
	
	@RequestMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> createEmployee(@RequestBody SendMessage sendMessage) 
    {
		service.save(sendMessage);
		List<SendMessage> listProducts = service.listAll();
		
		String dataResult= "";
		
		
		for(SendMessage sm : listProducts) {
			
			String s =	"<tr style='border: 1px solid black;border-collapse: collapse;'>"
					+ "<td style='color:#A52A2A;'>"+sm.getId()+"</td>"
					+ " <td style='color:#C0C0C0;'> "+sm.getName()+"</td>"
					+ "<td style='color:#00FF00;'>"+sm.getEmail()+"</td>"
					+ "<td style='color:#737CA1;'>"+sm.getSubject()+"</td>"
					+ "<td style='color:#C24641;'>"+sm.getMessage()+"</td></tr>";
			
			
			dataResult=dataResult+s;
			
		}
		
		String table="<table style='width:100%;border: 1px solid black;border-collapse: collapse;background-color:#0C090A'>"
				+ "<tr style='border: 1px solid black;border-collapse: collapse;'>"
				+ "<th style='color:blue;'>Id</th>"
				+ "<th style='color:blue;'>Name</th>"
				+ "<th style='color:blue;'>Email</th> "
				+ "<th style='color:blue;'>Subject</th>"
				+ "<th style='color:blue;'>Message</th></tr>"
				+ dataResult+"</table>";
		
		//System.out.println(table);
		try {
			sendMail.sendEmailWithAttachment("shubham.s.kamboj@gmail.com", "New Record Found on my profile",table);
		}catch(Exception e) {
			System.out.println("error:  "+e);
		}
		
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
	
	/*
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		SendMessage sendMessage = service.get(id);
		mav.addObject("product", sendMessage);
		
		return mav;
	}
	*/
	/*
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
	*/
}
