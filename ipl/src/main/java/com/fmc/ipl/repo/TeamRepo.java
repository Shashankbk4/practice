package com.fmc.ipl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fmc.ipl.entities.Team;

public interface TeamRepo extends JpaRepository<Team, Integer> {
	
	@Query("SELECT t FROM Team t JOIN t.player p WHERE p.playerId = :playerId")
	Team findTeamByPlayerId(@Param("playerId") Integer playerId);
	
	/*@Query(value="SELECT * FROM Team t JOIN Player p ON p.team_id=t.teamid WHERE p.player_id = :playerId",nativeQuery = true)
	Team findTeamByPlayerId(@Param("playerId") Integer playerId);
	*/
}
