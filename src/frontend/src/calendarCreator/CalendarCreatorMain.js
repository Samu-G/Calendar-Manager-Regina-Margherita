import {Content, Header} from "antd/es/layout/layout";
import {Button, Col, DatePicker, Divider, PageHeader, Row, Space, Typography} from "antd";
import moment from 'moment';
import React, {useEffect, useState} from "react";
import CalendarCreatorHeader from "./CalendarCreatorHeader";
import CalendarCreatorTable from "./CalendarCreatorTable";
import localization from 'moment/locale/it';
import locale from 'antd/es/date-picker/locale/it_IT';
import {
    getAllStudents, getAllStudentsPresentOnFriday,
    getAllStudentsPresentOnMonday,
    getAllStudentsPresentOnThursday,
    getAllStudentsPresentOnTuesday,
    getAllStudentsPresentOnWednesday,
    getAllTeachers,
    getAllTeachersPresentOnFriday,
    getAllTeachersPresentOnMonday,
    getAllTeachersPresentOnThursday,
    getAllTeachersPresentOnTuesday,
    getAllTeachersPresentOnWednesday
} from "../client";


const {Title, Text} = Typography;

const CalendarCreatorMain = () => {
    const [date, setDate] = useState([]);
    const [dateSelected, setDateSelected] = useState(false);
    const [createClicked, setCreateClicked] = useState(false);

    const [teachers, setTeachers] = useState([]);
    const [teachersPresentOnMonday, setTeachersPresentOnMonday] = useState([]);
    const [teachersPresentOnTuesday, setTeachersPresentOnTuesday] = useState([]);
    const [teachersPresentOnWednesday, setTeachersPresentOnWednesday] = useState([]);
    const [teachersPresentOnThursday, setTeachersPresentOnThursday] = useState([]);
    const [teachersPresentOnFriday, setTeachersPresentOnFriday] = useState([]);

    const [students, setStudents] = useState([]);
    const [studentsPresentOnMonday, setStudentsPresentOnMonday] = useState([]);
    const [studentsPresentOnTuesday, setStudentsPresentOnTuesday] = useState([]);
    const [studentsPresentOnWednesday, setStudentsPresentOnWednesday] = useState([]);
    const [studentsPresentOnThursday, setStudentsPresentOnThursday] = useState([]);
    const [studentsPresentOnFriday, setStudentsPresentOnFriday] = useState([]);

    const fetchTeachers = () => {
        getAllTeachers()
            .then(res => res.json())
            .then(data => {
                setTeachers(data);
            });
        getAllTeachersPresentOnMonday()
            .then(res => res.json())
            .then(data => {
                setTeachersPresentOnMonday(data);
            });
        getAllTeachersPresentOnTuesday()
            .then(res => res.json())
            .then(data => {
                setTeachersPresentOnTuesday(data);
            });
        getAllTeachersPresentOnWednesday()
            .then(res => res.json())
            .then(data => {
                setTeachersPresentOnWednesday(data);
            });
        getAllTeachersPresentOnThursday()
            .then(res => res.json())
            .then(data => {
                setTeachersPresentOnThursday(data);
            });
        getAllTeachersPresentOnFriday()
            .then(res => res.json())
            .then(data => {
                setTeachersPresentOnFriday(data);
            });

    }

    const fetchStudents = () => {
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                setStudents(data);
            })
        getAllStudentsPresentOnMonday()
            .then(res => res.json())
            .then(data => {
                setStudentsPresentOnMonday(data);
            });
        getAllStudentsPresentOnTuesday()
            .then(res => res.json())
            .then(data => {
                setStudentsPresentOnTuesday(data);
            });
        getAllStudentsPresentOnWednesday()
            .then(res => res.json())
            .then(data => {
                setStudentsPresentOnWednesday(data);
            });
        getAllStudentsPresentOnThursday()
            .then(res => res.json())
            .then(data => {
                setStudentsPresentOnThursday(data);
            });
        getAllStudentsPresentOnFriday()
            .then(res => res.json())
            .then(data => {
                setStudentsPresentOnFriday(data);
            });
    }

    useEffect(() => {
        fetchTeachers();
        fetchStudents();
        console.log("Calendar creator main mounted.");
        console.log(teachers);
    }, []);

    function renderCreateCalendarButton() {
        if (dateSelected) {
            return <> <Button type="primary" size="middle" onClick={() => {
                setCreateClicked(true);
            }}> Crea > </Button> </>
        } else {
            return <> <Button type="primary" size="middle" disabled> Crea > </Button> </>
        }
    }

    function getCurrentDate() {
        moment.updateLocale('it', localization);
        return moment().format('dddd') + " " + moment().format('LL');
    }

    function renderHeader() {
        if (!createClicked) {
            return <> <Row> <Space size="middle">
                <b>Oggi è {getCurrentDate()}</b>
                <Divider type="vertical"/>
                <p>Seleziona la data per il calendario:</p>
                <DatePicker locale={locale} style={{width: 200}} onChange={(date, dateString) => {
                    setDate(dateString);
                    setDateSelected(true)
                }}/>
                {renderCreateCalendarButton()}
            </Space> </Row> </>
        } else {
            let dayOfTheWeekName = moment(date, 'YYYY-MM-DD').format('dddd');
            console.log(dayOfTheWeekName);
            switch (dayOfTheWeekName) {
                case "lunedì":
                    return <CalendarCreatorTable date={date} teachers={teachersPresentOnMonday}
                                                 students={studentsPresentOnMonday}/>
                case "martedì":
                    return <CalendarCreatorTable date={date} teachers={teachersPresentOnTuesday}
                                                 students={studentsPresentOnTuesday}/>
                case "mercoledì":
                    return <CalendarCreatorTable date={date} teachers={teachersPresentOnWednesday}
                                                 students={studentsPresentOnWednesday}/>
                case "giovedì":
                    return <CalendarCreatorTable date={date} teachers={teachersPresentOnThursday}
                                                 students={studentsPresentOnThursday}/>
                case "venerdì":
                    return <CalendarCreatorTable date={date} teachers={teachersPresentOnFriday}
                                                 students={studentsPresentOnFriday}/>
            }
        }
    }

    return (
        <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>
            <PageHeader ghost={false} title="Crea un nuovo calendario">
                <Row>
                    <Col span={12}>
                        <Title level={5}>Info docenti</Title>
                        <Space direction="vertical">
                            <Text>Docenti attivi: {teachers.length}</Text>
                            <Text>Presenti di lunedì: {teachersPresentOnMonday.length}</Text>
                            <Text>Presenti di martedì: {teachersPresentOnTuesday.length}</Text>
                            <Text>Presenti di mercoledì: {teachersPresentOnWednesday.length}</Text>
                            <Text>Presenti di giovedì: {teachersPresentOnThursday.length}</Text>
                            <Text>Presenti di venerdì: {teachersPresentOnFriday.length}</Text>
                        </Space>
                    </Col>
                    <Col span={12}>
                        <Title level={5}>Info studenti</Title>
                        <Space direction="vertical">
                            <Text>Studenti attivi: {students.length}</Text>
                            <Text>Presenti di lunedì: {studentsPresentOnMonday.length}</Text>
                            <Text>Presenti di martedì: {studentsPresentOnTuesday.length}</Text>
                            <Text>Presenti di mercoledì: {studentsPresentOnWednesday.length}</Text>
                            <Text>Presenti di giovedì: {studentsPresentOnThursday.length}</Text>
                            <Text>Presenti di venerdì: {studentsPresentOnFriday.length}</Text>
                        </Space>
                    </Col>
                </Row>
            </PageHeader>
            <Divider/>

            {renderHeader()}


        </div>
    );
}

export default CalendarCreatorMain;