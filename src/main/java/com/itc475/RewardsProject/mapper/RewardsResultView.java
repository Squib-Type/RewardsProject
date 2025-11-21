package com.itc475.RewardsProject.mapper;

public class RewardsResultView {
	
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String stand_id;
    private String seat_id;
    
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
    public String getStand_id() {
        return stand_id;
    }
    public void setStand_id(String stand_id) {
        this.stand_id = stand_id;
    }
    public String getSeat_id() {
        return seat_id;
    }
    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }
}