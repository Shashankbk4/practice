package com.fmc.ipl.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fmc.ipl.dto.TeamDto;

public interface TeamInterface {

	TeamDto createTeam(TeamDto teamDto);
	
	TeamDto updateTeam(TeamDto teamDto);
	
    List<TeamDto> getAllTeams(Pageable page);
    
    TeamDto getTeam(Integer id);
    
    String deletePlayerFromTeam(Integer tId,Integer pId);
    
    String getTeamNameByPlayerId(Integer id);
}
