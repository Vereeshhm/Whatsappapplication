package com.example.Whatsappapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Whatsappapplication.Entity.insurance2dto;


@Repository
public interface insurancerepo1 extends JpaRepository<insurance2dto, Integer>{

	@Query("SELECT s FROM insurance2dto s WHERE s.mobile_no = :mobileNo")
    List<insurance2dto> findByMobile_no(String mobileNo);
}
