import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Checkbox, Divider, Form, Input} from 'antd';
import React, {useState} from 'react';
import "./Login.css"
import {loginAccount} from "../client";

const Login = () => {
    const [form] = Form.useForm();
    const [submitting, setSubmitting] = useState(false);

    const onFinish = login => {
        console.log('Received values of form: ', login);
        setSubmitting(true);
        console.log(JSON.stringify(login, null, 2));
        loginAccount(login);
    };

    return (
        <>
            <h1>Benvenuto :)</h1>
            <Divider/>
            <h2>Da qui puoi accedere ai servizi informatici erogati dalla Scuola Regina Margherita.</h2>
            <p>Inserisci le tue credenziali per inviare un feedback, altrimenti puoi registrati e riceverai il tuo
                calendario
                giornaliero direttamente sulla tua email.</p>
            <Divider/>
            <Form
                name="normal_login"
                className="login-form"
                initialValues={{
                    remember: true,
                }}
                onFinish={onFinish}
            >


                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci il tuo username',
                        },
                    ]}
                >
                    <Input
                        type="text"
                        className="form-control"
                        prefix={<UserOutlined className="site-form-item-icon"/>}
                        placeholder="Username"
                    />
                </Form.Item>


                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci la password',
                        },
                    ]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon"/>}
                        type="password"
                        placeholder="Password"
                    />
                </Form.Item>
                <Form.Item>
                    <a className="login-form-forgot" href="/recuperaPassword">
                        Ho dimenticato la password
                    </a>
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
                    </Button>
                    Oppure <a href="/registrati">registrati qui</a>
                </Form.Item>
            </Form>
        </>
    );
};

export default Login;