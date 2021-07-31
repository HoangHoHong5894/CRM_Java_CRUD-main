package cyber.java.crmApp.dto;

import cyber.java.crmApp.model.Role;

public class UserCreateDto {

	/* Data Transfer Object */
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private int roleId;
	private int id;
	
	
	public UserCreateDto() {
		this.id = id;
		
	}
	
	public UserCreateDto(String email, String password, String name, String address, String phone, int roleId, int id) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
		this.id = id;
	}
	
	public UserCreateDto(String email, String name, String address, String phone, int id) {
		this.email = email;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
