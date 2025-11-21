package com.itc475.RewardsProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itc475.RewardsProject.mapper.RewardsPreferenceService;
import com.itc475.RewardsProject.mapper.RewardsPreferenceView;
import com.itc475.RewardsProject.mapper.RewardsResultView;

@Controller
public class HomeController {
	
	@Autowired
	private RewardsPreferenceService service;

	@GetMapping("/")
	public String showPreferencePage(Model model) {
		List<RewardsPreferenceView> rewardsPreferenceView = service.findPreferenceSummary();
		model.addAttribute("summaries", rewardsPreferenceView);
		return "preference-page";
	}
	
	@GetMapping("/reward-results")
	public String showRewardResultsPage(Model model) {
		List<RewardsResultView> rewardResults = service.getRewardResults();
		model.addAttribute("rewardResults", rewardResults);
		return "reward-results";
	}
}