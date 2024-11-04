package com.board.users.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data  
// DATA:  @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	
	private String userid;
	private String passwd;
	private String username;
	private String email;
	private int upoint;
	private String regdate;
	
}
