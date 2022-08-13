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
    AutoComplete
} from 'antd';
import React, {useState} from "react";
import {addSubjectToStudent, getAllSubjects, setCurrentYearToStudent, setDayOfPresentToStudent} from "../client";
import {DownOutlined} from "@ant-design/icons";

const {Option} = Select;

function StudentDrawerForm({showDrawer, setShowDrawer}) {
    const onCLose = () => setShowDrawer(false);

    const onFinish = values => {
        alert(JSON.stringify(values, null, 2));
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    const RenderDropdownChooseYear = () => {

        const handleMenuClick = (e) => {

        };

        const menu = (
            <Menu
                onClick={handleMenuClick}
                items={[
                    {
                        label: 'cambia a 1° anno',
                        key: '1',
                    },
                    {
                        label: 'cambia a 2° anno',
                        key: '2',
                    },
                    {
                        label: 'cambia a 3° anno',
                        key: '3',
                    },
                    {
                        label: 'cambia a 4° anno',
                        key: '4',
                    },
                    {
                        label: 'cambia a 5° anno',
                        key: '5',
                    },
                ]}
            />
        );

        return (
            <>
                <Dropdown overlay={menu}>
                    <Button style={{marginTop: 10}}>
                        Seleziona anno scolastico
                        <DownOutlined/>
                    </Button>
                </Dropdown>
            </>
        );


    }

    const RenderDropdownIsPresent = () => {

        const handleMenuClick = (e) => {

        };

        const menu = (
            <Menu
                onClick={handleMenuClick}
                items={[
                    {
                        label: 'Si',
                        key: '1',
                    },
                    {
                        label: 'No',
                        key: '2',
                    },
                ]}
            />
        );

        return (
            <>
                <Dropdown overlay={menu}>
                    <Button style={{marginTop: 10}}>
                        Seleziona se presente
                        <DownOutlined/>
                    </Button>
                </Dropdown>
            </>
        );


    }

    const RenderCheckBox = () => {

        const CheckboxGroup = Checkbox.Group;
        const plainOptions = ['Lunedi', 'Martedi', 'Mercoledi', 'Giovedi', 'Venerdi'];

        function setDayOfPresentStudentVar(student, list) {
            console.log("set day of present student var");
            console.log(list);
        }


        const defaultCheckedList = [];
        const [checkedList, setCheckedList] = useState(defaultCheckedList);
        const [indeterminate, setIndeterminate] = useState(true);
        const [checkAll, setCheckAll] = useState(false);

        const onChange = (list) => {
            setCheckedList(list);
            setIndeterminate(!!list.length && list.length < plainOptions.length);
            setCheckAll(list.length === plainOptions.length);
        };

        const onCheckAllChange = (e) => {
            setCheckedList(e.target.checked ? plainOptions : []);
            setIndeterminate(false);
            setCheckAll(e.target.checked);
        };


        return (
            <>
                <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}
                          style={{marginLeft: 10, marginTop: 5}}>
                    Seleziona tutti
                </Checkbox>
                <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                               style={{marginLeft: 10}}/>
            </>
        );

    }

    const RenderSubjectList = () => {


        function loadSubjectsList() {
            let subjectList = [];
            getAllSubjects()
                .then(res => res.json())
                .then(data => {

                        data.forEach(
                            (elem) => {
                                subjectList.push({
                                    id: elem.id,
                                    value: elem.nameOfTheSubject,
                                })
                            })
                    }
                )
            return subjectList;
        }

        const subjectList = loadSubjectsList();


        return (
            <Row>
                <Col span={18}>
                    <List
                        size="small"
                        dataSource={subjectList}
                        footer={
                            <AutoComplete
                                style={{
                                    width: 200,
                                    marginLeft: 25,
                                }}
                                options={subjectList}
                                placeholder="cerca la materia"
                                filterOption={(inputValue, option) =>
                                    option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
                                }
                            />
                        }
                        renderItem={item => <List.Item>{item}</List.Item>}/>
                </Col>

            </Row>
        );
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
                    Cancel
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
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="Nome"
                        rules={[{required: true, message: 'Insersci il nome dello studente'}]}
                    >
                        <Input placeholder="Insersci il nome dello studente"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="surname"
                        label="Cognome"
                        rules={[{required: true, message: 'Insersci il cognome dello studente'}]}
                    >
                        <Input placeholder="Insersci il cognome dello studente"/>
                    </Form.Item>
                </Col>
            </Row>

            {/*<Row gutter={16}>*/}
            {/*    <Col span={12}>*/}
            {/*        <Form.Item*/}
            {/*        >*/}
            {/*            Anno scolastico*/}
            {/*            <RenderDropdownChooseYear/>*/}
            {/*        </Form.Item>*/}
            {/*    </Col>*/}
            {/*</Row>*/}

            {/*<Row gutter={16}>*/}
            {/*    <Divider orientation="left" orientationMargin="0">Gestione presenza:</Divider>*/}
            {/*    <Col span={12}>*/}
            {/*        <Form.Item*/}
            {/*        >*/}
            {/*            Presente in struttura*/}
            {/*            <RenderDropdownIsPresent/>*/}
            {/*        </Form.Item>*/}
            {/*    </Col>*/}
            {/*</Row>*/}

            {/*<Row gutter={16}>*/}
            {/*    <Col span={24}>*/}
            {/*        <Form.Item*/}
            {/*        >*/}
            {/*            Attivo nei giorni*/}
            {/*            <br/>*/}
            {/*            <RenderCheckBox/>*/}
            {/*        </Form.Item>*/}
            {/*    </Col>*/}
            {/*</Row>*/}

            {/*<Row gutter={16}>*/}
            {/*    <Col span={24}>*/}
            {/*        <Form.Item*/}
            {/*        >*/}
            {/*            Materie seguite*/}
            {/*            <br/>*/}
            {/*            <RenderSubjectList/>*/}
            {/*        </Form.Item>*/}
            {/*    </Col>*/}
            {/*</Row>*/}


            <Row>
                <Col span={12}>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
        </Form>
    </Drawer>
}

export default StudentDrawerForm;