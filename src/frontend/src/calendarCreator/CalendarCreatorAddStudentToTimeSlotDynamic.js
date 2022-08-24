import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons';
import {AutoComplete, Button, Form, Input, Space} from 'antd';
import React from 'react';

const App = ({studentsByValue}) => {

    const onFinish = (values) => {
        console.log('Received values of form:', values);
    };

    return (
        <Form name="dynamic_form_nest_item" onFinish={onFinish} autoComplete="off">
            <Form.List name="users">
                {(fields, {add, remove}) => (
                    <>
                        {fields.map(({key, name}) => (
                            <Space key={key} style={{display: 'flex', width: 450,}} align="baseline">

                                <Form.Item
                                    name={[name, 'first']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing first name',
                                        },
                                    ]}
                                    style={{width: 380}}
                                >
                                    <AutoComplete
                                        options={studentsByValue}
                                        filterOption={(inputValue, option) => option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1}
                                    >
                                    </AutoComplete>
                                </Form.Item>

                                <MinusCircleOutlined onClick={() => remove(name)}/>
                            </Space>
                        ))}
                        <Form.Item>
                            <Button type="dashed" onClick={() => {add(); onFinish()}} block icon={<PlusOutlined/>}
                                    style={{width: 400}}>
                                Aggiungi uno studente
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
};

export default App;