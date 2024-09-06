package com.ats.mahindrabattery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentStockDetails {
	
	private int bevCurrentStockCount;
	private int s230CurrentStockCount;
	private int emptyPalletCount;
	

}
