package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repository.DayRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.TimeSlotRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.util.*;

@AllArgsConstructor
@Service
public class TeacherService {

    private final TimeSlotRepository timeSlotRepository;

    private final SubjectRepository subjectRepository;

    private final TeacherRepository teacherRepository;

    private final DayRepository dayRepository;


    /*Get, Post, Delete teacher management*/
    @PostMapping
    public void addTeacher(String name, String surname, String emailAddress) {
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        List<Subject> subjectsTeached = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOfTheTeacher = new ArrayList<>();
        Teacher aNewTeacher = new Teacher(null, name, surname, emailAddress, true, daysOfPresenceOfTheTeacher,
                timeSlotsOfPresenceOfTheTeacher, subjectsTeached);
        teacherRepository.save(aNewTeacher);
    }

    @PostMapping
    public void removeTeacher(Long id) {
        Teacher toRemove = teacherRepository.findTeacherById(id);
        teacherRepository.delete(toRemove);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = teacherRepository.findAll();
        Collections.sort(teacherList);
        return teacherList;
    }

    /********************/

    /*Active teacher, email address management*/
    @PostMapping
    public void setEmailAddressToTeacher(Long id, String emailAddress) {
        Teacher toEdit = teacherRepository.findTeacherById(id);
        toEdit.setEmailAddress(emailAddress);
        teacherRepository.save(toEdit);
    }


    @PostMapping
    public void setActiveToTeacher(Long id, boolean isActive) {
        Teacher toEdit = teacherRepository.findTeacherById(id);
        toEdit.setActive(isActive);
        teacherRepository.save(toEdit);
    }

    /********************/

    /*Days of presence management */
    @GetMapping
    public List<String> getNameOfTheDaysOfPresenceFromTeacher(Long id) {
        List<String> nameOfTheDaysOfPresenceList = new ArrayList<>();
        Teacher teacher = teacherRepository.findTeacherById(id);
        for (Day day : teacher.getDaysOfPresence()) {
            nameOfTheDaysOfPresenceList.add(day.getDayName());
        }
        return nameOfTheDaysOfPresenceList;
    }

