package it.brandonmorques.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
//import javax.validation.constraints.Email;
@Setter
@Getter
public class SignupRequest {
	  private String username;
//	    @Email
	    private String email;
	    private Set<String> role;
	    private String password;
	    private String nome;
	    
	}

