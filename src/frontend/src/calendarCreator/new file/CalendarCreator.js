import {useDispatch, useSelector} from "react-redux";
import {
    createColumnArrayByBeginTimeEndTimeDuration,
    fetchSchedulableStudentByDayName, fetchSchedulableTeacherInCalendar
} from "../../redux/redux";
import React, {useState} from "react";
import {freshPendingRow, setStudentList, setTeacherList} from "../../redux/slice/calendarCreator";
import {Button, Space, Table} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import {AddRowModal} from "./AddRowModal";

export function CalendarCreator() {
    // const teacherList = useSelector((state) => state.creation.teacherList);
    // const studentList = useSelector((state) => state.creation.studentList);
    const dayName = useSelector((state) => state.configuration.dayName);
    const beginTime = useSelector((state) => state.configuration.beginTime);
    const endTime = useSelector((state) => state.configuration.endTime);
    const timeSlotDimension = useSelector((state) => state.configuration.timeSlotDimension);
    const rowsDataArray = useSelector((state) => state.creation.rowsDataArray);
    const [showModal, setShowModal] = useState(false);

    const dispatch = useDispatch();

    const [columns, setColumns] = useState([]);

    const fetchSchedulableTeacher = () => {
        // fetchSchedulableTeacherByDayName(dayName)
        //     .then(res => res.json())
        //     .then(data => {
        //         console.log(data);
        //         dispatch(setTeacherList(data));
        //     });

        fetchSchedulableTeacherInCalendar()
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

    const createColumnArray = () => {
        createColumnArrayByBeginTimeEndTimeDuration(dayName, beginTime, endTime, timeSlotDimension)
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
                        return <Button type="primary" danger> Rimuovi </Button>

                    }
                });
                setColumns(data);
            });
    }

    return (
        <>
            <Space>
                <Button onClick={() => {
                    fetchSchedulableTeacher()
                }}> fetchSchedulableTeacher </Button>
                <Button onClick={() => {
                    fetchSchedulableStudent()
                }}> fetchSchedulableStudent </Button>
                <Button onClick={() => {
                    createColumnArray()
                }}> createColumnArray </Button>
            </Space>
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