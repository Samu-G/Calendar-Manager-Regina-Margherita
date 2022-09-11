import TeacherManagementPageHeader from "./TeacherManagementPageHeader";
import {useEffect, useState} from "react";
import {getAllStudents, getAllTeachers} from "../client";
import {message} from "antd";
import {Content} from "antd/es/layout/layout";
import TeacherManagementTable from "./TeacherManagementTable";

const TeacherManagementMain = () => {
    const [teachers, setTeachers] = useState([]);

    const fetchTeachers = () => {
        getAllTeachers()
            .then(res => res.json())
            .then(data => {
                setTeachers(data);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    useEffect(() => {
        fetchTeachers();
        console.log("TeacherManagementMain mounted.");
    }, []);

    return (
        <div className="site-layout-background" style={{padding: 24}}>
            <TeacherManagementPageHeader teacherList={teachers}/>

            <Content className="site-layout-background" style={{padding: 6}}>
                <TeacherManagementTable teacherList={teachers} fetchTeachers={fetchTeachers}/>
            </Content>

        </div>
    );
}

export default TeacherManagementMain;