import {AutoComplete, Button, Divider, List, Space, Typography} from "antd";
import React, {useEffect, useState} from "react";
import {CloseOutlined, MinusOutlined, PlusOutlined} from "@ant-design/icons";
import {getAvailableStudentByTeacherAndTimeSlot} from "../client";
import {addPendingRow, removePendingRow} from "../redux/slice/calendarCreator";
import {useDispatch} from "react-redux";

const {Text} = Typography;

export function TimeSlotEditor({teacher, timeSlot}) {
    const [showEnterStudent, setShowEnterStudent] = useState(false);
    const [options, setOptions] = useState([]);

    const [label, setLabel] = useState("");
    const [value, setValue] = useState("");
    const [key, setKey] = useState(0);

    const [data, setData] = useState([]);
    const [indexOf, setIndexOf] = useState(0);

    const dispatch = useDispatch();

    useEffect(() => {
        fetchOptions();
        console.log("TimeSlotEditor");
    }, []);

    const addStudentToTimeSlotButton = (
        <Button type="link" size={"small"} onClick={() => {
            setShowEnterStudent(true)
        }} icon={<PlusOutlined/>}>
            Aggiungi studente</Button>
    );

    function fetchOptions() {
        getAvailableStudentByTeacherAndTimeSlot(teacher["id"], timeSlot["key"])
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setOptions(data);
            });
    }

    const onSelect = (value, option) => {
        setValue(value);
        console.log("L'opzione -> ");
        console.log(option["label"]);
        console.log(option["value"]);
        console.log(option["key"]);

        setLabel(option["label"]);
        setValue(option["value"]);
        setKey(option["key"]);
    }

    const addStudentToTimeSlotAutocomplete = (
        <Space>
            <AutoComplete
                options={options}
                style={{width: 320}}
                placeholder="input here"
                onSelect={onSelect}
            />
            <Button icon={<PlusOutlined/>} onClick={() => {
                let list = data;
                console.log(value);
                list.push(value);
                console.log(list);
                setData(list);
                setShowEnterStudent(false);
                setIndexOf(list.indexOf(value));

                let obj = {
                    timeSlotId: timeSlot["key"],
                    studentNameSurnameCF: value,
                    studentId: key,
                };

                dispatch(addPendingRow(obj));

            }}>Aggiungi</Button>
            <Button icon={<CloseOutlined/>} danger onClick={() => {
                setShowEnterStudent(false);
            }}/>
        </Space>
    );


    function renderFooter() {
        if (showEnterStudent) return addStudentToTimeSlotAutocomplete;
        return addStudentToTimeSlotButton;
    }


    return (
        <>
            <Divider orientation="left" orientationMargin="0">
                Lezione dalle {timeSlot["beginTime"]} alle {timeSlot["endTime"]}
            </Divider>
            <List
                locale={{emptyText: (<Text type="secondary" italic>Nessun studente selezionato</Text>)}}
                dataSource={[...data]}
                renderItem={item => (
                    <List.Item style={{paddingLeft: 0}}> <Button danger icon={<MinusOutlined/>} type="link"
                                                                 size={"small"}
                                                                 onClick={() => {
                                                                     console.log(item);
                                                                     setIndexOf(data.indexOf(item));
                                                                     const list = [...data];
                                                                     list.splice(indexOf, 1);
                                                                     setData(list);
                                                                     dispatch(removePendingRow(item));
                                                                 }}/> {item} </List.Item>
                )}
                size="small"
                bordered={false}
                footer={renderFooter()}
            />
        </>
    );
}

