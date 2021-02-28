package Group10.example.API.Controller;

import Group10.example.API.Model.Result;
import Group10.example.API.Model.Schedule;
import Group10.example.API.Model.ScheduleUpdatePayload;
import Group10.example.API.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //timetable apis
    @PostMapping(value = "/add")//checked
    public Schedule addScheduleItem( @Valid @RequestBody Schedule schedule){
        return scheduleService.addScheduleItem(schedule);
    }

    @GetMapping(value = "/find/all")//checked
    public Collection<Schedule> findAllSchedules(){
        return scheduleService.findAllSchedules();
    }

    @GetMapping(value = "/findbycourse/{id}")//checked
    public Collection<Schedule> findSchedulesByCourse(@PathVariable("id")String courseID){
        return scheduleService.findSchedulesByCourse(courseID);
    }

    @GetMapping(value = "/findbylecturer/{id}")//checked
    public Collection<Schedule> findSchedulesByLecturer(@PathVariable("id")String lecturerID){
        return scheduleService.findSchedulesByLecturer(lecturerID);
    }

    @GetMapping(value = "/findbycourseanddayofweek")//checked
    public Collection<Schedule> findSchedulesByCourseAndDayOfWeek(@RequestParam("course")String courseId,@RequestParam("day")String dayOfWeek){
        return scheduleService.findSchedulesByCourseAndDayOfWeek(courseId,dayOfWeek);
    }

    @GetMapping(value = "/findbystudentanddayofweek")
    public Collection<Schedule> findSchedulesByStudentAndDayOfWeek(@RequestParam("student")String studentId,@RequestParam("day")String dayOfWeek){
        return scheduleService.findSchedulesByStudentAndDayOfWeek(studentId,dayOfWeek);
    }

    @GetMapping(value = "/findbystudent/{id}")
    public Collection<Schedule> findSchedulesByStudent(@PathVariable("id")String studentId){
        return scheduleService.findSchedulesByStudent(studentId);
    }

    @PutMapping(value = "/update/{id}")
    public Optional<Schedule> updateScheduleById(@PathVariable("id")String scheduleId, @Valid @RequestBody ScheduleUpdatePayload scheduleUpdatePayload){
        return scheduleService.updateScheduleById(scheduleId,scheduleUpdatePayload);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Result deleteScheduleById(@PathVariable("id")String scheduleId){
        return scheduleService.deleteScheduleById(scheduleId);
    }

    @DeleteMapping(value = "/deleteallbycourse/{id}")
    public Result deleteAllSchedulesByCourseId(@PathVariable("id")String courseId){
        return scheduleService.deleteAllSchedulesByCourseId(courseId);
    }

    @GetMapping(value = "/findscheduledetailsbylecturer/{id}")
    public HashMap<String,ArrayList<ArrayList<String[]>>> findScheduleDetailsByLecturer(@PathVariable("id")String lecturerId){
        return scheduleService.findScheduleDetailsByLecturer(lecturerId);
    }

}

