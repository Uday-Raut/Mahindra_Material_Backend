package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;

public interface AuditTrailDetailsService {
	public List<AuditTrailDetailsEntity> findAuditTrailDetailsByCDateTime();

	public List<AuditTrailDetailsEntity> findByAllFiltersDetails(String startDate, String endDate, String username);

	public AuditTrailDetailsEntity addPositionLockReasonInUserAuditTrail(
			AuditTrailDetailsEntity auditTrailDetailsEntity, String operatorsAction, int positionId, String reason,
			String field, int beforeValue, int afterValue, String userName);

	public List<AuditTrailDetailsEntity> findAll();

	public List<AuditTrailDetailsEntity> findByDatetimeCOrderByAuditIdDesc();
}
