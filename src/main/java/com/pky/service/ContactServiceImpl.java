package com.pky.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pky.entity.ContactEntity;
import com.pky.model.Contact;
import com.pky.repository.ContactDtlsRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDtlsRepository repo;

	@Override
	public boolean saveContact(Contact contact) {
		
		ContactEntity entity=new ContactEntity();
		BeanUtils.copyProperties(contact,entity);
		ContactEntity savedEntity=repo.save(entity);
		
		return savedEntity.getContactId()!=null ;
		
	}
	//here we apply servers side pagination	 
	@Override
	public Page<ContactEntity> getAllContact(Integer pageSize,Integer pageNo) {
	     PageRequest page=PageRequest.of(pageNo,pageSize );
		Page<ContactEntity> findAll=repo.findAll(page);
		return findAll;
	}

	@Override
	public Contact getContactById(Integer id) {
	Optional<ContactEntity> findById=repo.findById(id);
	if(findById.isPresent()) {
		ContactEntity entity=findById.get();
		
		Contact contact=new Contact();
		BeanUtils.copyProperties(entity, contact);
		return contact;
	}
		return null;
	}
	
	@Override
	public boolean deleteContact(Integer id) {
		repo.deleteById(id);
	/*	if(repo.findById(id)==null) {
			return true;
		} */
		return true;
	}

	@Override
	public String findByEmail(String email) {
		  ContactEntity entity= repo.findByContactEmail(email);
		  if(entity!=null) {
			  return "Duplicate";
		  }
		return "Unique";
	}
	
	
	

}
