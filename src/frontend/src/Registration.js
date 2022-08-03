import {
    Button,
    Divider,
    Form,
    Input, Row, Spin
} from 'antd';
import "./Registration.css"
import React, {useState} from "react";
import {addNewAccount, setRoleToNewAccount} from "./client";
import {errorNotification, successNotification} from "./Notification";
import {LoadingOutlined} from "@ant-design/icons";

const formItemLayout = {
    labelCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 8,
        },
    },
    wrapperCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 16,
        },
    },
};

const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

const Registration = () => {
    const [form] = Form.useForm();
    const [submitting, setSubmitting] = useState(false);

    const onFinish = account => {
        setSubmitting(true);
        console.log(JSON.stringify(account, null, 2));
        addNewAccount(account)
            .then(() => {

                console.log("account added");
                successNotification(
                    "Ti sei registrato con successo!",
                    `${account.name} benvenuto, ora potrai accedere al servizio come ${account.role}`
                )
            }).catch(err => {
            console.log(err);
            errorNotification(
                "Errore",
                `Caro ${account.name}, c'è stato un errore durante la fase di registrazione. Conttata l'amministratrice`
            )
        }).finally(() => {
            setSubmitting(false);
        })
        console.log('Received values of form: ', account);
    };

    return (
        <>
            <h1>Registrati</h1>
            <Divider/>
            <p>Per potersi registrare, è necessario che la coordinatrice abbia inserito il tuo nome e cognome
                all'interno della lista degli studenti o docenti.</p>
            <p>Qualora riscontrassi delle anomalie a registrarti, contatta la
                amministratrice che saprà aiutarti per utilizzare il servizio.</p>
            <Divider/>
            <Form
                {...formItemLayout}
                form={form}
                name="register"
                onFinish={onFinish}
                scrollToFirstError
            >
                <Form.Item
                    name="name"
                    label="nome"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci il tuo nome',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="surname"
                    label="cognome"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci il tuo cognome',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="email"
                    label="email"
                    tooltip="a questo indirizzo email riceverai ogni giorno il tuo calendario"
                    rules={[
                        {
                            type: 'email',
                            message: 'La email inserita non è valida',
                        },
                        {
                            required: true,
                            message: 'inserisci il tuo indirizzo email',
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="username"
                    label="username"
                    rules={[
                        {
                            type: 'string',
                            message: 'C`è qualcosa che non va',
                        },
                        {
                            required: true,
                            message: 'inserisci un username',
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>


                <Form.Item
                    name="password"
                    label="password"
                    tooltip="ti servirà per effettuare il login ed inviare i tuoi feedback"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci una password'
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Registrati
                    </Button>
                </Form.Item>
                <Row>
                    {submitting && <Spin indicator={antIcon}/>}
                </Row>
            </Form>
        </>
    );
};

export default Registration;
