package com.fmc.ipl.payload;

import java.util.Date;

import lombok.Data;

@Data
public class CustomException {

	private Date date;
	private String details;
	private String message;
	
}
