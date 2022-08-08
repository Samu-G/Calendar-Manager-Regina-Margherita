import {Layout, Breadcrumb, Button, Spin, Table, Input, Space, Descriptions, PageHeader} from "antd";
import StudentDrawerForm from "./StudentDrawerForm";
import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";
import {useRef, useEffect, useState} from "react";
import {getAllStudents} from "../client";
import {SearchOutlined} from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import StudentTable from "./StudentTable";

const {Header, Content} = Layout;


function getColumnSearchProps(name) {

}

const data = [
    {
        key: '1',
        isPresent: 'No',
        name: 'Mario',
        surname: 'Rossi',
        currentYear: '1',
        dsa: 'No',
        feedback: '0',
    },
    {
        key: '2',
        isPresent: 'Si',
        name: 'Alberto',
        surname: 'Rossi',
        currentYear: '2',
        dsa: 'No',
        feedback: '1',
    },
    {
        key: '3',
        isPresent: 'Si',
        name: 'Giovanni',
        surname: 'Rossi',
        currentYear: '3',
        dsa: 'Si',
        feedback: '2',
    }
];

const columns = [
    {
        title: 'Presente',
        dataIndex: 'isPresent',
        key: 'isPresent',
        ...getColumnSearchProps('name'),
    },
    {
        title: 'Nome',
        dataIndex: 'name',
        key: 'name',
        ...getColumnSearchProps('name'),
    },
    {
        title: 'Cognome',
        dataIndex: 'surname',
        key: 'surname',
    },
    {
        title: 'Anno in corso',
        dataIndex: 'currentYear',
        key: 'currentYear',
    },
    {
        title: 'DSA',
        dataIndex: 'dsa',
        key: 'dsa',
    },
    {
        title: 'Feedback non letti',
        dataIndex: 'feedback',
        key: 'feedback',
    },
];


const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function StudentListManagement() {
    const [students, setStudents] = useState([]);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);


    // const fetchStudents = () => {
    //     getAllStudents()
    //         .then(res => res.json())
    //         .then(data => {
    //             console.log(data);
    //             setStudents(data);
    //             setFetching(false);
    //         })
    // }

    // useEffect(() => {
    //     console.log("component is mounted");
    //     fetchStudents();
    // }, []);

    // const renderStudents = () => {
    //     if (fetching) {
    //         return <Spin indicator={antIcon}/>;
    //     }
    //     return <>
    //         <StudentDrawerForm
    //             showDrawer={showDrawer}
    //             setShowDrawer={setShowDrawer}
    //             fetchStudents={fetchStudents}
    //         />
    //         <Table
    //             dataSource={data}
    //             columns={columns}
    //             bordered
    //             title={() =>
    //                 <>
    //                     <Button
    //                         onClick={() => setShowDrawer(!showDrawer)}
    //                         type="primary" shape="round" icon={<PlusOutlined/>} size="small">
    //                         Aggiungi studente
    //                     </Button>
    //                 </>
    //             }
    //             pagination={{pageSize: 50}}
    //             scroll={{y: 500}}
    //             rowKey={student => student.id}
    //         />
    //     </>
    // }

    return <>
        {/*T U T T O QUESTO VA IN OGNI COMPONENTE COSI MI GIOCO SEMRPRE LA PARTE SINISTRA DEL SITO*/}

        <Header className="site-layout-background" style={{padding: 0}}>

            <div>
                <PageHeader
                    ghost={false}
                    title="Lista degli studenti">

                    <Descriptions size="medium" column={2}>
                        <Descriptions.Item label="Studenti"> {students.length} </Descriptions.Item>
                        <Descriptions.Item label="Studenti attivi"> {students.length} </Descriptions.Item>
                        <Descriptions.Item
                            label="Feedback degli studenti da leggere"> {students.length} </Descriptions.Item>
                    </Descriptions>

                </PageHeader>
            </div>

        </Header>

        <Content style={{margin: '0 16px'}}>

            <div className="site-layout-background" style={{padding: 24, minHeight: 260}}>
                <br/> <br/>
                {StudentTable()}
            </div>

        </Content>
    </>
}

export default StudentListManagement;


