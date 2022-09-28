import React, {useEffect, useState} from "react";
import {Button, Col, Divider, Row, Space, Table, Tag, Typography} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import AddNewTeacherDrawerForm from "./teacherManagementTable/AddNewTeacherDrawerForm";
import SetTeacherEmailAddressForm from "./teacherManagementTable/teacherManagementTableRow/SetTeacherEmailAddressForm";
import SetTeacherIsActiveButton from "./teacherManagementTable/teacherManagementTableRow/SetTeacherIsActiveButton";
import SetTeacherAttendanceDaysCheckBox
    from "./teacherManagementTable/teacherManagementTableRow/SetTeacherAttendanceDaysCheckBox";
import SetTeacherSubjectTag from "./teacherManagementTable/teacherManagementTableRow/SetTeacherSubjectTag";
import DeleteTeacherButton from "./teacherManagementTable/teacherManagementTableRow/DeleteTeacherButton";
import SetTeacherTimeSlotsModal from "./SetTeacherTimeSlotsModal";

const {Text, Title} = Typography;

const columns = [
    {
        title: 'Nome',
        dataIndex: 'name',
        key: 'name',
        render: (name) => {
            return <Text> {name} </Text>
        },
        width: "20%",
    },
    {
        title: 'Cognome',
        dataIndex: 'surname',
        key: 'surname',
        render: (surname) => {
            return <Text> {surname} </Text>
        },
        width: "20%",
    },
    {
        title: 'Attivo in calendario',
        dataIndex: 'active',
        key: 'active',
        render: (boolean) => {
            if (boolean) return <Text strong type="success"> Sì </Text>;
            else return  <Text strong type="danger"> No </Text>;
        },
        width: "9%",
    },
    {
        title: 'Materie insegnate',
        dataIndex: 'subjectsTeached',
        key: 'subjectsTeached',
        render: (subjectsTeached) => {
            let subjectName = [];
            if (subjectsTeached.length === 0) return <Text type="secondary">Nessuna materia aggiunta</Text>
            subjectsTeached.forEach((subject) => {
                subjectName.push(<Tag style={{fontSize: 14}}> {subject["nameOfTheSubject"]} </Tag>);
            })
            return subjectName;
        },
    },
    {
        title: "Indirizzo email",
        dataIndex: "emailAddress",
        key: "emailAddress",
        width: "20%",
    }
];

const TeacherManagementTable = ({teacherList, fetchTeachers}) => {
    const [showDrawer, setShowDrawer] = useState(false);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [currentTeacher, setCurrentTeacher] = useState([]);


    useEffect(() => {
        console.log(teacherList)
        console.log("TeacherManagementTable mounted.");
    }, [teacherList]);

    return (
        <>
            <SetTeacherTimeSlotsModal isModalVisible={isModalVisible}
                                      setIsModalVisible={setIsModalVisible}
                                      currentTeacher={currentTeacher}
            />
            <AddNewTeacherDrawerForm showDrawer={showDrawer} setShowDrawer={setShowDrawer}
                                     fetchTeachers={fetchTeachers}/>
            <Table
                title={() =>
                    <Button onClick={() => setShowDrawer(!showDrawer)} type="primary" shape="round"
                            icon={<PlusOutlined/>} size="small"> Aggiungi un nuovo docente </Button>
                }
                expandable={{
                    expandedRowRender: (teacher) => (
                        <>
                            <Row>
                                <Col span={12}>
                                    <Title level={5}>Dati sulla presenza</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <SetTeacherIsActiveButton teacher={teacher} fetchTeachers={fetchTeachers}/>
                                        <SetTeacherAttendanceDaysCheckBox teacher={teacher}
                                                                          fetchTeachers={fetchTeachers}/>
                                        <Button type="primary" shape={"round"}
                                                onClick={() => {
                                                    setCurrentTeacher(teacher);
                                                    setIsModalVisible(true);
                                                }}
                                                style={{marginTop: 8}}>
                                            Consulta fasce orarie di disponibilità</Button>
                                    </Space>
                                    <Title level={5} style={{paddingTop: 16}}>Materie insegnate</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <SetTeacherSubjectTag teacher={teacher} fetchTeachers={fetchTeachers}/>
                                    </Space>
                                </Col>

                                <Col span={12}>
                                    <Title level={5}>Dati generici</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <Text>Nome: {teacher["name"]} </Text>
                                        <Text>Cognome: {teacher["surname"]} </Text>
                                        <SetTeacherEmailAddressForm teacher={teacher} fetchTeachers={fetchTeachers}/>
                                    </Space>
                                </Col>
                            </Row>

                            <Row>
                                <Divider/>
                                <DeleteTeacherButton teacher={teacher} fetchTeachers={fetchTeachers}/>
                            </Row>
                        </>
                    ),
                }}
                columns={columns}
                dataSource={teacherList}
                rowKey={teacher => teacher.id}
                bordered
                size={"small"}
                pagination={{pageSize: 30}}
            />
        </>
    );
}

export default TeacherManagementTable;