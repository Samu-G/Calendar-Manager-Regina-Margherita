import React, {useEffect, useState} from 'react';
import {Button, Descriptions, PageHeader, Spin, Table} from "antd";
import {Content, Header} from "antd/es/layout/layout";
import {getAllSubjects} from "../client";
import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";
import SubjectDrawerForm from "./SubjectDrawerForm";
import DeleteSubjectButton from "./DeleteSubjectButton";


const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

export default function SubjectTable() {
    const [fetching, setFetching] = useState(true);
    const [subjects, setSubjects] = useState([]);
    const [showDrawer, setShowDrawer] = useState(false);

    const fetchSubjects = () => {
        getAllSubjects()
            .then(res => res.json())
            .then(data => {
                setSubjects(data)
                setFetching(false);
                console.log(data)
                console.log("subject list update successfully");
            })
    }

    const columns = [
        {
            title: 'Nome',
            dataIndex: 'nameOfTheSubject',
            key: 'name',
        },
        {
            title: 'Azioni',
            key: 'action',
            render: (_, record) => (
                <DeleteSubjectButton subject={record} fetchSubjects={fetchSubjects}/>
            ),
        },
    ];

    useEffect(() => {
        console.log("component is mounted");
        fetchSubjects();
    }, []);

    if (fetching) {
        return <Spin indicator={antIcon}/>;
    }

    return (
        <>
            <SubjectDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchSubjects={fetchSubjects}
            />
            <Header className="site-layout-background" style={{padding: 0}}>
                <div>
                    <PageHeader
                        ghost={false}
                        title="Lista delle materie">
                        <Descriptions size="medium" column={2}>
                            <Descriptions.Item label="Materie"> {subjects.length} </Descriptions.Item>
                            <br/>
                        </Descriptions>
                    </PageHeader>
                </div>

            </Header>

            <Content style={{margin: '0 16px'}}>

                <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>

                    <Table
                        dataSource={subjects}
                        columns={columns}
                        bordered
                        rowKey={subject => subject.id}
                        title={() =>
                            <Button
                                onClick={() => setShowDrawer(!showDrawer)}
                                type="primary"
                                shape="round"
                                icon={<PlusOutlined/>}
                                size="small">
                                Aggiungi una nuova materia
                            </Button>
                        }
                    >
                    </Table>

                </div>

            </Content>

        </>
    );
}