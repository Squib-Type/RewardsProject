package com.itc475.RewardsProject.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RewardsRepository {
	
	@Select("SELECT * FROM t_fan_preference")
	@Results(id="FanPreferenceDataResult", value= {
			@Result(column="fan_id", property="fan_id"),
			@Result(column="first_name", property="first_name"),
			@Result(column="last_name", property="last_name"),
			@Result(column="email", property="email"),
			@Result(column="phone", property="phone"),
			@Result(column="first_occupation", property="first_occupation"),
			@Result(column="reservation_time", property="reservation_time"),
			@Result(column="stand_id", property="stand_id")
			})
	public List<RewardsFanPreference> findFanPreference();
	
	
	@Select("SELECT * FROM t_reward_result")
	@Results(id="RewardResultDataResult", value= {
			@Result(column="reward_id", property="reward_id"),
			@Result(column="fan_id", property="fan_id"),
			@Result(column="seat_id", property="seat_id")
			})
	public List<RewardsRewardResult> findRewardResult();
	
	
	@Select("SELECT * FROM t_seat")
	@Results(id="SeatDataResult", value= {
			@Result(column="seat_id", property="seat_id"),
			@Result(column="stand_id", property="stand_id")
			})
	public List<RewardsSeat> findSeat();
	
	
	@Select("SELECT * FROM t_stand")
	@Results(id="StandDataResult", value= {
			@Result(column="stand_id", property="stand_id"),
			@Result(column="available_seats", property="available_seats"),
			@Result(column="discount_price", property="discount_price")
			})
	public List<RewardsStand> findStand();
	
	@Select("SELECT COUNT(*) FROM t_fan_preference WHERE stand_id = #{stand_id}")
	public int countByStandId(String stand_id);
	
	@Select("SELECT COUNT(*) FROM t_fan_preference WHERE LOWER(first_name) = LOWER(#{firstName}) AND LOWER(last_name) = LOWER(#{lastName})")
	public int checkDuplicateName(String firstName, String lastName);

	@Insert("INSERT INTO t_fan_preference (first_name, last_name, email, phone, first_occupation, stand_id, reservation_time) " +
	        "VALUES (#{first_name}, #{last_name}, #{email}, #{phone}, #{first_occupation}, #{stand_id}, #{reservation_time})")
	public void insertPreference(RewardsFanPreference preference);

	@Delete("DELETE FROM t_fan_preference")
	public void deleteAllPreferences();
	
	@Select("SELECT * FROM t_fan_preference WHERE stand_id = #{standId} ORDER BY reservation_time ASC")
	@Results(id="FanPreferenceByStandResult", value= {
	    @Result(column="fan_id", property="fan_id"),
	    @Result(column="first_name", property="first_name"),
	    @Result(column="last_name", property="last_name"),
	    @Result(column="email", property="email"),
	    @Result(column="phone", property="phone"),
	    @Result(column="first_occupation", property="first_occupation"),
	    @Result(column="reservation_time", property="reservation_time"),
	    @Result(column="stand_id", property="stand_id")
	})
	public List<RewardsFanPreference> findFanPreferenceByStand(String standId);

	@Select("SELECT * FROM t_seat WHERE stand_id = #{standId}")
	public List<RewardsSeat> findSeatsByStand(String standId);

	@Delete("DELETE FROM t_reward_result")
	public void deleteAllRewards();

	@Insert("INSERT INTO t_reward_result (first_name, last_name, seat_id) VALUES (#{first_name}, #{last_name}, #{seat_id})")
	public void insertReward(RewardsRewardResult reward);


	@Select("SELECT COUNT(*) FROM t_seat WHERE stand_id = #{standId}")
	public int countSeatsByStand(String standId);
	
	@Select("SELECT rr.first_name, rr.last_name, fp.email, fp.phone, fp.stand_id, rr.seat_id " +
	        "FROM t_reward_result rr " +
	        "JOIN t_fan_preference fp ON LOWER(rr.first_name) = LOWER(fp.first_name) " +
	        "AND LOWER(rr.last_name) = LOWER(fp.last_name) " +
	        "ORDER BY fp.stand_id")
	@Results(id="RewardResultViewResult", value= {
	    @Result(column="first_name", property="first_name"),
	    @Result(column="last_name", property="last_name"),
	    @Result(column="email", property="email"),
	    @Result(column="phone", property="phone"),
	    @Result(column="stand_id", property="stand_id"),
	    @Result(column="seat_id", property="seat_id")
	})
	public List<RewardsResultView> findRewardResultsWithDetails();

}
