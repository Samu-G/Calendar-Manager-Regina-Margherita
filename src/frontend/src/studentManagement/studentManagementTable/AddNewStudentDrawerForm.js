import {Button, Col, Divider, Drawer, Form, Input, message, Row} from "antd";
import React from "react";
import {addNewStudent} from "../../client";


const AddNewStudentDrawerForm = ({showDrawer, setShowDrawer, fetchStudents}) => {
    const onCLose = () => setShowDrawer(false);

    const onFinish = (aNewStudent) => {
        if (aNewStudent["fiscalCode"] === undefined || aNewStudent["fiscalCode"] === "")
            aNewStudent["fiscalCode"] = "";
        if (aNewStudent["emailAddress"] === undefined || aNewStudent["emailAddress"] === "")
            aNewStudent["emailAddress"] = "";

        addNewStudent(aNewStudent)
            .then(() => {
                fetchStudents();
                setShowDrawer(false);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    }

    return (
        <Drawer
            visible={showDrawer} onClose={onCLose}
            title="Aggiungi un nuovo studente" width={720} bodyStyle={{paddingTop: 0}}
            footer={<Button danger onClick={() => setShowDrawer(false)} style={{marginRight: 8}}> ANNULLA </Button>}
        >
            <Form layout="vertical" onFinish={onFinish}>
                <Row gutter={16}>
                    <Divider orientation="left" orientationMargin="0">Dati obbligatori:</Divider>

                    <Col span={12}>
                        <Form.Item name="name" label="Nome"
                                   rules={[{
                                       required: true,
                                       type: "string",
                                       message: 'Insersci il nome dello studente'
                                   }]}>
                            <Input placeholder="Nome dello studente"/>
                        </Form.Item>
                    </Col>

                    <Col span={12}>
                        <Form.Item name="surname" label="Cognome"
                                   rules={[{
                                       required: true,
                                       type: "string",
                                       message: 'Insersci il cognome dello studente'
                                   }]}>
                            <Input placeholder="Cognome dello studente"/>
                        </Form.Item>
                    </Col>
                </Row>

                <Row gutter={16}>
                    <Divider orientation="left" orientationMargin="0">Dati opzionali:</Divider>

                    <Col span={12}>
                        <Form.Item name="fiscalCode" label="Codice fiscale" rules={[{required: false, type: "string"}]}>
                            <Input placeholder="Codice fiscale dello studente"/>
                        </Form.Item>
                    </Col>

                    <Col span={12}>
                        <Form.Item name="emailAddress" label="Indirizzo email" rules={[{
                            required: false,
                            type: "email",
                            message: "L'indirizzo email inserito non è valido"
                        }]}>
                            <Input placeholder="Indirizzo email dello studente"/>
                        </Form.Item>
                    </Col>
                </Row>

                <Row style={{paddingTop: 20}}>
                    <Form.Item>
                        <Button type="primary" htmlType="submit" shape="round">
                            Aggiungi un nuovo studente
                        </Button>
                    </Form.Item>
                </Row>
            </Form>
        </Drawer>
    );
}

export default AddNewStudentDrawerForm;