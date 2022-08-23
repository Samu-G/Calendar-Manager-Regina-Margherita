import {AutoComplete, Button, Col, Divider, List, message, Row} from "antd";
import React from "react";
import {addSubjectToTeacher, deleteSubjectFromTheTeacher, getAllSubjects} from "../client";

function RenderSubjectTeached({teacher, fetchTeachers}) {

    const subjectTeachedId = [];
    const subjectNotTeachedList = [];


    function loadSubjectsTeachedList() {
        let list = [];
        teacher.subjectTeached.forEach(
            (elem) => {
                subjectTeachedId.push(elem.id)
                list.push("•   " + elem.nameOfTheSubject)
            }
        )
        return list;
    }

    const subjectsTeachedList = loadSubjectsTeachedList();

    function loadSubjectsList() {
        let subjectList = [];
        getAllSubjects()
            .then(res => res.json())
            .then(data => {
                data.forEach(
                    (elem) => {
                        subjectList.push({
                            id: elem.id,
                            value: elem.nameOfTheSubject,
                        })
                        if (!subjectTeachedId.includes(elem.id)) {
                            subjectNotTeachedList.push({
                                key: elem.id,
                                value: elem.nameOfTheSubject,
                            })
                        }
                    }
                )
            });
    }

    function onSelect(val) {
        addSubjectToTeacher(teacher.id, val)
            .then(() => {
                    message.success(teacher.name + " " + teacher.surname + " ora insegna " + val);
                    fetchTeachers();
                }
            );
    }

    loadSubjectsList();

    return (
        <Row>
            <Col span={18}>
                <List
                    size="small"
                    dataSource={subjectsTeachedList}
                    footer={
                        <AutoComplete
                            style={{
                                width: 200,
                                marginLeft: 25,
                            }}
                            options={subjectNotTeachedList}
                            placeholder="cerca la materia"
                            filterOption={(inputValue, option) =>
                                option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
                            }
                            onSelect={(val, option) => {
                                onSelect(val);
                            }}
                        />
                    }
                    renderItem={(item) => (
                            <Row>
                                <Col span={15}>
                                    <List.Item> {item} </List.Item>
                                </Col>
                                <Col span={4}>
                                    <Button type="primary" danger onClick={() => {
                                        console.log(item);
                                        console.log(teacher.id)
                                        deleteSubjectFromTheTeacher(teacher.id, item)
                                            .then(() => {
                                                fetchTeachers()
                                                message.success("Materie insegnate da " + teacher.name + " aggiornate con successo");
                                            })
                                    }}>
                                        Cancella materia
                                    </Button>
                                </Col>
                                <Divider style={{margin: 7}}/>
                            </Row>
                        )
                    }/>
            </Col>
        </Row>

    );
}

export default RenderSubjectTeached;