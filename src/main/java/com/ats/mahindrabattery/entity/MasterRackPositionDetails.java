package com.ats.mahindrabattery.entity;

import java.util.List;

public class MasterRackPositionDetails {

	int rackId;
	List<MasterPositionDetailsEntity> position;
	
	public int getRackId() {
		return rackId;
	}
	public void setRackId(int rackId) {
		this.rackId = rackId;
	}
	public List<MasterPositionDetailsEntity> getPosition() {
		return position;
	}
	public void setPosition(List<MasterPositionDetailsEntity> position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "MasterRackPositionDetails [rackId=" + rackId + ", position=" + position + "]";
	}
	
	
}
