package com.wittybrains.busbookingsystem.dto;

import java.util.List;
import com.wittybrains.busbookingsystem.model.User;
import com.wittybrains.busbookingsystem.model.Role;

public class UserDTO {
    private Long id;
    private String name;
    private String email;


    public UserDTO() {}

    public UserDTO(User user) {
    	if(user!=null) {
        this.id = user.getUserId();
        this.name = user.getUsername();
        this.email = user.getEmail();

    }
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

