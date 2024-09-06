package com.ats.mahindrabattery.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ats.mahindrabattery.entity.AccessMatrixEntity;
import com.ats.mahindrabattery.repository.AccessMatrixRepository;
import com.ats.mahindrabattery.service.AccessMatrixService;
import com.ats.mahindrabattery.service.AuditTrailDetailsService;

@Service
public class AccessMatrixServiceImpl implements AccessMatrixService {

	@Autowired
	private AccessMatrixRepository accessMatrixRepository;

	public List<AccessMatrixEntity> getAllAccessMatrixDetails() {
		return accessMatrixRepository.findAll();
	}

	@Override
	public AccessMatrixEntity updateAccessMatrix(AccessMatrixEntity accessMatrix) {
		accessMatrixRepository.update(accessMatrix.getAdmin(), accessMatrix.getOperator(), accessMatrix.getSupervisor(),
				accessMatrix.getAccessmMatrixId());

		System.out.println(accessMatrix);
		return accessMatrix;
	}

}
