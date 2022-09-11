import {Checkbox, message} from "antd";
import React, {useState} from "react";
import {setDayOfAttendanceToTeacherId, setDaysOfPresenceToStudent} from "../../client";


function RenderPresenceCheckBox({teacher, fetchTeachers}) {

    function setDefaultCheckedList() {
        let list = [];
        if (teacher.mondayIsPresent) list.push('Lunedi')
        if (teacher.tuesdayIsPresent) list.push('Martedi')
        if (teacher.wednesdayIsPresent) list.push('Mercoledi')
        if (teacher.thursdayIsPresent) list.push('Giovedi')
         if (teacher.fridayIsPresent) list.push('Venerdi')
        return list;
    }

    const CheckboxGroup = Checkbox.Group;
    const plainOptions = ['Lunedi', 'Martedi', 'Mercoledi', 'Giovedi', 'Venerdi'];
    const defaultCheckedList = setDefaultCheckedList();
    const [checkedList, setCheckedList] = useState(defaultCheckedList);
    const [indeterminate, setIndeterminate] = useState(true);
    const [checkAll, setCheckAll] = useState(false);

    function setDayOfAttendanceToTeacher(list) {
        // console.log("set day of present student var");
        console.log(list);
        console.log(teacher.id);
        setDayOfAttendanceToTeacherId(teacher.id, list)
            .then(() => {
                message.success("Giorni di presenza modificati con successo")
                fetchTeachers()
            })
    }


    const onChange = (list) => {
        setCheckedList(list);
        setIndeterminate(!!list.length && list.length < plainOptions.length);
        setCheckAll(list.length === plainOptions.length);
        if (checkAll) {
            setDayOfAttendanceToTeacher(plainOptions);
        } else {
            setDayOfAttendanceToTeacher(list);
        }
    };

    return (
            <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                           style={{marginLeft: 10, marginTop: 5}}/>
    );
}

export default RenderPresenceCheckBox;