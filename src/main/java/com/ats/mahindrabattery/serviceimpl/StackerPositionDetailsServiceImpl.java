package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.StackerPositionDetailsEntity;
import com.ats.mahindrabattery.repository.StackerPositonDetailsRepository;
import com.ats.mahindrabattery.service.StackerPositionDetailsService;


@Service
public class StackerPositionDetailsServiceImpl implements StackerPositionDetailsService {

	@Autowired
	private StackerPositonDetailsRepository stackerPositonDetailsRepositoryInstnace;

	public List<StackerPositionDetailsEntity> findAllStckerPositionDetails() {
		try {
			return stackerPositonDetailsRepositoryInstnace.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}
