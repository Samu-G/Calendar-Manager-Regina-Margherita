import {Button, Divider, Dropdown, Modal, Space, message, Menu, Table, Typography, List} from 'antd';
import React, {useEffect, useState} from 'react';
import {DownOutlined} from "@ant-design/icons";
import CalendarCreatorAddStudentsToTimeSlot from "./CalendarCreatorAddStudentsToTimeSlot";

const {Title} = Typography;

const CalendarCreatorAddRowModal = ({
                                        isModalVisible,
                                        setIsModalVisible,
                                        dayName,
                                        teachersToBeScheduled,
                                        setTeachersToBeScheduled,
                                        studentsToBeScheduled,
                                        setStudentsToBeScheduled
                                    }) => {
    const [teacherSelected, setTeacherSelected] = useState([]);
    const [isTeacherSelected, setIsTeacherSelected] = useState(false);

    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = () => {
        setTeacherSelected([]);
        setIsTeacherSelected(false);
        setIsModalVisible(false);
    };

    const handleCancel = () => {
        setTeacherSelected([]);
        setIsTeacherSelected(false);
        setIsModalVisible(false);
    };

    useEffect(() => {
            console.log("CalendarCreatorAddRowModal mounted");
        }
        , []);


    const handleMenuClick = (e) => {
        message.info('Click on menu item.');
        console.log('click', e);
        setTeacherSelected(teachersToBeScheduled[e.key - 1]);
        setIsTeacherSelected(true);
        console.log(teachersToBeScheduled[e.key - 1]);
    };

    function createItemListOfTeachers() {
        let itemListOfTeachers = [];
        let key = 1;
        teachersToBeScheduled.forEach((teacher) => {
            itemListOfTeachers.push({
                label: teacher["name"] + " " + teacher["surname"],
                key: key,
            });
            key = key + 1;
        })
        return itemListOfTeachers;
    }

    const menu = (
        <Menu
            onClick={handleMenuClick}
            items={createItemListOfTeachers()}
        />
    );

    function renderTimeSlotsOfPresenceForTeacher() {
        let timeSlot = [];
        if (dayName === "lunedì") {
            timeSlot = teacherSelected["timeSlotsOfPresenceOnMonday"];
        } else if (dayName === "martedì") {
            timeSlot = teacherSelected["timeSlotsOfPresenceOnTuesday"];
        } else if (dayName === "mercoledì") {
            timeSlot = teacherSelected["timeSlotsOfPresenceOnWednesday"];
        } else if (dayName === "giovedì") {
            timeSlot = teacherSelected["timeSlotsOfPresenceOnThursday"];
        } else if (dayName === "venerdì") {
            timeSlot = teacherSelected["timeSlotsOfPresenceOnFriday"];
        }
        return timeSlot;
    }

    function renderCore() {
        if (!isTeacherSelected) {
            return (
                <>
                    <Divider orientation="left" orientationMargin="0" style={{padding: 5, margin: 5}}>Seleziona il
                        docente </Divider>
                    <Space style={{marginLeft: 20}}>
                        <Dropdown overlay={menu}>
                            <Button>
                                Scegli il docente...
                                <DownOutlined/>
                            </Button>
                        </Dropdown>
                    </Space>

                </>
            );
        } else {
            let timeSlot = renderTimeSlotsOfPresenceForTeacher();

            let subjectTeachedByName = [];
            teacherSelected["subjectsTeached"].forEach((subject) => {
                subjectTeachedByName.push(subject["nameOfTheSubject"]);
            });

            const studentsWitchFollowSubjectTeached = [];
            for (let i = 0; i < studentsToBeScheduled.length; i++) {
                for (let j = 0; j < studentsToBeScheduled[i]["subjectsFollowed"].length; j++) {
                    if (subjectTeachedByName.includes(studentsToBeScheduled[i]["subjectsFollowed"][j]["nameOfTheSubject"])) {
                        studentsWitchFollowSubjectTeached.push(studentsToBeScheduled[i]);
                        break;
                    }
                }
            }

            console.log(studentsWitchFollowSubjectTeached);

            let data = [];

            let key = 1;
            timeSlot.forEach((timeSlot) => {

                data.push({
                    timeSlotName: timeSlot["timeSlotName"],
                    timeSlot: timeSlot["beginTime"] + " - " + timeSlot["endTime"],
                    students: timeSlot["timeSlotName"],
                    key: key,
                })

                key = key + 1;
            });

            const columns = [
                {
                    title: 'Fascia oraria',
                    dataIndex: 'timeSlot',
                    key: 'timeSlot',
                },
                {
                    title: 'Studenti',
                    dataIndex: 'students',
                    key: 'students',
                    width: 500,
                    render: (_, record) => {
                        return <CalendarCreatorAddStudentsToTimeSlot
                            studentsWitchFollowSubjectTeached={studentsWitchFollowSubjectTeached}
                        />
                    }
                }
            ];


            return (
                <>
                    <Divider orientation="left" orientationMargin="0" style={{padding: 5, margin: 5}}>Orario
                        per <b> {teacherSelected["name"]} {teacherSelected["surname"]}</b> </Divider>

                    <Title level={5}>Materie insegnate: {subjectTeachedByName} </Title>
                    <Divider/>
                    <Table columns={columns} dataSource={data} pagination={false}/>
                </>
            );
        }

    }

    return (
        <>
            <Modal title="Crea orario per un docente" visible={isModalVisible} onOk={handleOk}
                   okText="Aggiungi riga al calendario"
                   onCancel={handleCancel} cancelText="Annulla"
                   bodyStyle={{padding: 15, paddingTop: 5}} style={{minWidth: 700}}>

                {renderCore()}

            </Modal>
        </>
    );
};

export default CalendarCreatorAddRowModal;