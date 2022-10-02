import {Checkbox, message} from "antd";
import React, {useEffect, useState} from "react";
import {getNameOfTheDaysOfPresenceFromTeacher, setDaysOfPresenceToTeacher} from "../client";

const CheckboxGroup = Checkbox.Group;

const plainOptions = ["Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"];

const SetTeacherAttendanceDaysCheckBox = ({teacher, fetchTeachers}) => {
    const [checkedList, setCheckedList] = useState([]);
    const [indeterminate, setIndeterminate] = useState(false);
    const [checkAll, setCheckAll] = useState(false);
    const [changed, setChanged] = useState(false);

    const setupCheckedList = () => {
        getNameOfTheDaysOfPresenceFromTeacher(teacher["id"])
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
        console.log("SetTeacherAttendanceDaysCheckBox mounted.");
    }, []);

    useEffect(() => {
        if (changed) {
            setDaysOfPresenceToTeacher(teacher["id"], checkedList)
                .then(() => {
                    fetchTeachers();
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
                      style={{marginLeft: 10, marginTop: 5}} disabled={!teacher["active"]}>
                Seleziona tutti
            </Checkbox>
            <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                           style={{marginLeft: 10}} disabled={!teacher["active"]}/>
        </>
    );

}

export default SetTeacherAttendanceDaysCheckBox;