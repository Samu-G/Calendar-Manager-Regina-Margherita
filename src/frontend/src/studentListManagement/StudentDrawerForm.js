import {
    Drawer,
    Input,
    Col,
    Select,
    Form,
    Row,
    Button,
    Divider,
    message,
    Menu,
    Dropdown,
    Checkbox,
    List,
    AutoComplete, Space, Typography
} from 'antd';
import React, {useState} from "react";
import {
    addNewStudent,
    addSubjectToStudent,
    getAllSubjects,
    setCurrentYearToStudent,
    setDayOfPresentToStudent
} from "../client";
import {DownOutlined} from "@ant-design/icons";

const {Option} = Select;


function StudentDrawerForm({showDrawer, setShowDrawer, fetchStudents}) {
    const onCLose = () => setShowDrawer(false);

    const onFinish = student => {
        student.currentYear = "1"
        student.isPresent = "Si"
        student.lun = "Si"
        student.mar = "Si"
        student.mer = "Si"
        student.gio = "Si"
        student.ven = "Si"
        if(student.fiscalCode === undefined) {
            student.fiscalCode = "non ancora inserito"
        }


        console.log(student)


        addNewStudent(student)
            .then(() => {
                fetchStudents();
                console.log("student added succesfuly");
                message.success(student.name + " " + student.surname + " è stato aggiunto");
                setShowDrawer(false);
            })
            .catch((err) => {
                console.log("UN ERRORE" + err);
                message.error("Errore nella aggiunta di un nuovo studente. Controllare lo stato del server");
            })
    };

    const onFinishFailed = errorInfo => {

    };

    const onClick = event => {
        console.log(event)
    }

    return <Drawer
        title="Aggiungi un nuovo studente"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    ANNULLA
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Divider orientation="left" orientationMargin="0">Dati generici:</Divider>

                {/*NOME*/}
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="* Nome"
                        rules={[{required: true, message: 'Insersci il nome dello studente'}]}
                    >
                        <Input placeholder="Insersci il nome dello studente"/>
                    </Form.Item>
                </Col>

                {/*COGNOME*/}
                <Col span={12}>
                    <Form.Item
                        name="surname"
                        label="* Cognome"
                        rules={[{required: true, message: 'Insersci il cognome dello studente'}]}
                    >
                        <Input placeholder="Insersci il cognome dello studente"/>
                    </Form.Item>
                </Col>
            </Row>

            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="fiscalCode"
                        label="Codice fiscale"
                        rules={[{required: false}]}
                    >
                        <Input placeholder="Insersci il codice fiscale dello studente"/>
                    </Form.Item>
                </Col>
            </Row>

            <Row>
                <Col span={12}>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            AGGIUNGI STUDENTE
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
        </Form>
    </Drawer>
}

export default StudentDrawerForm;