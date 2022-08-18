import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import {Button, Col, Descriptions, Divider, PageHeader, Row, Spin, Table} from "antd";
import {getAllTeachers} from "../client";
import {Content, Header} from "antd/es/layout/layout";
import RenderSubjectTeached from "./RenderSubjectTeached";

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;


function TeacherTable() {
    const [fetching, setFetching] = useState(true);
    const [teachers, setTeachers] = useState([]);
    const [showDrawer, setShowDrawer] = useState(false);
    const [numOfPresentTeacher, setNumberOfPresentTeacher] = useState(0);


    const columns = [
        {
            title: 'Giorni di presenza',
            key: 'isPresent',
            render: (_, record) => {
                let presenceArray = [];
                if (record.mondayIsPresent) presenceArray.push(" -Lun")
                if (record.tuesdayIsPresent) presenceArray.push(" -Mar")
                if (record.wednesdayIsPresent) presenceArray.push(" -Mer")
                if (record.thursdayIsPresent) presenceArray.push(" -Gio")
                if (record.fridayIsPresent) presenceArray.push(" -Ven")

                return (
                    presenceArray
                );
            },
        },
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
    ];

    const fetchTeachers = () => {
        getAllTeachers()
            .then(res => res.json())
            .then(data => {
                console.log(data)
                setTeachers(data);
                getIsPresentTeacher(data);
                setFetching(false);
                console.log("teacher list update successfully");
            })
    }

    function getIsPresentTeacher(teachers) {
        let numOfPresentTeacher = 0;
        console.log(teachers);
        teachers.forEach(teacher => {
                if (teacher.present) {
                    numOfPresentTeacher++;
                }
            }
        );
        setNumberOfPresentTeacher(numOfPresentTeacher);
    }

    useEffect(() => {
        console.log("component is mounted");
        fetchTeachers();
    }, []);


    if (fetching) {
        return <Spin indicator={antIcon}/>;
    }

    return (
        <>
            <Header className="site-layout-background" style={{padding: 0}}>
                <div>
                    <PageHeader
                        ghost={false}
                        title="Lista dei docenti">
                        <Descriptions size="medium" column={2}>
                            <Descriptions.Item label="Docenti"> {teachers.length} </Descriptions.Item>
                            <br/>
                            <Descriptions.Item
                                label="Docenti attivi per il calendario"> {numOfPresentTeacher} </Descriptions.Item>
                        </Descriptions>
                    </PageHeader>
                </div>

                <Content>
                    <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>
                        <Table
                            dataSource={teachers}
                            columns={columns}
                            bordered
                            title={() =>
                                <Button
                                    // onClick={}
                                    type="primary"
                                    shape="round"
                                    icon={<PlusOutlined/>}
                                    size="small">
                                    Aggiungi un nuovo docente
                                </Button>
                            }
                            pagination={{pageSize: 25}}
                            scroll={{y: 1000}}
                            rowKey={teacher => teacher.id}
                            expandable={{
                                expandedRowRender: (teacher) => (
                                    <div style={{margin: 5}}>
                                        <Row>
                                            <Col span={12}>
                                                <Divider orientation="left" orientationMargin="0">Gestione
                                                    insegnamenti:</Divider>
                                                <p>Materie insegnate:</p>
                                                <RenderSubjectTeached teacher={teacher} fetchTeachers={fetchTeachers}/>
                                            </Col>

                                            <Col span={12}>
                                                <Divider orientation="left" orientationMargin="0">Dati generici:</Divider>
                                                <p>Nome: {teacher.name}</p>
                                                <br/>
                                                <p>Cognome: {teacher.surname}</p>
                                            </Col>
                                        </Row>

                                        <Row>
                                            <Divider orientation="left" orientationMargin="0">Gestione
                                                presenza:</Divider>
                                        </Row>
                                    </div>
                                ),
                            }}
                        />
                    </div>
                </Content>

            </Header>
        </>
    );


}

export default TeacherTable;