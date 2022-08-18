import {AutoComplete, Col, List, message, Row} from "antd";
import React from "react";
import {addSubjectToTeacher, getAllSubjects} from "../client";

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
                                onSelect(val)
                                val = ""
                            }}
                        />
                    }
                    renderItem={item => <List.Item>{item}</List.Item>}/>
            </Col>
        </Row>

    );
}

export default RenderSubjectTeached;