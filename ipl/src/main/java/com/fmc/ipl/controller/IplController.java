package com.fmc.ipl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmc.ipl.dto.TeamDto;
import com.fmc.ipl.entities.Team;
import com.fmc.ipl.service.TeamInterface;
import com.fmc.ipl.serviceImpl.TeamImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ipl")
public class IplController {

	@Autowired
	private TeamInterface teamInterface;
	@Autowired
	private TeamImpl teamImpl;

	@PostMapping
	public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {

		TeamDto create = teamInterface.createTeam(teamDto);

		return new ResponseEntity<TeamDto>(create, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamDto teamDto) {

		teamDto.setTeamid(teamDto.getTeamid());

		TeamDto update = teamInterface.updateTeam(teamDto);

		return new ResponseEntity<TeamDto>(update, HttpStatus.CREATED);

	}
   
	@GetMapping
	public ResponseEntity<List<TeamDto>> getAllTeams(

			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "teamName", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
		List<TeamDto> allTeams = teamInterface.getAllTeams(pageRequest);

		return new ResponseEntity<List<TeamDto>>(allTeams, HttpStatus.ACCEPTED);

	}

	@GetMapping("/{teamid}")
	public ResponseEntity<TeamDto> getTeam(@PathVariable("teamid") Integer id) {

		TeamDto getTeamById = teamInterface.getTeam(id);
		return new ResponseEntity<TeamDto>(getTeamById, HttpStatus.OK);

	}
	@DeleteMapping("/{teamId}/{playerId}")
	public ResponseEntity<String> deletePlayer(@PathVariable("teamId")  Integer id,@PathVariable("playerId")Integer pid){
	
		
		String deleted=teamInterface.deletePlayerFromTeam(id,pid);
		return new ResponseEntity<String>(deleted,HttpStatus.OK);
		
	}
	@GetMapping("/playerId/{playerId}")
	public String getTeamNameByPlayerId(@PathVariable("playerId") Integer id) {
	
		String player=teamInterface.getTeamNameByPlayerId(id);
		
		return player;
		
	}
	@GetMapping("/teamplayer/{playerId}")
	public String getTeamByPlayerId(@PathVariable("playerId") Integer id) {
		
		Team t1=teamImpl.getTeamByPlayerId(id);
		
		return t1.getTeamName()+"->"+t1.getCity();
		
	}
}
