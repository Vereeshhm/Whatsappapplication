package com.example.Whatsappapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.Whatsappapplication.Entity.Saswatdto;


@Repository
public interface WhatsAppRepository extends JpaRepository<Saswatdto, Integer>{

}
