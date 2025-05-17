package com.fmc.ipl.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamDto {

	private Integer teamid;

	@NotNull(message = "team should not be empty or null")
	@NotEmpty(message = "Team name should not be empty or null")
	private String teamName;

	@NotNull(message = "city should not be empty or null")
	@NotEmpty(message = "city name should not be empty or null")
	private String city;

	private List<PlayerDto> player;

}
