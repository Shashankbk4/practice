package com.fmc.ipl.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fmc.ipl.dto.PlayerDto;
import com.fmc.ipl.dto.TeamDto;
import com.fmc.ipl.entities.Player;
import com.fmc.ipl.entities.Team;
import com.fmc.ipl.exception.ResourceNotFoundException;
import com.fmc.ipl.repo.TeamRepo;
import com.fmc.ipl.service.TeamInterface;



@Service
public class TeamImpl implements TeamInterface {

	@Autowired
	private TeamRepo teamRepo;

	public Team mapTeamDtoToTeam(TeamDto teamDto) {

		Team team = new Team();

		team.setTeamid(teamDto.getTeamid());
		team.setTeamName(teamDto.getTeamName());
		team.setCity(teamDto.getCity());

		List<Player> players = new ArrayList<>();

		List<PlayerDto> playerDto = teamDto.getPlayer();

		for (PlayerDto playerDetails : playerDto) {

			Player player = new Player();

			player.setPlayerId(playerDetails.getPlayerId());
			player.setPlayerName(playerDetails.getPlayerName());
			player.setRole(playerDetails.getRole());
			player.setTeam(team);
			players.add(player);
		}

		team.setPlayer(players);

		return team;
	}

	public TeamDto mapTeamToTeamDto(Team team) {

		TeamDto teamDto = new TeamDto();

		teamDto.setTeamid(team.getTeamid());
		teamDto.setTeamName(team.getTeamName());
		teamDto.setCity(team.getCity());

		List<PlayerDto> players = new ArrayList<>();

		List<Player> playerDto = team.getPlayer();

		for (Player playerDetails : playerDto) {

			PlayerDto playerDto1 = new PlayerDto();
			playerDto1.setPlayerId(playerDetails.getPlayerId());
			playerDto1.setPlayerName(playerDetails.getPlayerName());
			playerDto1.setRole(playerDetails.getRole());

			players.add(playerDto1);
		}

		teamDto.setPlayer(players);

		return teamDto;
	}

	@Override

	public TeamDto createTeam(TeamDto teamDto) {

		Team save = teamRepo.save(mapTeamDtoToTeam(teamDto));

		return mapTeamToTeamDto(save);
	}

	
	  @Override 
	  public TeamDto updateTeam(TeamDto teamDto) {
	  
	  Optional<Team> teamId =teamRepo.findById(teamDto.getTeamid());
	  if(teamId.isPresent()) {
	  
	  Team update =teamRepo.save(mapTeamDtoToTeam(teamDto)); 
	
	  return mapTeamToTeamDto(update); 
	  } 
	  else 
	  { 
		  return null; 
		  }
	  
	  }
	

	@Override
	public List<TeamDto> getAllTeams(Pageable page) {

		Page<Team> teams = teamRepo.findAll(page);
		List<Team> content = teams.getContent();

		return content.stream().map(team -> mapTeamToTeamDto(team)).toList();
	}

	@Override
	public TeamDto getTeam(Integer id) {

		 Team team = teamRepo.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
			    return mapTeamToTeamDto(team);

		
	}

	@Override
	public String deletePlayerFromTeam(Integer tid,Integer pid) {
	Team team=teamRepo.findById(tid).orElseThrow(() -> new ResourceNotFoundException("Team", "id", tid));

	  boolean remove= team.getPlayer().removeIf(p -> p.getPlayerId().equals(pid));

	if(!remove) {
		throw new ResourceNotFoundException("player", "id", pid);
	}
	else {
		teamRepo.save(team);
		return "Player id found and deleted";
	}

	}

	@Override
	public String getTeamNameByPlayerId(Integer id) {
	 
		List<Team> teams=teamRepo.findAll();
		
		for(Team team:teams) {
			
			List<Player> players=team.getPlayer();
	
			for(Player player :players) {
				
				if(player.getPlayerId().equals(id)) {
					
					return team.getTeamName()+" ->"+team.getCity()+" ->"+player.getPlayerName()+" ->"
							+team.getPlayer().stream().map(p -> p.getPlayerName()).collect(Collectors.joining(","));
				}
			
			}
		}
		 return "no record found";
	}
		
	public Team getTeamByPlayerId(Integer id) {
		
		Team team=teamRepo.findTeamByPlayerId(id);
		
		return team;
		
	}

	}
