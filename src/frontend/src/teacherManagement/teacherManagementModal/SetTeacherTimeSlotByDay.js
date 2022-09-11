import {Checkbox, Divider, message} from "antd";
import React, {useEffect, useState} from "react";
import {getTimeSlotFromTeacherByDayName, setTimeSlotForTeacherByDayName} from "../../client";

const plainOptions = ["08:30-09:00", "09:00-09:30", "09:30-10:00", "10:00-10:30",
    "10:30-11:00", "11:00-11:30", "11:30-12:00", "12:00-12:30",
    "12:30-13:00", "13:00-13:30", "13:30-14:00", "14:00-14:30",
    "14:30-15:00", "15:00-15:30", "15:30-16:00", "16:00-16:30"];

const CheckboxGroup = Checkbox.Group;

const SetTeacherTimeSlotByDay = ({dayName, currentTeacher}) => {
    const [changed, setChanged] = useState(false);

    const setupCheckedList = () => {
        getTimeSlotFromTeacherByDayName(currentTeacher["id"], dayName)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setCheckedList(data);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    useEffect(() => {
        setupCheckedList();
        console.log("SetTeacherTimeSlotByDay mounted");
    }, []);

    useEffect(() => {
        if (changed) {
            setTimeSlotForTeacherByDayName(currentTeacher["id"], dayName, checkedList)
                .then(() => {
                    message.success("Fasce orarie di disponibilità modificate con successo");
                }).catch(() => {
                message.error("Errore nella comunicazione con il server");
            });
            setChanged(false);
        }
    }, [changed]);

    const [checkedList, setCheckedList] = useState([]);

    const onChange = (list) => {
        setCheckedList(list);
        setChanged(true);
    }

    return (
        <>
            <Divider orientation="left" orientationMargin="0">
                {dayName}
            </Divider>
            <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange} style={{marginLeft: 10}}/>
        </>
    );
}

export default SetTeacherTimeSlotByDay;