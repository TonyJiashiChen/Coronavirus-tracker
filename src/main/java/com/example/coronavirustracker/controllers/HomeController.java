package com.example.coronavirustracker.controllers;

import com.example.coronavirustracker.models.LocationStats;
import com.example.coronavirustracker.services.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusService coronaVirusService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusService.getAllStats();
        int totalReportedCases=allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
