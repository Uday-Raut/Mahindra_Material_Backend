package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.entity.MasterStackerDetailsEntity;
import com.ats.mahindrabattery.service.MasterStackerDetailsService;
import com.ats.mahindrabattery.serviceimpl.MasterStackerDetailsServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/masterStackerDetails")
public class MasterStackerDetailsController {

    @Autowired
    private MasterStackerDetailsService masterStackerDetailsService;

    @GetMapping("/fetchByStackerDetailsIsDeleted")
    public List<MasterStackerDetailsEntity> fetchAllStackerDetails() {
        return masterStackerDetailsService.fetchAllStackerDetailsbyStackerIsDeleted(0);
    }
    
    
   
}
