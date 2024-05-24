package com.example.Whatsappapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.example.Whatsappapplication.Entity.Saswatdto;



@Repository

public interface Sasrepository extends JpaRepository<Saswatdto, Integer> {


	@Query("SELECT s FROM Saswatdto s WHERE s.mobile_no = :mobileNo")
    List<Saswatdto> findByMobile_no(String mobileNo);





	
}