    @PostMapping
    public void setDaysOfAttendanceToTeacher(Long id, JsonNode daysList) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        teacher.getDaysOfPresence().clear();
        List<Day> daysOfPresence = teacher.getDaysOfPresence();
        for (JsonNode node : daysList) {
            switch (node.textValue()) {
                case "Lunedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Monday"));
                case "Martedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Tuesday"));
                case "Mercoledì" -> daysOfPresence.add(dayRepository.findDayByDayName("Wednesday"));
                case "Giovedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Thursday"));
                case "Venerdì" -> daysOfPresence.add(dayRepository.findDayByDayName("Friday"));
            }
        }
        teacherRepository.save(teacher);
    }

    /********************/

    /*Subject management */
    @GetMapping
    public List<String> getSubjectByTeacher(Long id) {
        List<String> nameOfTheSubjectsFollowed = new ArrayList<>();
        Teacher teacher = teacherRepository.findTeacherById(id);
        for (Subject subject : teacher.getSubjectsTeached()) {
            nameOfTheSubjectsFollowed.add(subject.getNameOfTheSubject());
        }
        return nameOfTheSubjectsFollowed;
    }

    @GetMapping
    public List<String> getSubjectNotTeachByTeacher(Long id) {
        List<Subject> allSubjects = subjectRepository.findAll();
        List<String> nameOfTheSubjectsNotFollowed = new ArrayList<>();
        Teacher teacher = teacherRepository.findTeacherById(id);
        for (Subject subject : allSubjects) {
            if (!teacher.getSubjectsTeached().contains(subject)) {
                nameOfTheSubjectsNotFollowed.add(subject.getNameOfTheSubject());
            }
        }
        return nameOfTheSubjectsNotFollowed;
    }

    @PostMapping
    public void addSubjectTeachByTeacher(Long id, String subjectName) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        Subject toAdd = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        teacher.getSubjectsTeached().add(toAdd);
        teacherRepository.save(teacher);
    }

    @PostMapping
    public void removeSubjectTeachByTeacher(Long id, String subjectName) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        Subject toRemove = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        teacher.getSubjectsTeached().remove(toRemove);
        teacherRepository.save(teacher);
    }

    /********************/

    /*Time slot management*/
    @GetMapping
    public List<String> getTimeSlotFromTeacherByDayName(Long id, String dayName) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        String dayNameTranslatedInEng = translateDayName(dayName);
        List<String> timeSlotsName = new ArrayList<>();
        for (TimeSlot ts : teacher.getTimeSlotsOfPresence()) {
            String[] tsNameSplit = ts.getTimeSlotName().split("_");
            if (tsNameSplit[0].equals(dayNameTranslatedInEng)) {
                timeSlotsName.add(tsNameSplit[2] + ":" + tsNameSplit[3] + "-" + tsNameSplit[5] + ":" + tsNameSplit[6]);
            }
        }
        System.out.println(timeSlotsName);
        return timeSlotsName;
    }

    @PostMapping
    public void setTimeSlotForTeacherByDayName(Long id, String dayName, JsonNode timeSlotsList) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        String dayNameTranslatedInEng = translateDayName(dayName);
        System.out.println(teacher);
        System.out.println(dayNameTranslatedInEng);
        System.out.println(timeSlotsList);
        System.out.println(teacher.getTimeSlotsOfPresence());

        List<TimeSlot> timeSlotsToRemove = new ArrayList<>();
        for (TimeSlot t : teacher.getTimeSlotsOfPresence()) {
            String[] tsNameSplit = t.getTimeSlotName().split("_");
            if (tsNameSplit[0].equals(dayNameTranslatedInEng)) {
                timeSlotsToRemove.add(t);
            }
        }
        teacher.getTimeSlotsOfPresence().removeAll(timeSlotsToRemove);


        for (JsonNode node : timeSlotsList) {
            String timeSlotName = node.textValue();
            String[] tsNameSplit = timeSlotName.split("-");
            String tsBeginTime = tsNameSplit[0];
            String tsEndTime = tsNameSplit[1];
            String[] tsBeginTimeSplit = tsBeginTime.split(":");
            String[] tsEndTimeSplit = tsEndTime.split(":");
            String backEndTimeSlotName = dayNameTranslatedInEng + "_from_" +
                    tsBeginTimeSplit[0] + "_" + tsBeginTimeSplit[1] + "_to_" +
                    tsEndTimeSplit[0] + "_" + tsEndTimeSplit[1];
            System.out.println(backEndTimeSlotName);
            teacher.getTimeSlotsOfPresence().add(timeSlotRepository.findTimeSlotByTimeSlotName(backEndTimeSlotName));
        }

        teacherRepository.save(teacher);
    }

    /********************/

    private String translateDayName(String dayName) {
        String translatedDayName;
        switch (dayName) {
            case "Lunedì" -> translatedDayName = "monday";
            case "Martedì" -> translatedDayName = "tuesday";
            case "Mercoledì" -> translatedDayName = "wednesday";
            case "Giovedì" -> translatedDayName = "thursday";
            case "Venerdì" -> translatedDayName = "friday";
            default -> translatedDayName = "error";
        }
        return translatedDayName;
    }

    @GetMapping
    public List<Teacher> getAllTeachersPresentOnMonday() {
        List<Teacher> teachersList = getAllTeachers();
        List<Teacher> teachersPresentOnMonday = new ArrayList<>();
        for (Teacher t : teachersList) {
            if (t.getDaysOfPresence().contains(dayRepository.findDayByDayName("Monday"))) {
                teachersPresentOnMonday.add(t);
            }
        }
        return teachersPresentOnMonday;
    }

    @GetMapping
    public List<Teacher> getAllTeachersPresentOnTuesday() {
        List<Teacher> teachersList = getAllTeachers();
        List<Teacher> teachersPresentOnTuesday = new ArrayList<>();
        for (Teacher t : teachersList) {
            if (t.getDaysOfPresence().contains(dayRepository.findDayByDayName("Tuesday"))) {
                teachersPresentOnTuesday.add(t);
            }
        }
        return teachersPresentOnTuesday;
    }

    @GetMapping
    public List<Teacher> getAllTeachersPresentOnWednesday() {
        List<Teacher> teachersList = getAllTeachers();
        List<Teacher> teachersPresentOnWednesday = new ArrayList<>();
        for (Teacher t : teachersList) {
            if (t.getDaysOfPresence().contains(dayRepository.findDayByDayName("Wednesday"))) {
                teachersPresentOnWednesday.add(t);
            }
        }
        return teachersPresentOnWednesday;
    }

    @GetMapping
    public List<Teacher> getAllTeachersPresentOnThursday() {
        List<Teacher> teachersList = getAllTeachers();
        List<Teacher> teachersPresentOnThursday = new ArrayList<>();
        for (Teacher t : teachersList) {
            if (t.getDaysOfPresence().contains(dayRepository.findDayByDayName("Thursday"))) {
                teachersPresentOnThursday.add(t);
            }
        }
        return teachersPresentOnThursday;
    }

    @GetMapping
    public List<Teacher> getAllTeachersPresentOnFriday() {
        List<Teacher> teachersList = getAllTeachers();
        List<Teacher> teachersPresentOnFriday = new ArrayList<>();
        for (Teacher t : teachersList) {
            if (t.getDaysOfPresence().contains(dayRepository.findDayByDayName("Friday"))) {
                teachersPresentOnFriday.add(t);
            }
        }
        return teachersPresentOnFriday;
    }


    @PostMapping
    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

