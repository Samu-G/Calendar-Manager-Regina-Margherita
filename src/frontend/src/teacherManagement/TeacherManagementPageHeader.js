import {Descriptions, PageHeader} from "antd";
import React, {useEffect, useState} from "react";


const TeacherManagementPageHeader = ({teacherList}) => {
    const [numOfActiveTeacher, setNumOfActiveTeacher] = useState(0);

    useEffect(() => {
        getActiveTeacher(teacherList);
        console.log("TeacherManagementPageHeader mounted.");
    }, [teacherList]);

    function getActiveTeacher(teacherList) {
        let numOfActiveTeacher = 0;
        teacherList.forEach(teacher => {
            if(teacher["active"]) numOfActiveTeacher++;
        });
        setNumOfActiveTeacher(numOfActiveTeacher);
    }

    const DescriptionsStyle = {paddingBottom: 0};

    return (
        <PageHeader ghost={false} title="Gestisci i docenti">
            <Descriptions size="small" column={1}>
                <Descriptions.Item label="Docenti totali" style={DescriptionsStyle}> {teacherList.length} </Descriptions.Item>
                <Descriptions.Item label="Docenti attivi in calendario" style={DescriptionsStyle}> {numOfActiveTeacher} </Descriptions.Item>
            </Descriptions>
        </PageHeader>
    );
}

export default TeacherManagementPageHeader;