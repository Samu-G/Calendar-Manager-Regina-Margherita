import {Descriptions, PageHeader} from "antd";
import React from "react";


const TeacherManagementPageHeader = ({teacherList}) => {

    return (
        <PageHeader ghost={false} title="Gestisci i docenti">
            <Descriptions size="small" column={1}>
                <Descriptions.Item label="Docenti totali"> {teacherList.length} </Descriptions.Item>
            </Descriptions>
        </PageHeader>
    );
}

export default TeacherManagementPageHeader;