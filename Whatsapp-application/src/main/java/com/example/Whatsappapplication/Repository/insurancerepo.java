package com.example.Whatsappapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Entity.prospectdto;

@Repository
public interface insurancerepo extends JpaRepository<insurancedto, Integer>{

	@Query("SELECT s FROM insurancedto s WHERE s.mobile_no = :mobileNo")
    List<insurancedto> findByMobile_no(String mobileNo);


}
