import {Button, Col, Divider, Row, Space, Table, Typography} from "antd";
import React, {useEffect} from "react";
import {PlusOutlined} from "@ant-design/icons";
import AddNewStudentDrawerForm from "./studentManagementTable/AddNewStudentDrawerForm";
import SetFiscalCodeForm from "./studentManagementTable/studentManagementTableRow/SetFiscalCodeForm";
import SetEmailAddressForm from "./studentManagementTable/studentManagementTableRow/SetEmailAddressForm";
import SetStudentIsPresentButton from "./studentManagementTable/studentManagementTableRow/SetStudentIsPresentButton";
import SetStudentAttendanceDaysCheckBox
    from "./studentManagementTable/studentManagementTableRow/SetStudentAttendanceDaysCheckBox";
import DeleteStudentButton from "./studentManagementTable/studentManagementTableRow/DeleteStudentButton";
import SetSubjectFollowedTag from "./studentManagementTable/studentManagementTableRow/SetSubjectFollowedTag";

const {Text, Title} = Typography;

const columns = [
    {
        title: 'Nome',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Cognome',
        dataIndex: 'surname',
        key: 'surname',
    },
    {
        title: 'Presente',
        dataIndex: 'present',
        key: 'present',
        render: (boolean) => {
            if (boolean) return "Sì";
            else return "No";
        },
    }
];

const StudentManagementTable = ({studentList, showDrawer, setShowDrawer, fetchStudents}) => {

    useEffect(() => {
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
                                        <SetSubjectFollowedTag student={student} fetchStudents={fetchStudents}/>
                                    </Space>
                                </Col>

                                <Col span={12}>
                                    <Title level={5}>Dati generici</Title>
                                    <Space direction="vertical" style={{paddingLeft: 10}}>
                                        <Text>Nome: {student["name"]} </Text>
                                        <Text>Cognome: {student["surname"]} </Text>
                                        <SetFiscalCodeForm student={student} fetchStudents={fetchStudents}/>
                                        <SetEmailAddressForm student={student} fetchStudents={fetchStudents}/>
                                    </Space>
                                </Col>
                            </Row>

                            <Row>
                                <Divider/>
                                <DeleteStudentButton student={student} fetchStudents={fetchStudents}/>
                            </Row>
                        </>
                    ),
                }}
                columns={columns}
                dataSource={studentList}
                rowKey={student => student.id}
                bordered
                pagination={false}/>
        </>
    );
}

export default StudentManagementTable;