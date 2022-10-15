import {Header} from "antd/es/layout/layout";
import {Button, Col, DatePicker, Divider, PageHeader, Row, Space, Typography} from "antd";
import moment from 'moment';
import React, {useEffect, useState} from "react";
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
import {CalendarCreatorConfigurator} from "./CalendarCreatorConfigurator";

const {Title, Text} = Typography;

const CalendarCreatorMain = () => {
    const [date, setDate] = useState([]);
    const [dateSelected, setDateSelected] = useState(false);
    const [createClicked, setCreateClicked] = useState(false);
    // const dispatch = useDispatch();

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
    }, []);

    function getCurrentDate() {
        moment.updateLocale('it', localization);
        return moment().format('dddd') + " " + moment().format('LL');
    }

    function normalizeDateForItaly(date) {
        let data = moment(date, 'YYYY-MM-DD');
        return data.format('dddd') + " " + data.format('LL');
    }

    function returnTitleOfTheCalendar() {
        return "Piano di lavoro giornaliero per " + normalizeDateForItaly(date);
    }

    function renderCreateCalendarButton() {
        if (dateSelected) {
            return <>
                <Button type="primary" size="middle" onClick={() => {
                    setCreateClicked(true);
                }}> Crea calendario >
                </Button>
            </>
        } else {
            return <>
                <Button type="primary" size="middle" disabled>
                    Crea >
                </Button>
            </>
        }
    }

    function renderHeader() {
        if (!createClicked) {
            return <>
                <Space size={"middle"}>
                    <Text strong>Oggi è {getCurrentDate()}</Text>
                    <Divider type="vertical"/>
                    <Text>Seleziona la data per il calendario:</Text>
                    <DatePicker locale={locale} style={{width: 200}} onChange={(date, dateString) => {
                        setDate(dateString);
                        setDateSelected(true);
                    }}/>
                    {renderCreateCalendarButton()}
                </Space>
            </>
        } else {
            let dayOfTheWeekName = moment(date, 'YYYY-MM-DD').format('dddd');
            switch (dayOfTheWeekName) {
                case "lunedì":
                case "martedì":
                case "mercoledì":
                case "giovedì":
                case "venerdì":
                    return <>
                        <Header className="site-layout-background" style={{paddingLeft: 24, margin: 0, height: 24}}>
                            <Title level={5}> {returnTitleOfTheCalendar()} </Title>
                        </Header>
                        <Divider/>
                        <CalendarCreatorConfigurator dateSelected={date} dayNameSelected={dayOfTheWeekName}/>
                    </>

                default:
                    return <>
                        <Space size={"middle"}>
                            <Text strong type="danger">Non è possibile creare un calendario per un giorno
                                festivo</Text>
                            <Button onClick={() => setCreateClicked(false)}>
                                Torna indietro
                            </Button>
                        </Space>
                    </>
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