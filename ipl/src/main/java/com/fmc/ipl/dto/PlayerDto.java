package com.fmc.ipl.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerDto {
   
   private Integer playerId;
   
   @NotEmpty(message ="player should not be empty or null" )
   @NotNull(message ="player should not be empty or null")
   private String playerName;
   
	@NotEmpty(message = "roler should not be empty or null")
	@NotNull(message = "role should not be empty or null")
   private String role;

}
