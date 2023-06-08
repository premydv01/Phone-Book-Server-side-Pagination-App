package com.pky.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pky.entity.ContactEntity;
import com.pky.model.Contact;
import com.pky.service.ContactService;

@Controller
public class ContactInfoController {
	
	@Autowired
	private ContactService service;
	
	@GetMapping(value = {"/","/addContact"})
	public String  loadForm(Model model) {
		Contact c=new Contact();
		model.addAttribute("contact",c);
		return "contactInfo";
	}
	
	
	@PostMapping(value = "/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact")Contact c,RedirectAttributes attributes) {
		 boolean isSaved=service.saveContact(c);
		 
		 if(isSaved) {
			 attributes.addFlashAttribute("succMsg","Contact is Saved");
		 }else {
			 attributes.addFlashAttribute("errMsg","Failed to Save Contact");
		 }
		return "redirect:/doublePosting";
	}
//THIS METHOD IS USING FOR DOUBLE POSTING PROBLEM BY IMPLEMENTING PRG DISIGN PATTERN	
	
	@GetMapping(value = "/doublePosting")
	public String postRedirectToGet(Model model) {
		
		model.addAttribute("contact",new Contact());
		return "contactInfo";
		}
//here we apply servers side pagination	 
	@GetMapping(value = "/viewContacts")
	public String handleViewContactsLinks(HttpServletRequest req,Model model) {
		Integer currpageNo=1;
		Integer pageSize=3;
		String pno=req.getParameter("pno");
		
		if(pno!=null && !pno.equals("")) {
			currpageNo=Integer.parseInt(pno);
		}
	Page<ContactEntity> pageList=service.getAllContact(pageSize,currpageNo-1);
	 List<ContactEntity>  entities=pageList.getContent();
	 int totalPages=pageList.getTotalPages();
	//java 8 stream approch		
			List<Contact> contactList=entities.stream().map(entity->{
				Contact contact=new Contact();
				BeanUtils.copyProperties(entity, contact);
				return contact;
			}).collect(Collectors.toList());
			
		model.addAttribute("contacts",contactList);
		model.addAttribute("tp", totalPages);
		model.addAttribute("cpn", currpageNo);
		return "viewContacts";
	}
	
	@GetMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(HttpServletRequest req) {
	    String email=req.getParameter("email");
		System.out.println(email);
		String emailStatus=service.findByEmail(email);
		System.out.println(emailStatus);
		return emailStatus;
	}
}
