package com.itc475.RewardsProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itc475.RewardsProject.mapper.RewardService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reward")
public class RewardController {
    
    @Autowired
    private RewardService rewardService;
    
    @PostMapping("/process")
    @ResponseBody
    public ResponseEntity<?> processRewards() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            rewardService.processRewards();
            response.put("success", true);
            response.put("message", "Rewards processed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error processing rewards: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}