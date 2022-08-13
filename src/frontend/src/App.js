import React, {useState} from 'react'
import {Layout} from 'antd';
import Sider from "./Sider";
import './App.css';
import StudentListManagement from "./studentListManagement/StudentListManagement";
import SubjectListManagement from "./subjectListManagement/SubjectListManagement";

const {Content} = Layout;

export default function App() {

    const components = {
        1: <div>Option 1</div>,
        2: <div>Option 2</div>,
        3: <div>Option 3</div>,
        4: StudentListManagement(),
        5: <div>Option 5</div>,
        6: SubjectListManagement(),
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