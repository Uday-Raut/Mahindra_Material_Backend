package com.ats.mahindrabattery.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;
import com.ats.mahindrabattery.repository.EquipmentAlarmHistoryRepository;
import com.ats.mahindrabattery.service.EquipmentAlarmHistoryService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



@Service
public class EquipmentAlarmHistoryServiceImpl implements EquipmentAlarmHistoryService {
	@Autowired
	private EquipmentAlarmHistoryRepository equipmentAlarmHistoryRepositoryInstance;

	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlarmHistory() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmOccurredDatetimeBetween(
					currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlaramStatus() {
		return equipmentAlarmHistoryRepositoryInstance.findAllByEquipmentAlarmStatus(1);
	}

	public List<EquipmentAlarmHistoryEntity> findByWmsEquipmentAlarmHistoryId(int equipmentAlarmHistoryId) {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByWmsEquipmentAlarmHistoryIdAndEquipmentAlarmResolvedDatetime(equipmentAlarmHistoryId,null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNull() {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findAllByEquipmentAlarmResolvedDatetime(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker(
			int equipmentId) {
		try {
			//System.out.println("findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker ::"+equipmentId);
			return equipmentAlarmHistoryRepositoryInstance.findAllByEquipmentAlarmResolvedDatetimeAndEquipmentId(null,
					equipmentId);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmHistoryId(int alarmId) {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmId(alarmId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmName(String alarmName) {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmName(alarmName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentName(String equipmentName) {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentName(equipmentName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccuredDateTimeBetween(
			String alarmOccuredStartDateTime, String alarmOccuredEndDateTime) {
		String startDateTime = alarmOccuredStartDateTime.toString().replace("T", " ");
		String endDateTime = alarmOccuredEndDateTime.toString().replace("T", " ");
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmOccurredDatetimeBetween(startDateTime,
					endDateTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findByHistoryAlarmResolvedDateBetween(String alarmResolvedDatetimeStart,
			String alarmResolvedDatetimeEnd) {
		String startDateTime = alarmResolvedDatetimeStart.toString().replace("T", " ");
		String endDateTime = alarmResolvedDatetimeEnd.toString().replace("T", " ");
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmResolvedDatetimeBetween(startDateTime,
					endDateTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(
			String equipmentName, String alarmOccuredDatetimeStart, String alarmOccuredDatetimeEnd) {
		String startDateTime = alarmOccuredDatetimeStart.toString().replace("T", " ");
		String endDateTime = alarmOccuredDatetimeEnd.toString().replace("T", " ");
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(
					equipmentName, startDateTime, endDateTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndAlarmResolvedDateBetween(String equipmentName,
			String alarmResolveDatetimeStart, String alarmResolveDatetimeEnd) {
		String startDateTime = alarmResolveDatetimeStart.toString().replace("T", " ");
		String endDateTime = alarmResolveDatetimeEnd.toString().replace("T", " ");
		try {
			return equipmentAlarmHistoryRepositoryInstance.findByEquipmentNameAndEquipmentAlarmResolvedDatetimeBetween(
					equipmentName, startDateTime, endDateTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Optional<EquipmentAlarmHistoryEntity> findHistoryAlarmHistoryDetailsById(int alarmHistoryId) {
		try {
			return equipmentAlarmHistoryRepositoryInstance.findById(alarmHistoryId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<EquipmentAlarmHistoryEntity> findByAllFilters(String alarmOccuredDatetimeStart,
			String alarmOccuredDatetimeEnd, String equipmentName) {
		String startDateTime = alarmOccuredDatetimeStart.toString().replace("T", " ");
		String endDateTime = alarmOccuredDatetimeEnd.toString().replace("T", " ");
		List<String> filterList = new ArrayList<String>();
		List<EquipmentAlarmHistoryEntity> alarmDataByFilterList = new ArrayList<EquipmentAlarmHistoryEntity>();

		if (!equipmentName.equals("NA")) {
			filterList.add("equipmentName");
		}

		Predicate<EquipmentAlarmHistoryEntity> equipmentNamePred = equipmentNameData -> equipmentNameData
				.getEquipmentName().equals(equipmentName);

		if (!startDateTime.equals("NA") && !endDateTime.equals("NA")) {

			alarmDataByFilterList = equipmentAlarmHistoryRepositoryInstance
					.findByEquipmentAlarmOccurredDatetimeBetween(startDateTime, endDateTime);

		} else if (!startDateTime.equals("NA") && !endDateTime.equals("NA")) {

			alarmDataByFilterList = equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmOccurredDatetimeBetween(
					startDateTime + " " + "00:00:00", endDateTime + " " + "23:59:59");

		} else {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

			String currentDateTimeFormatted = dateFormat.format(date);

			alarmDataByFilterList = equipmentAlarmHistoryRepositoryInstance.findByEquipmentAlarmOccurredDatetimeBetween(
					currentDateTimeFormatted + " " + "00:00:00", currentDateTimeFormatted + " " + "23:59:59");

		}

		for (int i = 0; i < filterList.size(); i++) {

			if (filterList.get(i).equals("equipmentName")) {
				alarmDataByFilterList = alarmDataByFilterList.stream().filter(equipmentNamePred)
						.collect((Collectors.toList()));

			}
		}

		if (filterList.size() == 0 && alarmDataByFilterList.size() == 0) {
			alarmDataByFilterList = null;
		}

		return alarmDataByFilterList;

	}

	public List<EquipmentAlarmHistoryEntity> findByCDatetimeEquipmentAlarmHistory() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
//		System.out.println("currentDateTime " + currentDateTime);
			List<EquipmentAlarmHistoryEntity> equipmentAlarmHistoryBetweenDates = equipmentAlarmHistoryRepositoryInstance
					.getEquipmentAlarmHistoryBetweenDates(currentDateTime + " " + "00:00:00",
							currentDateTime + " " + "23:59:59");
			// System.out.println(equipmentAlarmHistoryBetweenDates);
			return equipmentAlarmHistoryBetweenDates;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	

	public List<EquipmentAlarmHistoryEntity> findByAllFiltersDetails(String startDate, String endDate,
			String equipmentName) {
		List<String> filterList = new ArrayList<>();
		List<EquipmentAlarmHistoryEntity> list = new ArrayList<>();

		if (!equipmentName.equals("NA")) {
			filterList.add(equipmentName);
			//System.out.println("equipmentName= " + equipmentName);
		}

		Predicate<EquipmentAlarmHistoryEntity> predEquipmentName = data -> (data.getEquipmentName()
				.equals(equipmentName));
//		Predicate<PalletOperationHistoryEntity> predArea = data -> (data.getArea().equals(area));
//		Predicate<PalletOperationHistoryEntity> predFloor = data -> (data.getFloor().equals(floor));

		if (!(startDate.equals("NA")) && !(endDate.equals("NA"))) {
			String startDateTime = startDate.toString().replace("T", " ");
			String endDateTime = endDate.toString().replace("T", " ");
//			System.out.println("startDateTime= " + startDateTime);
//			System.out.println("endDateTime= " + endDateTime);
			equipmentAlarmHistoryRepositoryInstance.getEquipmentAlarmHistoryBetweenDates(startDateTime, endDateTime);
		} else {
			Date dateNow = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
			String date = dateFormat.format(dateNow);
			equipmentAlarmHistoryRepositoryInstance.getEquipmentAlarmHistoryBetweenDates(date + " " + "00:00:00",
					date + " " + "23:59:59");
		}

		if (filterList.size() != 0) {
			//System.out.println("list size=" + filterList.size());
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).equals(equipmentName)) {
					list = list.stream().filter(predEquipmentName).collect(Collectors.toList());
					//System.out.println("equipment name list size= " + list.size());
				}
			}
		}

		if (filterList.size() == 0 || list.size() == 0) {
			String startDateTime = startDate.toString().replace("T", " ");
			String endDateTime = endDate.toString().replace("T", " ");
//			System.out.println("startDateTime= " + startDateTime);
//			System.out.println("endDateTime= " + endDateTime);
			equipmentAlarmHistoryRepositoryInstance.getEquipmentAlarmHistoryBetweenDates(startDateTime, endDateTime);
		}
		return list;

	}
	
	

	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc() {
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String currentDateTime = dateFormat.format(date);
   //	System.out.println("currentDateTime " + currentDateTime);
		List<EquipmentAlarmHistoryEntity> findByequipmentAlarmOccurredDatetimeOrderByequipmentAlarmResolvedDatetimeAsc = equipmentAlarmHistoryRepositoryInstance
				.findByEquipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc(currentDateTime);
		return findByequipmentAlarmOccurredDatetimeOrderByequipmentAlarmResolvedDatetimeAsc;
	}

	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlarmOccuredAndResolvedDateIsNullByEquipmentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone1() {
		List<EquipmentAlarmHistoryEntity> findByAlarmZone = equipmentAlarmHistoryRepositoryInstance.findByAlarmZone("ZONE_1");
		return findByAlarmZone;
	}

	@Override
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone2() {
		List<EquipmentAlarmHistoryEntity> findByAlarmZone = equipmentAlarmHistoryRepositoryInstance.findByAlarmZone("ZONE_2");
		return findByAlarmZone;
	}

	@Override
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone3() {
		List<EquipmentAlarmHistoryEntity> findByAlarmZone = equipmentAlarmHistoryRepositoryInstance.findByAlarmZone("ZONE_3");
		return findByAlarmZone;
	}
}
