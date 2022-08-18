import {Checkbox, message} from "antd";
import React, {useState} from "react";
import {setDayOfAttendanceToTeacherId, setDayOfPresentToStudent} from "../client";



function RenderPresenceCheckBox({teacher}) {

    function setDefaultCheckedList() {
        // let list = [];
        // if (teacher.lun === "Si") list.push('Lunedi')
        // if (teacher.mar === "Si") list.push('Martedi')
        // if (teacher.mer === "Si") list.push('Mercoledi')
        // if (teacher.gio === "Si") list.push('Giovedi')
        // if (teacher.ven === "Si") list.push('Venerdi')
        // return list;
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
    }




    const onChange = (list) => {
        // console.log(teacher)
        setCheckedList(list);
        setIndeterminate(!!list.length && list.length < plainOptions.length);
        setCheckAll(list.length === plainOptions.length);
        setDayOfAttendanceToTeacher(list);
    };

    const onCheckAllChange = (e) => {
        // setCheckedList(e.target.checked ? plainOptions : []);
        // setIndeterminate(false);
        // setCheckAll(e.target.checked);
        //
        // if (e.target.checked) {
        //     setDayOfPresentStudentVar(student, plainOptions);
        // } else {
        //     setDayOfPresentStudentVar(student, []);
        // }
    };

    return (
        <div>
        <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}
                  style={{marginLeft: 10, marginTop: 5}}>
            Seleziona tutti
        </Checkbox>
    <CheckboxGroup options={plainOptions} value={checkedList} onChange={onChange}
                   style={{marginLeft: 10}}/>
        </div>
    );
}

export default RenderPresenceCheckBox;