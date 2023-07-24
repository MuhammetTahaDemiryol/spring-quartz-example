package taha.demiryol.springquartzexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import taha.demiryol.springquartzexample.entity.SchedulerJobInfo;
import taha.demiryol.springquartzexample.service.SchedulerJobService;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private SchedulerJobService scheduleJobService;

    @GetMapping()
    public String index(Model model){
        List<SchedulerJobInfo> jobList = scheduleJobService.getAllJobList();
        model.addAttribute("jobs", jobList);
        return "index";
    }

}