import {useDispatch, useSelector} from "react-redux";
import {
    createCalendar,
    createJsonArrayForColumn,
    fetchSchedulableStudentByDayName, getRemainingTeacherToSchedule
} from "../redux/redux";
import React, {useEffect, useState} from "react";
import {freshPendingRow, setStudentList, setTeacherList} from "../redux/slice/calendarCreator";
import {Button, Table} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import {AddRowModal} from "./AddRowModal";
import {RemoveButton} from "./RemoveButton";

export function CalendarCreator() {
    // const teacherList = useSelector((state) => state.creation.teacherList);
    // const studentList = useSelector((state) => state.creation.studentList);

    const date = useSelector((state) => state.configuration.date);
    const dayName = useSelector((state) => state.configuration.dayName);
    const beginTime = useSelector((state) => state.configuration.beginTime);
    const endTime = useSelector((state) => state.configuration.endTime);
    const timeSlotDimension = useSelector((state) => state.configuration.timeSlotDimension);
    const rowsDataArray = useSelector((state) => state.creation.rowsDataArray);

    const [showModal, setShowModal] = useState(false);

    const dispatch = useDispatch();

    const [columns, setColumns] = useState([]);

    useEffect(() => {
        createCalendar(date, dayName, beginTime, endTime, timeSlotDimension)
            .then(() => {
                createColumnArray();
                fetchSchedulableTeacher();
                fetchSchedulableStudent();
            });
    }, []);

    const fetchSchedulableTeacher = () => {
        getRemainingTeacherToSchedule()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                dispatch(setTeacherList(data));
            });
    }

    const fetchSchedulableStudent = () => {
        fetchSchedulableStudentByDayName(dayName)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                dispatch(setStudentList(data));
            });
    }


    // Column creation
    const createColumnArray = () => {
        createJsonArrayForColumn()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                data.push({
                    title: 'Rimuovi riga',
                    dataIndex: 'rimuovi',
                    key: 'rimuovi',
                    width: 120,
                    fixed: 'right',
                    render: (_, record) => {
                        return <RemoveButton record={record}/>
                    }
                });
                setColumns(data);
            });
    }


    return (
        <>
            {/*<Space>*/}
            {/*    <Button onClick={() => {*/}
            {/*        sendConfigurationToBackend();*/}
            {/*    }}> send configuration to backEnd (fresh backend) </Button>*/}
            {/*    <Button onClick={() => {*/}
            {/*        fetchSchedulableTeacher();*/}
            {/*    }}> fetchSchedulableTeacher (refresh backend) </Button>*/}
            {/*    <Button disabled={true} onClick={() => {*/}
            {/*        fetchSchedulableStudent();*/}
            {/*    }}> fetchSchedulableStudent (refresh backend) </Button>*/}
            {/*    <Button onClick={() => {*/}
            {/*        createColumnArray();*/}
            {/*    }}> createColumnArray (refresh backend) </Button>*/}
            {/*    <Button onClick={() => {*/}
            {/*        console.log(rowsDataArray);*/}
            {/*    }}> log rowsDataArray </Button>*/}
            {/*</Space>*/}

            <AddRowModal showModal={showModal} setShowModal={setShowModal}/>
            <Table columns={columns} dataSource={
                rowsDataArray
            } scroll={{x: 1300}} bordered
                   title={() => <Button type="primary" shape="round" icon={<PlusOutlined/>}
                                        onClick={() => {
                                            setShowModal(true);
                                            dispatch(freshPendingRow());
                                        }}>
                       Aggiungi una riga
                   </Button>
                   }></Table>
        </>
    );
}