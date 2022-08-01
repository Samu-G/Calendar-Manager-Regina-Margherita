import {
    Button,
    Divider,
    Form,
    Input
} from 'antd';
import "./Registration.css"

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

const Registration = () => {
    const [form] = Form.useForm();

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    return (
        <>
            <h1>Registrati</h1>
            <Divider/>
            <Form
                {...formItemLayout}
                form={form}
                name="register"
                onFinish={onFinish}
                initialValues={{
                    residence: ['zhejiang', 'hangzhou', 'xihu'],
                    prefix: '86',
                }}
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
                    rules={[
                        {
                            type: 'email',
                            message: 'The input is not valid E-mail!',
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
                    name="user"
                    label="password"
                    tooltip="ti servirà per effettuare il login ed inviare i tuoi feedback"
                    rules={[
                        {
                            required: true,
                            message: 'attenzione, ti sei dimenticato di inserire la password'
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
            </Form>
        </>
    );
};

export default Registration;
