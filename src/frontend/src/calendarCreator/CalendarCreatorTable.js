import {Button, Descriptions, Divider, Table, Typography} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import * as PropTypes from "prop-types";
import {Content} from "antd/es/layout/layout";
import moment from "moment/moment";
import CalendarCreatorAddRowModal from "./CalendarCreatorAddRowModal";


const {Title} = Typography;

Title.propTypes = {
    level: PropTypes.number,
    children: PropTypes.node
};
const CalendarCreatorTable = ({date, teachers, students}) => {
    const [listOfTeachers, setListOfTeachers] = useState([]);
    const [listOfStudents, setListOfStudents] = useState([]);
    const [teachersToBeScheduled, setTeachersToBeScheduled] = useState([]);
    const [studentsToBeScheduled, setStudentsToBeScheduled] = useState([]);
    const [isModalVisible, setIsModalVisible] = useState(false);

    // const [calendarDate, setCalendarDate] = useState([]);

    let dayOfTheWeekName = "";

    const columns = [
        {
            title: 'Docente',
            dataIndex: 'docente',
            key: 'docente',
            width: 200,
            fixed: 'left',
        },
        {
            title: '8:30 - 9:00',
            dataIndex: '830_900',
            key: '8300_9000',
            width: 150,
        },
        {
            title: '9:00 - 9:30',
            dataIndex: '9000_9300',
            key: '9000_9300',
            width: 150,
        },
        {
            title: '9:30 - 10:00',
            dataIndex: '9000_1000',
            key: '9000_1000',
            width: 150,
        },
        {
            title: '10:00 - 10:30',
            dataIndex: '1000_1030',
            key: '1000_1030',
            width: 150,
        },
        {
            title: '10:30 - 11:00',
            dataIndex: '1030_1100',
            key: '1030_1100',
            width: 150,
        },
        {
            title: '11:00 - 11:30',
            dataIndex: '1100_1130',
            key: '1100_1130',
            width: 150,
        },
        {
            title: '11:30 - 12:00',
            dataIndex: '1130_1200',
            key: '1130_1200',
            width: 150,
        },
        {
            title: '12:00 - 12:30',
            dataIndex: '1200_1230',
            key: '1200_1230',
            width: 150,
        },
        {
            title: '12:30 - 13:00',
            dataIndex: '1230_1300',
            key: '1230_1300',
            width: 150,
        },
        {
            title: '13:00 - 13:30',
            dataIndex: '1300_1330',
            key: '1300_1330',
            width: 150,
        },
        {
            title: '13:30 - 14:00',
            dataIndex: '1300_1330',
            key: '1300_1330',
            width: 150,
        },
        {
            title: '14:00 - 14:30',
            dataIndex: '1400_1430',
            key: '1400_1430',
            width: 150,
        },
        {
            title: '14:30 - 15:00',
            dataIndex: '1430_1500',
            key: '1430_1500',
            width: 150,
        },
        {
            title: '15:00 - 15:30',
            dataIndex: '1500_1530',
            key: '1500_1530',
            width: 150,
        },
        {
            title: '15:30 - 16:00',
            dataIndex: '1530_1600',
            key: '1530_1600',
            width: 150,
        },
        {
            title: '16:00 - 16:30',
            dataIndex: '1600_1630',
            key: '1600_1630',
            width: 150,
        },
        {
            title: 'Rimuovi riga',
            dataIndex: 'rimuovi',
            key: 'rimuovi',
            width: 120,
            fixed: 'right',
            render: (_, record) => {
                return <Button type="primary" danger> Rimuovi </Button>

            }
        },
    ];
    const data = [
        {
            key: '1',
            name: 'John Brown',
            age: 32,
            address: 'New York No. 1 Lake Park',
            tags: ['nice', 'developer'],
        },
    ];

    let titleOfTheCalendar = "";
    const calendar = [];

    useEffect(() => {
        console.log("Calendar Creator Table");
        setCalendarDate(date);
        setListOfTeachers(teachers);
        setListOfStudents(students);
        setTeachersToBeScheduled(teachers);
        setStudentsToBeScheduled(students);
        dayOfTheWeekName = moment(date, 'YYYY-MM-DD').format('dddd');
    }, []);

    // function normalizeDateForItaly() {
    //     let data = moment(date, 'YYYY-MM-DD');
    //     return data.format('dddd') + " " + data.format('LL');
    // }

    // function returnTitleOfTheCalendar() {
    //     titleOfTheCalendar = "Piano di lavoro giornaliero per " + normalizeDateForItaly();
    //     calendar.push({title: titleOfTheCalendar});
    //     console.log(calendar);
    //     return titleOfTheCalendar;
    // }


    return <>
        <CalendarCreatorAddRowModal
            isModalVisible={isModalVisible}
            setIsModalVisible={setIsModalVisible}
            dayName={moment(date, 'YYYY-MM-DD').format('dddd')}
            teachersToBeScheduled={teachersToBeScheduled}
            setTeachersToBeScheduled={setTeachersToBeScheduled}
            studentsToBeScheduled={studentsToBeScheduled}
            setStudentsToBeScheduled={setStudentsToBeScheduled}
        />
            <Descriptions size="medium" column={2}>
                <p>Docenti presenti oggi: {listOfTeachers.length}</p>
                <p>Studenti presenti oggi: {listOfStudents.length}</p>
                <p>Docenti da calendarizzare rimanenti: {teachersToBeScheduled.length}</p>
                <p>Studenti da calendarizzare rimanenti: {studentsToBeScheduled.length}</p>
            </Descriptions>
        <Content>
            <Divider orientation="left" orientationMargin="0">Anteprima del calendario</Divider>
            <Table columns={columns} dataSource={data} scroll={{x: 1300}} bordered
                   title={() => <Button type="primary" shape="round" icon={<PlusOutlined/>} size="small"
                                        onClick={() => setIsModalVisible(true)}> Aggiungi una riga </Button>
                   }
            />
        </Content>
    </>
}


export default CalendarCreatorTable;