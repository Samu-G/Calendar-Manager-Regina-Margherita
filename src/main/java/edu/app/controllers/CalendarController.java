package edu.app.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.app.models.AttendanceRules;
import edu.app.models.Student;
import edu.app.models.Subject;
import edu.app.models.Teacher;
import edu.app.services.StudentService;
import edu.app.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*Calendar creation logic variable*/
    private String date = "";
    private String dayName = "";
    private String beginTime = "";
    private String endTime = "";
    private int timeSlotDimension = 0;
    private List<String> columnsDataIndex = new ArrayList<>();
    private Map<String, List<Student>> studentListForEachTimeSlot = new HashMap<>();
    private List<Teacher> schedulableTeacher = new ArrayList<>();
    /**/


    @PostMapping("/admin/createCalendar")
    public ResponseEntity<?> createAndInitializeCalendarCreation(@RequestBody ObjectNode json) {
        date = json.get("date").textValue();

        String dayNameEng = "vuota";
        switch (json.get("dayName").textValue()) {
            case "lunedì" -> dayNameEng = "Monday";
            case "martedì" -> dayNameEng = "Tuesday";
            case "mercoledì" -> dayNameEng = "Wednesday";
            case "giovedì" -> dayNameEng = "Thursday";
            case "venerdì" -> dayNameEng = "Friday";
        }
        dayName = dayNameEng;

        beginTime = json.get("beginTime").textValue();
        endTime = json.get("endTime").textValue();

        timeSlotDimension = json.get("timeSlotDimension").asInt();

        // fetching teacher and student available
        schedulableTeacher = teacherService.fetchSchedulableTeacherByDayName(dayNameEng);

        return new ResponseEntity<>("Calendar created:\n"
                + date + "\n" +
                dayName + "\n" +
                beginTime + "\n" +
                endTime + "\n" +
                timeSlotDimension,
                HttpStatus.OK);
    }

    @PostMapping("/admin/getRemainingTeacherToSchedule")
    public List<Teacher> getRemainingTeacherToSchedule() {
        List<Teacher> result = new ArrayList<>();
        for (Teacher t : schedulableTeacher) {
            result.add(teacherService.getTeacherById(t.getId()));
        }
        return result;
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

    @PostMapping("/admin/createJsonArrayForColumn")
    public ArrayNode createJsonArrayForColumn() {

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
            long endTimeSlot = (i + timeSlotDimension);
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
            columnsDataIndex.add(h);


            studentListForEachTimeSlot.put(h, studentService.getAllStudentsPresentByDayName(dayName));

            fasciaOraria.put("dataIndex", h);
            fasciaOraria.put("key", h);
            fasciaOraria.put("width", 150);

            arrayNode.addAll(List.of(fasciaOraria));

            i += timeSlotDimension;
        }
        return arrayNode;
    }


    @PostMapping("/admin/generateAndGetTimeSlotByTeacherId")
    public ArrayNode generateAndGetTimeSlotByTeacherId(@RequestBody Long teacherId) {
        if (dayName.equals("")) throw new RuntimeException();
        if (beginTime.equals("undefined")) throw new RuntimeException();
        if (endTime.equals("undefined")) throw new RuntimeException();
        if (timeSlotDimension == 0) throw new RuntimeException();

        System.out.println(teacherId);
        System.out.println(dayName);
        System.out.println(beginTime);
        System.out.println(endTime);
        System.out.println(timeSlotDimension);

        List<AttendanceRules> rules = teacherService.fetchAttendanceRules(teacherId, dayName);
        System.out.println(rules);

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        long beginTimeInMinutes = Duration.parse("PT" + beginTime.replace(":", "H") + "M").toMinutes();
        long endTimeInMinutes = Duration.parse("PT" + endTime.replace(":", "H") + "M").toMinutes();

        for (AttendanceRules ar : rules) {
            long arBeginTime = Duration.parse("PT" + ar.getBeginTime().replace(":", "H") + "M").toMinutes();
            long arEndTime = Duration.parse("PT" + ar.getEndTime().replace(":", "H") + "M").toMinutes();

            while ((arBeginTime + timeSlotDimension) <= arEndTime) {
                long checkTime = arBeginTime + timeSlotDimension;
//                System.out.println(checkTime);
//                System.out.println(beginTimeInMinutes);
//                System.out.println(endTimeInMinutes);
                if (checkTime > beginTimeInMinutes && checkTime <= endTimeInMinutes) {
                    Duration inizio = Duration.ofMinutes(arBeginTime);
                    Duration fine = Duration.ofMinutes(arBeginTime + timeSlotDimension);
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
                arBeginTime += timeSlotDimension;
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
        for (Student s : studentListForEachTimeSlot.get(timeSlotKey)) {
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

    private void removeTeacherFromSchedulableTeacherById(Long id) {
        for (Teacher t : schedulableTeacher) {
            if (t.getId().equals(id)) {
                schedulableTeacher.remove(t);
                break;
            }
        }
    }

    @PostMapping("/admin/generateRowForTableInJson")
    public ObjectNode generateRowForTableInJson(@RequestBody ObjectNode json) {

        removeTeacherFromSchedulableTeacherById(json.get("teacher").get("id").asLong());

        ArrayNode arrayNode = (ArrayNode) json.get("pendingRow");
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode jsonResult = mapper.createObjectNode();
        jsonResult.put("key", json.get("teacher").get("id").asLong());
        jsonResult.put("docente", json.get("teacher").get("name").textValue() + " " + json.get("teacher").get("surname").textValue());

        for (String timeSlotName : columnsDataIndex) {
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

//                    System.out.println(studentListForEachTimeSlot.get(timeSlotName));

                    Long studentToRemove = element.get("studentId").asLong();

                    Student toRemove = null;
                    for (Student s : studentListForEachTimeSlot.get(timeSlotName)) {
                        System.out.println(s.getName() + " " + s.getSurname());
                        if (s.getId().equals(studentToRemove)) {
                            toRemove = s;
                        }
                    }

                    studentListForEachTimeSlot.get(timeSlotName).remove(toRemove);

//                    System.out.println(studentListForEachTimeSlot.get(timeSlotName));


                    studentsInThisTimeSlot.add(toAdd);
                }
            });

            if (!studentsInThisTimeSlot.isEmpty()) {
                jsonResult.set(timeSlotName, studentsInThisTimeSlot);
            }
        }

        return jsonResult;
    }

    @PostMapping("/admin/removeRowByIdAndRefreshRowsDataArray")
    public JsonNode removeRowByIdAndRefreshRowsDataArray(@RequestBody ObjectNode json) {
        System.out.println(json);

        //record with ID = recordKey is to remove
        System.out.println("ROW TO REMOVE IS: " + json.get("recordKey").asInt());
        int rowToRemove = json.get("recordKey").asInt();

//        System.out.println(json.get("dataRowsArray").get(0));
//        System.out.println(json.get("dataRowsArray").get(0).get("key").asInt());
//
//        System.out.println(json.get("dataRowsArray").get(1));
//        System.out.println(json.get("dataRowsArray").get(1).get("key").asInt());

//        System.out.println(schedulableTeacher);

        for (JsonNode dataRowsArray : json.get("dataRowsArray")) {

            if (dataRowsArray.get("key").asInt() == rowToRemove) {
                System.out.println(dataRowsArray);

                // insert teacher into schedulable teacher again
                Teacher toAdd = teacherService.getTeacherById(dataRowsArray.get("key").asLong());
                schedulableTeacher.add(toAdd);

                // insert student into schedulable student by time slot
                studentListForEachTimeSlot.forEach((key, value) -> {

                    if (dataRowsArray.get(key).textValue() == null) {
                        System.out.println("do something on " + key);
                        ArrayNode arrayNode = (ArrayNode) dataRowsArray.get(key);

//                        System.out.println(arrayNode);

                        System.out.println(studentListForEachTimeSlot.get(key));

                        List<Student> newList = new ArrayList<>();

                        arrayNode.forEach((studentNameSurname) -> {
                            String studentString = studentNameSurname.textValue();
                            studentString = studentString.replaceFirst(", ", "");

//                      System.out.println(studentString);

                            String name = studentString.split("\\s+")[0];
                            String surname = studentString.split("\\s+")[1];

                            newList.add(studentService.getStudentByNameAndSurname(name, surname));
                        });

                        studentListForEachTimeSlot.put(key, newList);

                        System.out.println(studentListForEachTimeSlot.get(key));

                    } else if (dataRowsArray.get(key).textValue().equals("-")) {
                        System.out.println("do nothing on " + key);
                    }
                });
                break;
            }
        }

        Iterator<JsonNode> nodeIterator = json.get("dataRowsArray").elements();

        System.out.println("Iterator working...");



        while (nodeIterator.hasNext()) {
            if (nodeIterator.next().get("key").asInt() == rowToRemove) {
                nodeIterator.remove();
            }
        }

        return json.get("dataRowsArray");

    }

}
