import React, {useEffect} from "react";
import {Table} from "antd";
import AddSubjectButton from "./AddSubjectButton";
import DeleteSubjectButton from "./DeleteSubjectButton";

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
                <DeleteSubjectButton subject={record} fetchSubjects={fetchSubjects}/>
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