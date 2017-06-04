package com.pelican.text.management.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelican.text.management.persistence.model.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long>{

	List<Text> findAllByOrderByTextIdDesc();
	
	Text findByTextTitle(String textTitle);
	
	Text findByTextId(Long textId);
}
