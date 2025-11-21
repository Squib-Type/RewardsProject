package com.itc475.RewardsProject.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class RewardsPreferenceService {
	
	@Autowired
	private RewardsRepository rewardsRepository;
	
	public List<RewardsPreferenceView> findPreferenceSummary(){
		
		List<RewardsPreferenceView> rets = new ArrayList<RewardsPreferenceView>();
		

		List<RewardsStand> rewardStands = rewardsRepository.findStand();
		
		for (RewardsStand stand : rewardStands) {
			
			RewardsPreferenceView view = new RewardsPreferenceView();
			
			view.setStand_id(stand.getStand_id());
			view.setAvailable_seats(stand.getAvailable_seats());
			view.setDiscount_price(stand.getDiscount_price());
			
			int preferredSeatsCount = rewardsRepository.countByStandId(stand.getStand_id());
			view.setPreferred_seats(preferredSeatsCount);
			
			int seatsForSale = Math.min(view.getPreferred_seats(), view.getAvailable_seats());
			BigDecimal preferredSeats = new BigDecimal(seatsForSale);
			BigDecimal totalEarnings = stand.getDiscount_price().multiply(preferredSeats);
			view.setEstimatedTotalEarnings(totalEarnings);
			
			
			rets.add(view);
		}
		
		
		return rets;
	}
	
	public boolean isDuplicateName(String firstName, String lastName) {
	    return rewardsRepository.checkDuplicateName(firstName, lastName) > 0;
	}

	public void addPreference(RewardsFanPreference preference) {
	    rewardsRepository.insertPreference(preference);
	}

	public void replaceAllPreferences(List<RewardsFanPreference> preferences) {
	    rewardsRepository.deleteAllPreferences();
	    for (RewardsFanPreference preference : preferences) {
	        rewardsRepository.insertPreference(preference);
	    }
	}
	
	public void appendPreferences(List<RewardsFanPreference> preferences) {
	    for (RewardsFanPreference preference : preferences) {
	        rewardsRepository.insertPreference(preference);
	    }
	}
	
	public List<RewardsResultView> getRewardResults() {
	    return rewardsRepository.findRewardResultsWithDetails();
	}
}
