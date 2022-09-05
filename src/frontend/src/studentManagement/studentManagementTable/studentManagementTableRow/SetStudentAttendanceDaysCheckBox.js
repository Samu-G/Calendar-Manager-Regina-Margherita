import {Checkbox, message} from "antd";
import React, {useEffect, useState} from "react";
import {getNameOfTheDaysOfPresenceFromStudent, setDayOfPresentToStudent} from "../../../client";

const CheckboxGroup = Checkbox.Group;

const plainOptions = ['Lunedi', 'Martedi', 'Mercoledi', 'Giovedi', 'Venerdi'];

const SetStudentAttendanceDaysCheckBox = ({student, fetchStudents}) => {

    function fillCheckedList() {
        let days = [];
        getNameOfTheDaysOfPresenceFromStudent(student["id"])
            .then(res => res.json())
            .then(data => {
                days = data;
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
        return days;
    }

    useEffect(() => {
        fillCheckedList();
        console.log("SetStudentAttendanceDaysCheckBox mounted.");
    }, [student]);

    function setDayOfPresentStudentVar(student, list) {
        console.log("set day of present student var");
        console.log(list);
        setDayOfPresentToStudent(student.id, list)
            .then(() => {
                message.success('Modifiche apportate con successo');
            });
    }

    const onChange = (list) => {
        setCheckedList(list);
        setIndeterminate(!!list.length && list.length < plainOptions.length);
        setCheckAll(list.length === plainOptions.length);
        setDayOfPresentStudentVar(student, list);
    };

    const onCheckAllChange = (e) => {
        setCheckedList(e.target.checked ? plainOptions : []);
        setIndeterminate(false);
        setCheckAll(e.target.checked);

        if (e.target.checked) {
            setDayOfPresentStudentVar(student, plainOptions);
        } else {
            setDayOfPresentStudentVar(student, []);
        }
    };

    const [checkedList, setCheckedList] = useState(fillCheckedList);
    const [indeterminate, setIndeterminate] = useState(true);
    const [checkAll, setCheckAll] = useState(false);

    return (
        <>
            <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}
                      style={{marginLeft: 10, marginTop: 5}}>
                Seleziona tutti
            </Checkbox>
            <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                           style={{marginLeft: 10}}/>
        </>
    );
}

export default SetStudentAttendanceDaysCheckBox;