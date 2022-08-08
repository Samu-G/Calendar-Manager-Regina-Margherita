import {Button, Checkbox, Col, Divider, Row, Spin, Table} from 'antd';
import React, {useEffect, useState} from 'react';
import {flipIsPresent, getAllStudents} from "../client";
import StudentDrawerForm from "./StudentDrawerForm";
import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";

const columns = [
    {
        title: 'Nome',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Cognome',
        dataIndex: 'surname',
        key: 'surname',
    },
    {
        title: 'Presente',
        dataIndex: 'isPresent',
        key: 'isPresent',
    }
];

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function StudentTable() {
    const [students, setStudents] = useState([]);
    const [fetching, setFetching] = useState(true);
    const [updatingIsPresent, setUpdatingIsPresent] = useState(true);

    const fetchStudents = () => {
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                setStudents(data);
                setFetching(false);
                console.log(data);
            })
    }


    useEffect(() => {
        console.log("component is mounted");
        fetchStudents();
    }, []);

    function flipIsPresentVar(student) {
        flipIsPresent(student)
            .then(() => {
                    console.log("flipped isPresent var. from student")
                    getAllStudents()
                        .then(res => res.json())
                        .then(data => {
                                setStudents(data);
                                console.log("student list update successfully");
                            }
                        )
                }
            );
    }

    const renderStudents = () => {
        if (fetching) {
            return <Spin indicator={antIcon}/>;
        }

        function studentIsPresent(student) {
            if (student.isPresent === "Si") {
                return <>
                    Si <Button style={{marginLeft: 20}}
                               onClick={() => flipIsPresentVar(student)}>
                    Disattiva presenza
                </Button>
                </>

            } else if (student.isPresent === "No") {
                return <>
                    No <Button type="primary" style={{marginLeft: 20}}
                               onClick={() => flipIsPresentVar(student)}>
                    Attiva presenza
                </Button>
                </>
            }
        }

        function studentWeek(student) {

        }

        return <>
            <Table
                dataSource={students}
                columns={columns}
                bordered
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={student => student.id}
                expandable={{
                    expandedRowRender: (student) => (
                        <div style={{margin: 5}}>
                            <Row>
                                <Col span={12}>
                                    <Divider orientation="left" orientationMargin="0">Gestione presenza:</Divider>
                                    <p>Presente in struttura: {studentIsPresent(student)}</p>
                                    <br/>
                                    <p>Attivo nei giorni di:</p>
                                    <p>{studentWeek(student)}</p>
                                </Col>

                                <Col span={12}>
                                    <Divider orientation="left" orientationMargin="0">Dati generici:</Divider>
                                    <p>Nome: {student.name}</p>
                                    <p>Cognome: {student.surname}</p>
                                    <br/>
                                    <p>Anno scolastico: {student.currentYear}</p>
                                    <p>Istituto di provenienza: ?? </p>
                                </Col>

                            </Row>
                        </div>
                    ),
                }}
            />
        </>
    }

    // return <>
    // <Table
    //     columns={columns}
    //     expandable={{
    //         expandedRowRender: (record) => (
    //             <p
    //                 style={{
    //                     margin: 0,
    //                 }}
    //             >
    //                 {record.description}
    //             </p>
    //         ),
    //         rowExpandable: (record) => record.name !== 'Not Expandable',
    //     }}
    //     dataSource={data}
    // />
    // </>

    return renderStudents();
}

export default StudentTable;