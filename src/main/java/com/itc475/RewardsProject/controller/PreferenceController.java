package com.itc475.RewardsProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itc475.RewardsProject.mapper.RewardsFanPreference;
import com.itc475.RewardsProject.mapper.RewardsPreferenceService;
import com.itc475.RewardsProject.mapper.RewardsPreferenceView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PreferenceController {
    
    @Autowired
    private RewardsPreferenceService service;
    
    private static final DateTimeFormatter Formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    @PostMapping("/add")
    public ResponseEntity<?> addPreference(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String email = request.get("email");
        String phone = request.get("phone");
        String firstOccupation = request.get("firstOccupation");
        String preferredStand = request.get("preferredStand");
        String reservationTime = request.get("reservationTime");
        
        if (service.isDuplicateName(firstName, lastName)) {
            response.put("success", false);
            response.put("message", "Duplicate name: " + firstName + " " + lastName);
            return ResponseEntity.badRequest().body(response);
        }
        
        LocalDateTime parsedTime = LocalDateTime.parse(reservationTime, Formatter);
        
        RewardsFanPreference preference = new RewardsFanPreference();
        preference.setFirst_name(firstName);
        preference.setLast_name(lastName);
        preference.setEmail(email);
        preference.setPhone(phone);
        preference.setFirst_occupation(firstOccupation);
        preference.setStand_id(preferredStand);
        preference.setReservation_time(parsedTime);
        service.addPreference(preference);
        
        response.put("success", true);
        response.put("message", "Preference added successfully");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/import")
    public ResponseEntity<?> importPreferences(
    @RequestBody List<Map<String, String>> preferences,
    @RequestParam(defaultValue = "false") boolean confirm) {
        Map<String, Object> response = new HashMap<>();
        Set<String> names = new HashSet<>();
        for (Map<String, String> pref : preferences) {
            String fullName = (pref.get("firstName") + " " + pref.get("lastName")).toLowerCase();
            if (names.contains(fullName)) {
                response.put("success", false);
                response.put("message", "Duplicate names found in file: " + pref.get("firstName") + " " + pref.get("lastName"));
                return ResponseEntity.badRequest().body(response);
            }
            names.add(fullName);
        }
        
        List<RewardsFanPreference> fanPreferences = new ArrayList<>();
        for (Map<String, String> pref : preferences) {
            RewardsFanPreference preference = new RewardsFanPreference();
            preference.setFirst_name(pref.get("firstName"));
            preference.setLast_name(pref.get("lastName"));
            preference.setEmail(pref.get("email"));
            preference.setPhone(pref.get("phone"));
            preference.setFirst_occupation(pref.get("firstOccupation"));
            preference.setStand_id(pref.get("preferredStand"));
            preference.setReservation_time(
                LocalDateTime.parse(pref.get("reservationTime"), Formatter));
            fanPreferences.add(preference);
        }
        
        if (!confirm) {
            response.put("success", true);
            response.put("preview", true);
            response.put("data", fanPreferences);
            return ResponseEntity.ok(response);
        }
        service.replaceAllPreferences(fanPreferences);
        response.put("success", true);
        response.put("message", "Preferences imported successfully");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/import-append")
    public ResponseEntity<?> importAndAppendPreferences(@RequestBody List<Map<String, String>> preferences) {
        Map<String, Object> response = new HashMap<>(); 
        Set<String> names = new HashSet<>();
        for (Map<String, String> pref : preferences) {
            String fullName = (pref.get("firstName") + " " + pref.get("lastName")).toLowerCase();
            if (names.contains(fullName)) {
                response.put("success", false);
                response.put("message", "Duplicate names found in file: " + pref.get("firstName") + " " + pref.get("lastName"));
                return ResponseEntity.badRequest().body(response);
            }
            names.add(fullName);
        }
        
        for (Map<String, String> pref : preferences) {
            if (service.isDuplicateName(pref.get("firstName"), pref.get("lastName"))) {
                response.put("success", false);
                response.put("message", "Duplicate names found: " + pref.get("firstName") + " " + pref.get("lastName"));
                return ResponseEntity.badRequest().body(response);
            }
        }
        
        List<RewardsFanPreference> fanPreferences = new ArrayList<>();
        for (Map<String, String> pref : preferences) {
            RewardsFanPreference preference = new RewardsFanPreference();
            preference.setFirst_name(pref.get("firstName"));
            preference.setLast_name(pref.get("lastName"));
            preference.setEmail(pref.get("email"));
            preference.setPhone(pref.get("phone"));
            preference.setFirst_occupation(pref.get("firstOccupation"));
            preference.setStand_id(pref.get("preferredStand"));
            preference.setReservation_time(
                LocalDateTime.parse(pref.get("reservationTime"), Formatter));
            fanPreferences.add(preference);
        }
        service.appendPreferences(fanPreferences);
        response.put("success", true);
        response.put("message", "Preferences appended successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getPreferenceSummary() {
        List<RewardsPreferenceView> summaries = service.findPreferenceSummary();
        return ResponseEntity.ok(summaries);
    }
}