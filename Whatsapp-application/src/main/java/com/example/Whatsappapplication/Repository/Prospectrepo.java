package com.example.Whatsappapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Whatsappapplication.Entity.prospectdto;

@Repository
public interface Prospectrepo extends JpaRepository<prospectdto , Integer>{

	List<prospectdto> findByMobileno(String mobileno);

	
}
