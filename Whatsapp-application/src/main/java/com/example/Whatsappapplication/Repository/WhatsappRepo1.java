package com.example.Whatsappapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.dto.Reminderdata;

@Component
@Repository
public interface WhatsappRepo1 extends JpaRepository<Reminderdata, Integer>{

}
