package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.entity.MasterStackerDetailsEntity;
import com.ats.mahindrabattery.repository.MasterStackerDetailsRepository;
import com.ats.mahindrabattery.service.MasterEquipmentDetailsService;
import com.ats.mahindrabattery.service.MasterStackerDetailsService;
import com.ats.mahindrabattery.serviceimpl.MasterStackerDetailsServiceImpl;

@Service
public class MasterStackerDetailsServiceImpl implements MasterStackerDetailsService{

    @Autowired
    private MasterStackerDetailsRepository masterStackerDetailsRepository;

    
    public List<MasterStackerDetailsEntity> fetchAllStackerDetailsbyStackerIsDeleted(int stackerIsDeleted) {
        return masterStackerDetailsRepository.findByStackerIsDeleted(0);
    }





	


	
}
