package com.itc475.RewardsProject.mapper;


import java.time.LocalDateTime;

public class RewardsFanPreference {

	private int fan_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private String first_occupation;
	private LocalDateTime reservation_time;
	private String stand_id;
	
	public int getFan_id() {
		return fan_id;
	}
	public void setFan_id(int fan_id) {
		this.fan_id = fan_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirst_occupation() {
		return first_occupation;
	}
	public void setFirst_occupation(String first_occupation) {
		this.first_occupation = first_occupation;
	}
	public LocalDateTime getReservation_time() {
		return reservation_time;
	}
	public void setReservation_time(LocalDateTime reservation_time) {
		this.reservation_time = reservation_time;
	}
	public String getStand_id() {
		return stand_id;
	}
	public void setStand_id(String stand_id) {
		this.stand_id = stand_id;
	}
	
}
