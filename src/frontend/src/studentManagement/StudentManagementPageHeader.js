import {PageHeader, Descriptions} from "antd";
import React, {useEffect, useState} from "react";

const StudentManagementPageHeader = ({studentsList}) => {
    const [numOfPresentStudent, setNumberOfPresentStudent] = useState(0);

    useEffect(() => {
        getIsPresentStudent(studentsList);
        console.log("StudentManagementPageHeader mounted.");
    }, [studentsList]);

    function getIsPresentStudent(students) {
        let numOfPresentStudent = 0;
        students.forEach(student => {
            if (student["present"]) numOfPresentStudent++;
        });
        setNumberOfPresentStudent(numOfPresentStudent);
    }

    return (
        <PageHeader ghost={false} title="Gestisci gli studenti">
            <Descriptions size="small" column={1}>
                <Descriptions.Item label="Studenti totali"> {studentsList.length}</Descriptions.Item>
                <Descriptions.Item label="Studenti presenti in struttura"> {numOfPresentStudent}</Descriptions.Item>
            </Descriptions>
        </PageHeader>
    );
}

export default StudentManagementPageHeader;