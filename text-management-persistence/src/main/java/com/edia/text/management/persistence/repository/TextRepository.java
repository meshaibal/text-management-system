package com.edia.text.management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edia.text.management.persistence.model.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long>{

}
