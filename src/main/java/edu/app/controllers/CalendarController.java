package edu.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.app.models.AttendanceRules;
import edu.app.models.Student;
import edu.app.models.Subject;
import edu.app.models.Teacher;
import edu.app.services.StudentService;
import edu.app.services.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class CalendarController {
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CalendarController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    private String dayNameCalendar = "undefined";
    private String beginTimeCalendar = "undefined";
    private String endTimeCalendar = "undefined";
    private int timeSlotDurationCalendar = 0;

    private List<String> dataIndexColonne = new ArrayList<>();
    private Map<String, List<Student>> colonne = new HashMap<>();

    private List<Teacher> schedulableTeacher = new ArrayList<>();
    private int numeroDiRighe = 1;


    @PostMapping("/admin/fetchSchedulableTeacher")
    public List<Teacher> fetchSchedulableTeacherByDayName(@RequestBody ObjectNode json) {
        String dayName = json.get("dayName").textValue();
        String dayNameEng = "vuota";
        switch (dayName) {
            case "lunedì" -> dayNameEng = "Monday";
            case "martedì" -> dayNameEng = "Tuesday";
            case "mercoledì" -> dayNameEng = "Wednesday";
            case "giovedì" -> dayNameEng = "Thursday";
            case "venerdì" -> dayNameEng = "Friday";
        }
        return teacherService.fetchSchedulableTeacherByDayName(dayNameEng);
    }


    @PostMapping("/admin/fetchSchedulableStudent")
    public List<Student> fetchSchedulableStudentByDayName(@RequestBody ObjectNode json) {
        String dayName = json.get("dayName").textValue();
        String dayNameEng = "vuota";
        switch (dayName) {
            case "lunedì" -> dayNameEng = "Monday";
            case "martedì" -> dayNameEng = "Tuesday";
            case "mercoledì" -> dayNameEng = "Wednesday";
            case "giovedì" -> dayNameEng = "Thursday";
            case "venerdì" -> dayNameEng = "Friday";
        }
        return studentService.fetchSchedulableStudentByDayName(dayNameEng);
    }

    @PostMapping("/admin/fetchSchedulableTeacherInCalendar")
    public List<Teacher> fetchSchedulableTeacherInCalendar() {
        List<Teacher> result = new ArrayList<>();
        for(Teacher t : schedulableTeacher) {
            result.add(teacherService.getTeacherById(t.getId()));
        }
        return result;
    }

    @PostMapping("/admin/createColumnArrayByBeginTimeEndTimeDuration")
    public ArrayNode createJsonColumnArray(@RequestBody ObjectNode json) {
        String dayNameEng = "vuota";
        switch (json.get("dayName").textValue()) {
            case "lunedì" -> dayNameEng = "Monday";
            case "martedì" -> dayNameEng = "Tuesday";
            case "mercoledì" -> dayNameEng = "Wednesday";
            case "giovedì" -> dayNameEng = "Thursday";
            case "venerdì" -> dayNameEng = "Friday";
        }
        schedulableTeacher = teacherService.fetchSchedulableTeacherByDayName(dayNameEng);
        dayNameCalendar = dayNameEng;
        String beginTime = json.get("beginTime").textValue();
        beginTimeCalendar = json.get("beginTime").textValue();
        String endTime = json.get("endTime").textValue();
        endTimeCalendar = json.get("endTime").textValue();
        int duration = json.get("duration").asInt();
        timeSlotDurationCalendar = json.get("duration").asInt();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        ObjectNode colonnaDocente = mapper.createObjectNode();
        colonnaDocente.put("title", "Docente");
        colonnaDocente.put("dataIndex", "docente");
        colonnaDocente.put("key", "docente");
        colonnaDocente.put("width", 200);
        colonnaDocente.put("fixed", "left");

        arrayNode.addAll(List.of(colonnaDocente));

        long beginTimeInMinutes = Duration.parse("PT" + beginTime.replace(":", "H") + "M").toMinutes();
        long endTimeInMinutes = Duration.parse("PT" + endTime.replace(":", "H") + "M").toMinutes();
        System.out.println(beginTimeInMinutes + " " + endTimeInMinutes);
        long i = beginTimeInMinutes;
        while (i < endTimeInMinutes) {
            long beginTimeSlot = i;
            long endTimeSlot = (i + duration);
            Duration d = Duration.ofMinutes(beginTimeSlot);
            Duration e = Duration.ofMinutes(endTimeSlot);
            String f = String.format("%02d:%02d", d.toHoursPart(), d.toMinutesPart());
            String g = String.format("%02d:%02d", e.toHoursPart(), e.toMinutesPart());

            ObjectNode fasciaOraria = mapper.createObjectNode();
            String h = f + " - " + g;
            fasciaOraria.put("title", h);
            h = h.replaceAll(":", "");
            h = h.replaceAll(" - ", "_");
            System.out.println("h = " + h);
            System.out.println(h);
            dataIndexColonne.add(h);


            colonne.put(h, studentService.getAllStudentsPresentByDayName(dayNameCalendar));

            fasciaOraria.put("dataIndex", h);
            fasciaOraria.put("key", h);
            fasciaOraria.put("width", 150);

            arrayNode.addAll(List.of(fasciaOraria));

            i += duration;
        }
        return arrayNode;
    }

    @PostMapping("/admin/getTimeSlotFromTeacherById")
    public ArrayNode getAvailableStudentByTeacherAndTimeSlot(@RequestBody Long teacherId) {
        if (dayNameCalendar.equals("undefined")) throw new RuntimeException();
        if (beginTimeCalendar.equals("undefined")) throw new RuntimeException();
        if (endTimeCalendar.equals("undefined")) throw new RuntimeException();
        if (timeSlotDurationCalendar == 0) throw new RuntimeException();

        System.out.println(teacherId);
        System.out.println(dayNameCalendar);
        System.out.println(beginTimeCalendar);
        System.out.println(endTimeCalendar);
        System.out.println(timeSlotDurationCalendar);

        List<AttendanceRules> rules = teacherService.fetchAttendanceRules(teacherId, dayNameCalendar);
        System.out.println(rules);

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        long beginTimeInMinutes = Duration.parse("PT" + beginTimeCalendar.replace(":", "H") + "M").toMinutes();
        long endTimeInMinutes = Duration.parse("PT" + endTimeCalendar.replace(":", "H") + "M").toMinutes();

        for (AttendanceRules ar : rules) {
            long arBeginTime = Duration.parse("PT" + ar.getBeginTime().replace(":", "H") + "M").toMinutes();
            long arEndTime = Duration.parse("PT" + ar.getEndTime().replace(":", "H") + "M").toMinutes();

            while ((arBeginTime + timeSlotDurationCalendar) < arEndTime) {
                long checkTime = arBeginTime + timeSlotDurationCalendar;
//                System.out.println(checkTime);
//                System.out.println(beginTimeInMinutes);
//                System.out.println(endTimeInMinutes);
                if (checkTime > beginTimeInMinutes && checkTime <= endTimeInMinutes) {
                    Duration inizio = Duration.ofMinutes(arBeginTime);
                    Duration fine = Duration.ofMinutes(arBeginTime + timeSlotDurationCalendar);
                    String i = String.format("%02d%02d", inizio.toHoursPart(), inizio.toMinutesPart());
                    String f = String.format("%02d%02d", fine.toHoursPart(), fine.toMinutesPart());
                    String res = i + "_" + f;
                    ObjectNode fasciaOraria = mapper.createObjectNode();
                    fasciaOraria.put("dataIndex", res);
                    fasciaOraria.put("key", res);
                    fasciaOraria.put("beginTime", String.format("%02d:%02d", inizio.toHoursPart(), inizio.toMinutesPart()));
                    fasciaOraria.put("endTime", String.format("%02d:%02d", fine.toHoursPart(), fine.toMinutesPart()));
                    arrayNode.addAll(List.of(fasciaOraria));
                }
                arBeginTime += timeSlotDurationCalendar;
            }
        }
        return arrayNode;
    }

    @PostMapping("/admin/getAvailableStudentByTeacherAndTimeSlot")
    public ArrayNode getAvailableStudentByTeacherAndTimeSlot(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String timeSlotKey = json.get("timeSlotName").textValue();
        System.out.println(teacherId);
        System.out.println(timeSlotKey);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        List<Subject> subjectsTeached = teacherService.getTeacherById(teacherId).getSubjectsTeached();
        for (Student s : colonne.get(timeSlotKey)) {
            List<Subject> subjectsFollowed = studentService.getStudentById(s.getId()).getSubjectsFollowed();
            for (Subject subject : subjectsTeached) {
                if (subjectsFollowed.contains(subject)) {
                    ObjectNode labelValue = mapper.createObjectNode();
                    String label;
                    String value;
                    Long key;
                    if (!s.getFiscalCode().equals("")) {
                        label = s.getName() + " " + s.getSurname() + " (" + s.getFiscalCode() + ")";
                        value = s.getName() + " " + s.getSurname() + " (" + s.getFiscalCode() + ")";
                    } else {
                        label = s.getName() + " " + s.getSurname();
                        value = s.getName() + " " + s.getSurname();
                    }
                    key = s.getId();

                    labelValue.put("label", label);
                    labelValue.put("value", value);
                    labelValue.put("key", key);

                    arrayNode.addAll(List.of(labelValue));
                    break;
                }
            }
        }
        return arrayNode;
    }

    @PostMapping("/admin/generateRowForTableInJson")
    public ObjectNode generateRowForTableInJson(@RequestBody ObjectNode json) {
        System.out.println(json);

        ArrayNode arrayNode = (ArrayNode) json.get("pendingRow");
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode jsonResult = mapper.createObjectNode();
        jsonResult.put("key", numeroDiRighe);
        jsonResult.put("docente", json.get("teacher").get("name").textValue() + " " + json.get("teacher").get("surname").textValue());

        for (String timeSlotName : dataIndexColonne) {
            jsonResult.put(timeSlotName, "-");

            ArrayNode studentsInThisTimeSlot = mapper.createArrayNode();

            arrayNode.forEach(element -> {
                if (element.get("timeSlotId").textValue().equals(timeSlotName)) {
                    String toAdd;
                    if (studentsInThisTimeSlot.isEmpty()) {
                        toAdd = element.get("studentNameSurnameCF").textValue();
                    } else {
                        toAdd = ", " + element.get("studentNameSurnameCF").textValue();
                    }

                    int indexOfCF = toAdd.indexOf("(");
                    if (indexOfCF != -1) {
                        toAdd = toAdd.substring(0, (indexOfCF - 1));
                    }

                    System.out.println(colonne.get(timeSlotName));

                    Long studentToRemove = element.get("studentId").asLong();

                    Student toRemove = null;
                    for (Student s : colonne.get(timeSlotName)) {
                        System.out.println(s.getName() + " " + s.getSurname());
                        if (s.getId().equals(studentToRemove)) {
                            toRemove = s;
                        }
                    }

                    colonne.get(timeSlotName).remove(toRemove);

                    System.out.println(colonne.get(timeSlotName));


                    studentsInThisTimeSlot.add(toAdd);
                }
            });

            if (!studentsInThisTimeSlot.isEmpty()) {
                jsonResult.set(timeSlotName, studentsInThisTimeSlot);
            }
        }

        numeroDiRighe++;

        return jsonResult;
    }
}
