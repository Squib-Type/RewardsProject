package com.itc475.RewardsProject.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RewardService {
    
    @Autowired
    private RewardsRepository rewardsRepository;
    
    public void processRewards() {
        rewardsRepository.deleteAllRewards();
        
        allocateEastStandSeats();
        allocateWestStandSeats();
        allocateSouthStandSeats();
        allocateNorthStandSeats();
    }
    
    
    private void allocateEastStandSeats() {
        String standId = "East";
        List<RewardsFanPreference> fans = rewardsRepository.findFanPreferenceByStand(standId);
        List<RewardsSeat> seats = rewardsRepository.findSeatsByStand(standId);
        if (fans.isEmpty() || seats.isEmpty()) {
            return;
        }
        
        int seatsToAllocate = Math.min(fans.size(), seats.size());
        for (int i = 0; i < seatsToAllocate; i++) {
            RewardsRewardResult reward = new RewardsRewardResult();
            reward.setFirst_name(fans.get(i).getFirst_name());
            reward.setLast_name(fans.get(i).getLast_name());
            reward.setSeat_id(seats.get(i).getSeat_id());
            rewardsRepository.insertReward(reward);
        }
    }

    private void allocateWestStandSeats() {
        String standId = "West";
        List<RewardsFanPreference> fans = rewardsRepository.findFanPreferenceByStand(standId);
        List<RewardsSeat> seats = rewardsRepository.findSeatsByStand(standId);
        if (fans.isEmpty() || seats.isEmpty()) {
            return;
        }
        
        int seatsToAllocate = Math.min(fans.size(), seats.size());
        for (int i = 0; i < seatsToAllocate; i++) {
            RewardsRewardResult reward = new RewardsRewardResult();
            reward.setFirst_name(fans.get(i).getFirst_name());
            reward.setLast_name(fans.get(i).getLast_name());
            reward.setSeat_id(seats.get(i).getSeat_id());
            rewardsRepository.insertReward(reward);
        }
    }

    private void allocateSouthStandSeats() {
        String standId = "South";
        List<RewardsFanPreference> fans = rewardsRepository.findFanPreferenceByStand(standId);
        List<RewardsSeat> seats = rewardsRepository.findSeatsByStand(standId);
        if (fans.isEmpty() || seats.isEmpty()) {
            return;
        }
        
        List<RewardsFanPreference> shuffledFans = new ArrayList<>(fans);
        Collections.shuffle(shuffledFans);
        int seatsToAllocate = Math.min(shuffledFans.size(), seats.size());
        for (int i = 0; i < seatsToAllocate; i++) {
            RewardsRewardResult reward = new RewardsRewardResult();
            reward.setFirst_name(shuffledFans.get(i).getFirst_name());
            reward.setLast_name(shuffledFans.get(i).getLast_name());
            reward.setSeat_id(seats.get(i).getSeat_id());
            rewardsRepository.insertReward(reward);
        }
    }
    
    private void allocateNorthStandSeats() {
        String standId = "North";
        List<RewardsFanPreference> fans = rewardsRepository.findFanPreferenceByStand(standId);
        List<RewardsSeat> seats = rewardsRepository.findSeatsByStand(standId);
        if (fans.isEmpty() || seats.isEmpty()) {
            return;
        }
  
        List<RewardsFanPreference> militaryFans = new ArrayList<>();
        List<RewardsFanPreference> otherFans = new ArrayList<>();
        for (RewardsFanPreference fan : fans) {
            if ("MLT".equals(fan.getFirst_occupation())) {
                militaryFans.add(fan);
            } else {
                otherFans.add(fan);
            }
        }

        List<RewardsFanPreference> weightedPool = new ArrayList<>();
        for (RewardsFanPreference fan : militaryFans) {
            weightedPool.add(fan);
            weightedPool.add(fan);
            weightedPool.add(fan);
            weightedPool.add(fan);
        }
        weightedPool.addAll(otherFans);
        
        Collections.shuffle(weightedPool);
        Set<String> selectedNames = new HashSet<>();
        List<RewardsFanPreference> selectedFans = new ArrayList<>();
        for (RewardsFanPreference fan : weightedPool) {
            String fullName = fan.getFirst_name() + " " + fan.getLast_name();
            if (!selectedNames.contains(fullName)) {
                selectedFans.add(fan);
                selectedNames.add(fullName);
                if (selectedFans.size() >= Math.min(fans.size(), seats.size())) {
                    break;
                }
            }
        }
        
        for (int i = 0; i < selectedFans.size(); i++) {
            RewardsRewardResult reward = new RewardsRewardResult();
            reward.setFirst_name(selectedFans.get(i).getFirst_name());
            reward.setLast_name(selectedFans.get(i).getLast_name());
            reward.setSeat_id(seats.get(i).getSeat_id());
            rewardsRepository.insertReward(reward);
        }
    }
}