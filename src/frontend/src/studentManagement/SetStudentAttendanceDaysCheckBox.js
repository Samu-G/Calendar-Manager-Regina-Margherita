import {Checkbox, message} from "antd";
import React, {useEffect, useState} from "react";
import {getNameOfTheDaysOfPresenceFromStudent, setDaysOfPresenceToStudent} from "../client";

const CheckboxGroup = Checkbox.Group;

const plainOptions = ["Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"];

const SetStudentAttendanceDaysCheckBox = ({student, fetchStudents}) => {
    const [checkedList, setCheckedList] = useState([]);
    const [indeterminate, setIndeterminate] = useState(false);
    const [checkAll, setCheckAll] = useState(false);
    const [changed, setChanged] = useState(false);

    const setupCheckedList = () => {
        getNameOfTheDaysOfPresenceFromStudent(student["id"])
            .then(res => res.json())
            .then(data => {
                setCheckedList(data);
                setIndeterminate(!!data.length && data.length < plainOptions.length);
                setCheckAll(data.length === plainOptions.length);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    useEffect(() => {
        setupCheckedList();
        console.log("SetStudentAttendanceDaysCheckBox mounted.");
    }, []);

    useEffect(() => {
        if (changed) {
            setDaysOfPresenceToStudent(student["id"], checkedList)
                .then(() => {
                    fetchStudents();
                    message.success("Giorni di presenza modificati con successo");
                }).catch(() => {
                message.error("Errore nella comunicazione con il server");
            });
            setChanged(false);
        }
    }, [changed]);

    const onChange = (list) => {
        setCheckedList(list);
        setIndeterminate(!!list.length && list.length < plainOptions.length);
        setCheckAll(list.length === plainOptions.length);
        setChanged(true);
    };

    const onCheckAllChange = (e) => {
        setCheckedList(e.target.checked ? plainOptions : []);
        setIndeterminate(false);
        setCheckAll(e.target.checked);
        setChanged(true);
    };

    return (
        <>
            <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}
                      style={{marginLeft: 10, marginTop: 5}} disabled={!student["present"]}>
                Seleziona tutti
            </Checkbox>
            <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                           style={{marginLeft: 10}} disabled={!student["present"]}/>
        </>
    );
}

export default SetStudentAttendanceDaysCheckBox;