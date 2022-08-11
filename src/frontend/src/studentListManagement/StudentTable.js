import {
    Button,
    Checkbox,
    Col,
    Divider,
    Menu,
    message,
    Row,
    Spin,
    Table,
    Dropdown,
    PageHeader,
    Descriptions,
    List,
    Typography, AutoComplete
} from 'antd';
import React, {useEffect, useState} from 'react';
import {
    addSubjectToStudent,
    flipIsPresent,
    getAllStudents, getAllSubjects, setCurrentYearToStudent,
    setDayOfPresentToStudent
} from "../client";
import {DownOutlined, LoadingOutlined} from "@ant-design/icons";
import {Content, Header} from "antd/es/layout/layout";

const columns = [
    {
        title: 'Nome',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Cognome',
        dataIndex: 'surname',
        key: 'surname',
    },
    {
        title: 'Presente',
        dataIndex: 'isPresent',
        key: 'isPresent',
    }
];

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function StudentTable() {
    const [students, setStudents] = useState([]);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);
    const [numOfPresentStudent, setNumberOfPresentStudent] = useState(0);

    const fetchStudents = () => {
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                setStudents(data);
                getIsPresentStudent(data);
                setFetching(false);
                console.log("student list update successfully");
            })
    }

    function getIsPresentStudent(students) {
        let numOfPresentStudent = 0;
        console.log(students);
        students.forEach(student => {
                if (student.isPresent === "Si") {
                    numOfPresentStudent++;
                }
            }
        );
        setNumberOfPresentStudent(numOfPresentStudent);
    }

    useEffect(() => {
        console.log("component is mounted");
        fetchStudents();
    }, []);

    function flipIsPresentVar(student) {
        flipIsPresent(student)
            .then(() => {
                    console.log("flipped isPresent var. from student");
                    message.info('Modifiche apportate con successo');
                    fetchStudents();
                }
            );
    }

    function studentIsPresent(student) {
        if (student.isPresent === "Si") {
            return <>
                Si <Button style={{marginLeft: 20}}
                           onClick={() => flipIsPresentVar(student)}>
                Disattiva presenza
            </Button>
            </>

        } else if (student.isPresent === "No") {
            return <>
                No <Button type="primary" style={{marginLeft: 20}}
                           onClick={() => flipIsPresentVar(student)}>
                Attiva presenza
            </Button>
            </>
        }
    }

    const renderStudents = () => {
        if (fetching) {
            return <Spin indicator={antIcon}/>;
        }

        const RenderCheckBox = (object) => {
            let student = object.user;

            const CheckboxGroup = Checkbox.Group;
            const plainOptions = ['Lunedi', 'Martedi', 'Mercoledi', 'Giovedi', 'Venerdi'];

            function setDayOfPresentStudentVar(student, list) {
                console.log("set day of present student var");
                console.log(list);
                setDayOfPresentToStudent(student.id, list)
                    .then(() => {
                        message.info('Modifiche apportate con successo');
                    });
            }

            function setDefaultCheckedList() {
                let list = [];
                if (student.lun === "Si") list.push('Lunedi')
                if (student.mar === "Si") list.push('Martedi')
                if (student.mer === "Si") list.push('Mercoledi')
                if (student.gio === "Si") list.push('Giovedi')
                if (student.ven === "Si") list.push('Venerdi')
                return list;
            }

            const defaultCheckedList = setDefaultCheckedList();
            const [checkedList, setCheckedList] = useState(defaultCheckedList);
            const [indeterminate, setIndeterminate] = useState(true);
            const [checkAll, setCheckAll] = useState(false);

            const onChange = (list) => {
                setCheckedList(list);
                setIndeterminate(!!list.length && list.length < plainOptions.length);
                setCheckAll(list.length === plainOptions.length);
                setDayOfPresentStudentVar(student, list);
            };

            const onCheckAllChange = (e) => {
                setCheckedList(e.target.checked ? plainOptions : []);
                setIndeterminate(false);
                setCheckAll(e.target.checked);

                if (e.target.checked) {
                    setDayOfPresentStudentVar(student, plainOptions);
                } else {
                    setDayOfPresentStudentVar(student, []);
                }
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

        const DropdownChooseYear = (object) => {

            const handleMenuClick = (e) => {
                let student = object.user;
                console.log("the student id is: ", student.id);
                console.log('The key clicked is: ', e.key);
                setCurrentYearToStudent(student.id, e.key)
                    .then(() => {
                        fetchStudents();
                        message.info('Modifiche apportate con successo');
                    })
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
                        <Button style={{marginLeft: 20}}>
                            Cambia anno scolastico
                            <DownOutlined/>
                        </Button>
                    </Dropdown>
                </>
            );


        }

        const RenderSubjectList = (object) => {
            let student = object.user;

            const subjectsFollowedListId = [];
            const subjectNotFollowedList = [];

            function loadSubjectsFollowedList() {
                let list = [];
                student.subjectsFollowedList.forEach(
                    (elem) => {
                        subjectsFollowedListId.push(elem.id)
                        list.push("•   " + elem.nameOfTheSubject + " anno scolastico: " + elem.yearOfTeaching)
                    }
                )
                return list;
            }

            const subjectsFollowedList = loadSubjectsFollowedList();

            function loadSubjectsList() {
                let subjectList = [];
                getAllSubjects()
                    .then(res => res.json())
                    .then(data => {

                        data.forEach(
                            (elem) => {
                                // console.log(elem)
                                subjectList.push({
                                    id: elem.id,
                                    value: elem.nameOfTheSubject,
                                })
                                if (!subjectsFollowedListId.includes(elem.id)) {
                                    subjectNotFollowedList.push({
                                        key: elem.id,
                                        value: elem.nameOfTheSubject,
                                    })
                                }
                            }
                        )

                        console.log("subject list update successfully");
                        console.log("Lo studente " + student.id + " segue le seguenti materie: ")
                        console.log(subjectsFollowedList)
                        console.log("Lo studente " + student.id + " non segue le seguenti materie: ")
                        console.log(subjectNotFollowedList)
                        console.log("Le materie che insegnamo sono: ")
                        console.log(subjectList)
                    })
            }

            function onSelect(val) {
                console.log(val)
                addSubjectToStudent(student.id, val)
                    .then(() => {
                            message.info('Modifiche apportate con successo');
                            fetchStudents();
                        }
                    );
            }

            loadSubjectsList();

            return (
                <Row>
                    <Col span={18}>
                        <List
                            size="small"
                            dataSource={subjectsFollowedList}
                            footer={
                                <AutoComplete
                                    style={{
                                        width: 200,
                                        marginLeft: 25,
                                    }}
                                    options={subjectNotFollowedList}
                                    placeholder="cerca la materia"
                                    filterOption={(inputValue, option) =>
                                        option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
                                    }
                                    onSelect={(val, option) => onSelect(val)}
                                />
                            }
                            renderItem={item => <List.Item>{item}</List.Item>}/>
                    </Col>

                </Row>
            );
        }

        return <>
            <Header className="site-layout-background" style={{padding: 0}}>

                <div>
                    <PageHeader
                        ghost={false}
                        title="Lista degli studenti">

                        <Descriptions size="medium" column={2}>
                            <Descriptions.Item label="Studenti"> {students.length} </Descriptions.Item>
                            <br/>
                            <Descriptions.Item
                                label="Studenti presenti in struttura"> {numOfPresentStudent} </Descriptions.Item>
                        </Descriptions>

                    </PageHeader>
                </div>

            </Header>

            <Content style={{margin: '0 16px'}}>

                <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>
                    <br/> <br/>
                    <Table
                        dataSource={students}
                        columns={columns}
                        bordered
                        pagination={{pageSize: 50}}
                        scroll={{y: 1000}}
                        rowKey={student => student.id}
                        expandable={{
                            expandedRowRender: (student) => (
                                <div style={{margin: 5}}>
                                    <Row>
                                        <Col span={12}>
                                            <Divider orientation="left" orientationMargin="0">Gestione
                                                presenza:</Divider>
                                            <p>Presente in struttura: {studentIsPresent(student)}</p>
                                            <br/>
                                            <p>Attivo nei giorni di:</p>
                                            <RenderCheckBox user={student}/>
                                            <br/> <br/>
                                            <p>Materie seguite:</p>
                                            <RenderSubjectList user={student}/>
                                        </Col>

                                        <Col span={12}>
                                            <Divider orientation="left" orientationMargin="0">Dati generici:</Divider>
                                            <p>Nome: {student.name}</p>
                                            <br/>
                                            <p>Cognome: {student.surname}</p>
                                            <br/>
                                            <p>Anno scolastico: {student.currentYear} <DropdownChooseYear
                                                user={student}/></p>
                                        </Col>
                                    </Row>
                                </div>
                            ),
                        }}
                    />
                </div>

            </Content>
        </>
    }

    return renderStudents();
}

export default StudentTable;