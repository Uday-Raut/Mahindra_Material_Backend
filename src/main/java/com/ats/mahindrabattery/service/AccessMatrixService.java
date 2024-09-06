package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.AccessMatrixEntity;

public interface AccessMatrixService {

	List<AccessMatrixEntity> getAllAccessMatrixDetails();

	public AccessMatrixEntity updateAccessMatrix(AccessMatrixEntity accessMatrix);

}
