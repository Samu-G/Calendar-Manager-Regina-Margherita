import {LoadingOutlined} from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import {Descriptions, PageHeader, Spin, Table} from "antd";
import {getAllTeachers} from "../client";
import {Content, Header} from "antd/es/layout/layout";

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function TeacherTable() {
    const [fetching, setFetching] = useState(true);
    const [teachers, setTeachers] = useState([]);
    const [showDrawer, setShowDrawer] = useState(false);
    const [numOfPresentTeacher, setNumberOfPresentTeacher] = useState(0);

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

    return (
        <>
            <Header className="site-layout-background" style={{padding: 0}}>
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


                <Content>

                    <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>
                        <Table
                            dataSource={teachers}
                            columns={columns}
                            bordered

                        >

                        </Table>
                    </div>
                </Content>

            </Header>
        </>
    );


}

export default TeacherTable;