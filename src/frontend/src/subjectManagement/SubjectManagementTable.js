import React, {useEffect} from "react";
import {Button, Space, Table} from "antd";
import {deleteSubjectById} from "../client";
import {PlusOutlined} from "@ant-design/icons";
import AddSubjectButton from "./AddSubjectButton";

const SubjectManagementTable = ({subjectsList, fetchSubjects}) => {

    const columns = [
        {
            title: 'Nome della materia',
            dataIndex: 'nameOfTheSubject',
            key: 'name',
            width: "80%",
        },
        {
            title: 'Azioni',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    <Button type="primary" danger onClick={() => {
                        deleteSubjectById(record.id)
                            .then(() => {
                                    fetchSubjects();
                                }
                            ).catch(() => {

                        })
                    }}>
                        Cancella la materia
                    </Button>
                </Space>
            ),
            width: "20%",
        },
    ];


    useEffect(() => {
        console.log(subjectsList)
        console.log("SubjectManagementTable mounted.");
    }, [subjectsList]);

    return (
        <Table
            dataSource={subjectsList}
            columns={columns}
            bordered
            rowKey={subject => subject.id}
            title={() => <AddSubjectButton subjectsList={subjectsList} fetchSubjects={fetchSubjects}/>}
            size={"small"}
            pagination={{pageSize: 20}}
        >
        </Table>
    );

}

export default SubjectManagementTable;