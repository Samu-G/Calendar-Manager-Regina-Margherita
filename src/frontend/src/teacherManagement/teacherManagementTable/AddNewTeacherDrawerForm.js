import {Button, Col, Divider, Drawer, Form, Input, message, Row, Typography} from "antd";
import React from "react";
import {addNewTeacher} from "../../client";


const {Text} = Typography;

const AddNewTeacherDrawerForm = ({showDrawer, setShowDrawer, fetchTeachers}) => {
    const onCLose = () => setShowDrawer(false);

    const onFinish = (aNewTeacher) => {
        if (aNewTeacher["emailAddress"] === undefined || aNewTeacher["emailAddress"] === "")
            aNewTeacher["emailAddress"] = "";

        addNewTeacher(aNewTeacher)
            .then(() => {
                fetchTeachers();
                setShowDrawer(false);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    }

    return (
        <Drawer
            visible={showDrawer} onClose={onCLose}
            title="Aggiungi un nuovo docente" width={720} bodyStyle={{paddingTop: 0}}
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
                                       message: "Insersci il nome del docente"
                                   }]}>
                            <Input placeholder="Nome del docente"/>
                        </Form.Item>
                    </Col>

                    <Col span={12}>
                        <Form.Item name="surname" label="Cognome"
                                   rules={[{
                                       required: true,
                                       type: "string",
                                       message: "Insersci il cognome del docente"
                                   }]}>
                            <Input placeholder="Cognome del docente"/>
                        </Form.Item>
                    </Col>
                </Row>

                <Row gutter={16}>
                    <Divider orientation="left" orientationMargin="0">Dati opzionali:</Divider>
                    <Col span={12}>
                        <Form.Item name="emailAddress" label="Indirizzo email" rules={[{
                            required: false,
                            type: "email",
                            message: "L'indirizzo email inserito non è valido"
                        }]}>
                            <Input placeholder="Indirizzo email del docente"/>
                        </Form.Item>
                    </Col>
                </Row>

                <Text type="secondary">Sarà possibile inserire le materie insegnate una volta aggiunto il nuovo docente
                    direttamente dalla tabella di questa pagina.</Text>

                <Row style={{paddingTop: 20}}>
                    <Form.Item>
                        <Button type="primary" htmlType="submit" shape="round">
                            Aggiungi un nuovo docente
                        </Button>
                    </Form.Item>
                </Row>
            </Form>
        </Drawer>
    );
}

export default AddNewTeacherDrawerForm;