//    @GetMapping
//    public TimeSlot getTimeSlotByBeginTimeAndEndTime(Time begin, Time end) {
//        return timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(begin, end);
//    }

    @GetMapping
    public Teacher getTeacherByNameAndSurname(String name, String surname) {
        return teacherRepository.findTeacherByNameAndSurname(name, surname);
    }


    @PostMapping
    public void deleteSubjectFromTheTeacher(ObjectNode json) {
        Long teacherId = json.get("id").asLong();
        String subjectName = json.get("subjectName").toString();
        subjectName = subjectName.substring(5, subjectName.length() - 1);
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Subject toRemove = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        teacher.getSubjectsTeached().remove(toRemove);
        teacherRepository.save(teacher);
    }


//    public void setDayOfAttendanceToTeacher(ObjectNode json) {
//        Long teacherId = json.get("id").asLong();
//        Iterator<JsonNode> presenceDaysList = json.get("presenceDaysList").elements();
//        System.out.println(teacherId);
//        Teacher teacher = teacherRepository.findTeacherById(teacherId);
//        teacher.setMondayIsPresent(false);
//        teacher.setTuesdayIsPresent(false);
//        teacher.setWednesdayIsPresent(false);
//        teacher.setThursdayIsPresent(false);
//        teacher.setFridayIsPresent(false);
//        while (presenceDaysList.hasNext()) {
//            String specificDay = presenceDaysList.next().toString();
//            specificDay = specificDay.substring(1, specificDay.length() - 1);
//            switch (specificDay) {
//                case "Lunedi" -> teacher.setMondayIsPresent(true);
//                case "Martedi" -> teacher.setTuesdayIsPresent(true);
//                case "Mercoledi" -> teacher.setWednesdayIsPresent(true);
//                case "Giovedi" -> teacher.setThursdayIsPresent(true);
//                case "Venerdi" -> teacher.setFridayIsPresent(true);
//            }
//        }
//        teacherRepository.save(teacher);
//    }
}
