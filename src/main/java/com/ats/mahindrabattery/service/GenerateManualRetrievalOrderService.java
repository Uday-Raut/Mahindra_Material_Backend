package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;

public interface GenerateManualRetrievalOrderService {

	public List<GenerateManualRetrievalOrderEntity> getAllMannualRetrivalDetails();
	
	public List<GenerateManualRetrievalOrderEntity> findAllMannualDispatchOrdersByDate(String startDate,
			String endDate);
	
	public List<GenerateManualRetrievalOrderEntity> findByMannualDispatchNumber(String dispatchOrderNumber);
	
	
	//public Page<GenerateManualRetrievalOrderEntity> getAllMannualRetrivalDetails(Pageable pageable);

	
	
}
