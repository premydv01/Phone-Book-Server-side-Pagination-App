package com.pky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.model.Contact;
import com.pky.service.ContactService;

@Controller
public class ViewContactsController {
	
	@Autowired
	private ContactService service;

	@RequestMapping(value = "/editContact")
	public String editContact(@RequestParam("cid") Integer cid ,Model model) {
		Contact c=service.getContactById(cid);
		model.addAttribute("contact",c);
		return "contactInfo";
	}
	
	@RequestMapping(value = "/deleteContact")
	public String deleteContact(@RequestParam("cid") Integer contactId) {
		boolean isDelete=service.deleteContact(contactId);
		if(isDelete) {
			return "redirect:/viewContacts";
		}
		return "null";
	}
}
