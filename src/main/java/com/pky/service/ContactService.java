package com.pky.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pky.entity.ContactEntity;
import com.pky.model.Contact;

public interface ContactService {
	public boolean saveContact(Contact contact);
	public Page<ContactEntity> getAllContact(Integer pageSize,Integer pageNo);
	public Contact getContactById(Integer id);
    public boolean deleteContact(Integer id);
    
    public String findByEmail(String email);

}

