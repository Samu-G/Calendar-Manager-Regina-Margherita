import React, {useState} from 'react'
import {Layout} from 'antd';
import Sider from "./Sider";
import './App.css';
import CalendarCreatorMain from "./calendarCreator/CalendarCreatorMain";
import StudentManagementMain from "./studentManagement/StudentManagementMain";
import TeacherManagementMain from "./teacherManagement/TeacherManagementMain";

const {Content} = Layout;

export default function App() {

    const components = {
        1: <CalendarCreatorMain/>,
        2: <div>Option 2</div>,
        3: <div>Option 3</div>,
        4: <StudentManagementMain/>,
        5: <TeacherManagementMain/>,
        6: <div>Opzione Lista materie</div>,
        7: <div>Option 7</div>,
    };

    const [render, updateRender] = useState(1);

    const handleMenuClick = menu => {
        updateRender(menu.key);
    };

    return (
        <Layout style={{minHeight: '100vh'}}>
            <Sider handleClick={handleMenuClick} />
            <Layout className="site-layout">
                <Content>{components[render]}</Content>
            </Layout>
        </Layout>
    );
}