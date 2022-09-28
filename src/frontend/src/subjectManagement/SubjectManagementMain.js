import {useEffect, useState} from "react";
import {getAllSubjects} from "../client";
import {Content} from "antd/es/layout/layout";
import SubjectManagementPageHeader from "./SubjectManagementPageHeader";
import SubjectManagementTable from "./SubjectManagementTable";

const SubjectManagementMain = () => {
    const [subjects, setSubjects] = useState([]);

    const fetchSubjects = () => {
        getAllSubjects()
            .then(res => res.json())
            .then(data => {
                setSubjects(data);
            })
    }

    useEffect(() => {
        fetchSubjects();
        console.log("SubjectManagementMain mounted.");
    }, []);

    return (
        <div className="site-layout-background" style={{padding: 24}}>
            <SubjectManagementPageHeader subjectList={subjects} />

            <Content className="site-layout-background" style={{padding: 6}}>
                <SubjectManagementTable subjectsList={subjects} fetchSubjects={fetchSubjects} />
            </Content>
        </div>
    );
}

export default SubjectManagementMain;