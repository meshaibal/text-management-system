package com.edia.text.management.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edia.text.management.persistence.model.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long>{

	List<Text> findAllByOrderByCreatedDateDesc();
	
	Text findByTextTitle(String textTitle);
}
