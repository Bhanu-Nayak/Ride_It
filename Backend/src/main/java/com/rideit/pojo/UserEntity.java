package com.rideit.pojo;

import com.rideit.enumclass.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "userentity", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"email", "role"})
	})
public class UserEntity  extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	private String name;
	
	@Embedded
	private UserEntityId userEntityId;
	
	@Column(length = 300, nullable = false)
	private String password;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String firstName) {
		this.name = firstName;
	}
	
	
	public UserEntityId getUserEntityKey() {
        return userEntityId;
    }
	public void setUserEntityKey(UserEntityId userEntityKey) {
	        this.userEntityId = userEntityKey;
	}
    public String getEmail() {
        return userEntityId.getEmail();
    }

    public void setEmail(String email) {
        this.userEntityId.setEmail(email);
    }

    public Role getRole() {
        return userEntityId.getRole();
    }

    public void setRole(Role role) {
        this.userEntityId.setRole(role);
    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEntity(String name, String email, String password, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.userEntityId = new UserEntityId(email, role);
	}
	@Override
	public String toString() {
		return "UserEntity [firstName=" + name + "email=" + getEmail() + ", password="
				+ password + ", role=" + getRole() + "]";
	}
	
	
}
