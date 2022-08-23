import {Button, Col, Divider, Drawer, Form, Input, Row, message} from "antd";
import React from "react";
import {saveSubjectByName} from "../client";

function SubjectDrawerForm({showDrawer, setShowDrawer, fetchSubjects}) {
    const onCLose = () => setShowDrawer(false);

    const onFinish = values => {
        console.log(values)
        //TUTTO IN MAIUSCOLO LATO BACK
        saveSubjectByName(values)
            .then(() => {
                    onCLose();
                    fetchSubjects();
                    message.info("Materia " + values.name + " inserita con successo");
                }
            );
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };


    return <Drawer
        title="Aggiungi una nuova materia"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
        >

            <Row gutter={16}>
                <Form.Item
                    name="name"
                    label="Nome della materia"
                    rules={[{required: true, message: 'Insersci il nome della nuova materia'}]}
                >
                    <Input placeholder="Insersci il nome della nuova materia"/>
                </Form.Item>
            </Row>

            <Row>

                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Inserisci
                    </Button>
                </Form.Item>
            </Row>
        </Form>
    </Drawer>
}

export default SubjectDrawerForm;