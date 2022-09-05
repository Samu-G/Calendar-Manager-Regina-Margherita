import React, {useEffect, useState} from "react";
import {getAllStudents} from "../client";
import StudentManagementPageHeader from "./StudentManagementPageHeader";
import {Content} from "antd/es/layout/layout";
import StudentManagementTable from "./StudentManagementTable";
import {message} from "antd";


const StudentManagementMain = () => {
    const [students, setStudents] = useState([]);
    const [showDrawer, setShowDrawer] = useState(false);

    const fetchStudents = () => {
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                setStudents(data);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    useEffect(() => {
        fetchStudents();
        console.log("StudentManagementMain mounted.");
    }, []);

    return (
        <div className="site-layout-background" style={{padding: 24}}>
            <StudentManagementPageHeader
                studentsList={students}
            />

            <Content className="site-layout-background" style={{padding: 6}}>
                <StudentManagementTable
                    studentList={students}
                    showDrawer={showDrawer}
                    setShowDrawer={setShowDrawer}
                    fetchStudents={fetchStudents}
                />
            </Content>

        </div>
    );
}

export default StudentManagementMain;