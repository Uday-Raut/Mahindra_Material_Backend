package com.ats.mahindrabattery.security;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
public class NoOpItemWriter<T> implements ItemWriter<T> {


	@Override
	public void write(List<? extends T> items) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
