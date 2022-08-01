import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Checkbox, Divider, Form, Input} from 'antd';
import React from 'react';
import "./Login.css"

const Login = () => {

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    return (
        <>
            <h1>Benvenuto :)</h1>
            <Divider/>
            <p>Da qui puoi accedere ai servizi informatici erogati dalla Scuola Regina Margherita.</p>
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
                    name="email"
                    rules={[
                        {
                            required: true,
                            message: 'inserisci la tua email',
                        },
                    ]}
                >
                    <Input
                        type="text"
                        className="form-control"
                        prefix={<UserOutlined className="site-form-item-icon"/>}
                        placeholder="Email"
                    />
                </Form.Item>


                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'hai dimenticato la password',
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
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                        <Checkbox>Ricordami</Checkbox>
                    </Form.Item>

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