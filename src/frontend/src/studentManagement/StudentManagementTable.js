import {Button, Col, Divider, Row, Space, Table, Tag, Typography} from "antd";
import React, {useEffect} from "react";
import {PlusOutlined} from "@ant-design/icons";
import AddNewStudentDrawerForm from "./AddNewStudentDrawerForm";
import SetStudentFiscalCodeForm from "./SetStudentFiscalCodeForm";
import SetStudentEmailAddressForm from "./SetStudentEmailAddressForm";
import SetStudentIsPresentButton from "./SetStudentIsPresentButton";
import SetStudentAttendanceDaysCheckBox
    from "./SetStudentAttendanceDaysCheckBox";
import DeleteStudentButton from "./DeleteStudentButton";
import SetStudentSubjectFollowedTag from "./SetStudentSubjectFollowedTag";

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
        title: 'Presente in struttura',
        dataIndex: 'present',
        key: 'present',
        render: (boolean) => {
            if (boolean) return <Text strong type="success"> Sì </Text>;
            else return  <Text strong type="secondary"> No </Text>;
        },
        width: "9%",
    },
    {
        title: 'Giorni di presenza',
        dataIndex: 'daysOfPresence',
        key: 'daysOfPresence',
        render: (daysOfPresence) => {
            let dayTagName = [];
            if(daysOfPresence.length === 0) return <Text type="secondary">Nessuno</Text>;
            daysOfPresence.forEach((daysOfPresence) => {
                if(daysOfPresence["dayName"] === "Monday") dayTagName.push(<Tag style={{fontSize: 14}}> Lunedì </Tag>);
                else if(daysOfPresence["dayName"] === "Tuesday") dayTagName.push(<Tag style={{fontSize: 14}}> Martedì </Tag>);
                else if(daysOfPresence["dayName"] === "Wednesday") dayTagName.push(<Tag style={{fontSize: 14}}> Mercoledì </Tag>);
                else if(daysOfPresence["dayName"] === "Thursday") dayTagName.push(<Tag style={{fontSize: 14}}> Giovedì </Tag>);
                else if(daysOfPresence["dayName"] === "Friday") dayTagName.push(<Tag style={{fontSize: 14}}> Venerdì </Tag>);
            })
            return dayTagName;
        },
    },
    {
        title: "Indirizzo email",
        dataIndex: "emailAddress",
        key: "emailAddress",
        width: "20%",
    }
];

const StudentManagementTable = ({studentList, showDrawer, setShowDrawer, fetchStudents}) => {

    useEffect(() => {
        console.log(studentList);
        console.log("StudentManagementTable mounted.");
    }, [studentList]);

    return (
        <>
            <AddNewStudentDrawerForm showDrawer={showDrawer} setShowDrawer={setShowDrawer}
                                     fetchStudents={fetchStudents}/>
            <Table
                title={() =>
                    <Button onClick={() => setShowDrawer(!showDrawer)} type="primary" shape="round"
                            icon={<PlusOutlined/>} size="small"> Aggiungi un nuovo studente </Button>
                }
                expandable={{
                    expandedRowRender: (student) => (
                        <>
                            <Row>
                                <Col span={12}>
                                    <Title level={5}>Dati sulla presenza</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <SetStudentIsPresentButton student={student} fetchStudents={fetchStudents}/>
                                        <SetStudentAttendanceDaysCheckBox student={student}
                                                                          fetchStudents={fetchStudents}/>
                                    </Space>
                                    <Title level={5} style={{paddingTop: 16}}>Materie seguite</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <SetStudentSubjectFollowedTag student={student} fetchStudents={fetchStudents}/>
                                    </Space>
                                </Col>

                                <Col span={12}>
                                    <Title level={5}>Dati generici</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <Text>Nome: {student["name"]} </Text>
                                        <Text>Cognome: {student["surname"]} </Text>
                                        <SetStudentFiscalCodeForm student={student} fetchStudents={fetchStudents}/>
                                        <SetStudentEmailAddressForm student={student} fetchStudents={fetchStudents}/>
                                    </Space>
                                </Col>
                            </Row>

                            <Row>
                                <Divider style={{marginBottom: 8}}/>
                                <DeleteStudentButton student={student} fetchStudents={fetchStudents}/>
                            </Row>
                        </>
                    ),
                }}
                columns={columns}
                dataSource={studentList}
                rowKey={student => student.id}
                bordered
                size={"small"}
                pagination={{pageSize: 30}}
            />
        </>
    );
}

export default StudentManagementTable;