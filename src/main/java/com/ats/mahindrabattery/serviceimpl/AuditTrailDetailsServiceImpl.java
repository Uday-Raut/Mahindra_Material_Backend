package com.ats.mahindrabattery.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.service.AuditTrailDetailsService;

@Service
public class AuditTrailDetailsServiceImpl implements AuditTrailDetailsService {

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepositoryInstance;

	public List<AuditTrailDetailsEntity> findAuditTrailDetailsByCDateTime() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			//System.out.println("current datetime= " + currentDateTime);
			List<AuditTrailDetailsEntity> findAuditTrailDetailsBetweenDates = auditTrailDetailsRepositoryInstance
					.findAuditTrailDetailsBetweenDates(currentDateTime + " " + "00:00:00",
							currentDateTime + " " + "23:59:59");
			if (findAuditTrailDetailsBetweenDates.size() > 0) {
				return findAuditTrailDetailsBetweenDates;
			} else {
				throw new NullPointerException();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<AuditTrailDetailsEntity> findByAllFiltersDetails(String startDate, String endDate, String username) {

		List<String> filterList = new ArrayList<String>();

		List<AuditTrailDetailsEntity> list = new ArrayList<AuditTrailDetailsEntity>();

		if (!username.equals("NA")) {
			filterList.add("username");
		}

		Predicate<AuditTrailDetailsEntity> predUsername = data -> (data.getUsername().equals(username));
		if (!(startDate.equals("NA")) && !(endDate.equals("NA"))) {
			// Removing "T" from datatime format
			String startDateTime = startDate.toString().replace("T", " ");
			String endDateTime = endDate.toString().replace("T", " ");

			list = auditTrailDetailsRepositoryInstance.findByDatetimeCBetween(startDateTime, endDateTime);
		} else {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			String date = ft.format(dNow);
			list = auditTrailDetailsRepositoryInstance.findByDatetimeCBetween(date + " " + "00:00:00",
					date + " " + "23:59:59");
		}

		if (filterList.size() != 0) {

			for (int i = 0; i < filterList.size(); i++) {

				if (filterList.get(i).equals("username")) {
					list = list.stream().filter(predUsername).collect((Collectors.toList()));
				}
			}
		}

		if (filterList.size() == 0 && list.size() == 0) {
			list = null;
		}
		return list;

	}

	public AuditTrailDetailsEntity addPositionLockReasonInUserAuditTrail(
			AuditTrailDetailsEntity auditTrailDetailsEntity, String operatorsAction, int positionId, String reason,
			String field, int beforeValue, int afterValue, String userName) {
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			auditTrailDetailsEntity.setDatetimeC(date);
			AuditTrailDetailsEntity userAuditTrailReportDetailsEntity = new AuditTrailDetailsEntity();
			userAuditTrailReportDetailsEntity.setOperatorActions(operatorsAction);
			userAuditTrailReportDetailsEntity.setReason(reason);
			userAuditTrailReportDetailsEntity.setField(field);
			userAuditTrailReportDetailsEntity.setBeforeValue(beforeValue);
			userAuditTrailReportDetailsEntity.setAfterValue(afterValue);

			userAuditTrailReportDetailsEntity.setUsername(userName);
			userAuditTrailReportDetailsEntity.setDatetimeC(date);
			return auditTrailDetailsRepositoryInstance.save(userAuditTrailReportDetailsEntity);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return auditTrailDetailsEntity;

	}

	public List<AuditTrailDetailsEntity> findAll() {
		return auditTrailDetailsRepositoryInstance.findAll();
	}

	public List<AuditTrailDetailsEntity> findByDatetimeCOrderByAuditIdDesc() {
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String currentDateTime = dateFormat.format(date);
		// System.out.println("current datetime= " + currentDateTime);
		List<AuditTrailDetailsEntity> findBycDateTimeOrderByAuditIdDesc = auditTrailDetailsRepositoryInstance
				.findByDatetimeCOrderByAuditIdDesc(currentDateTime);
		// System.out.println("audit trail list ::" +
		// findBycDateTimeOrderByAuditIdDesc);
		return findBycDateTimeOrderByAuditIdDesc;
	}

}
