package com.pky.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pky.entity.ContactEntity;

@Repository
public interface ContactDtlsRepository extends JpaRepository<ContactEntity,Integer>{
	@Transactional
	@Modifying
	@Query("update ContactEntity set activeSw=:sw where contactId=:cid")
	public void update(String sw, Integer cid);
	
	@Query("from ContactEntity where contactEmail=:email and userPwd=:pwd")
	public ContactEntity findByContactEmailAndUserPwd(String pwd,String email);
	
	public ContactEntity findByContactEmail(String contactEmail);   // to find contact by using non primary key column
	

}
