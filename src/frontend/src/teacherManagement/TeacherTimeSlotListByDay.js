import {Button, Divider, List, message, Space, TimePicker, Typography} from "antd";
import {MinusOutlined, PlusOutlined} from "@ant-design/icons";
import {
    addAttendanceRules,
    fetchAttendanceRules,
    removeAttendanceRule
} from "../client";
import moment from "moment/moment";
import React, {useEffect, useState} from "react";

const {Text} = Typography;

const styles = ".ant-list-empty-text {text-align: left; padding: 0}";
let styleSheet = document.createElement("style");
styleSheet.innerText = styles
document.head.appendChild(styleSheet)

const TeacherTimeSlotListByDay = ({dayName, currentTeacher}) => {
    const [showAttendancePicker, setShowAttendancePicker] = useState(false);
    const [beginTimePicker, setBeginTimePicker] = useState("08:00");
    const [endTimePicker, setEndTimePicker] = useState("17:45");
    const [data, setData] = useState([]);

    const onLoadFetchAttendance = () => {
        fetchAttendanceRules(currentTeacher["id"], dayName)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setData(data);
            });
    }

    useEffect(() => {
        onLoadFetchAttendance();
    }, [currentTeacher]);

    const onClickAddRules = () => {
        console.log(beginTimePicker);
        console.log(endTimePicker);
        if (beginTimePicker < endTimePicker) {
            addAttendanceRules(currentTeacher["id"], dayName, beginTimePicker, endTimePicker)
                .then(() => {
                    setBeginTimePicker("08:00");
                    setEndTimePicker("17:45");
                    setShowAttendancePicker(false);
                    onLoadFetchAttendance();
                });
            message.success("Aggiunta regola di presenza");
        } else {
            message.warn("Ora d'inizio maggiore dell'ora di fine");
        }
    }
    const format = 'HH:mm';
    const attendancePicker = (
        <>
            <Space direction="horizontal">
                <Text> Presente dalle ore </Text>
                <TimePicker defaultValue={moment("08:00", format)} format={format} minuteStep={15}
                            disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23]} suffixIcon={null}
                            style={{width: 80}} showNow={false} placeholder={"inizio"}
                            onChange={(time, timeString) => {
                                setBeginTimePicker(timeString)
                            }}/>
                <Text> alle ore </Text>
                <TimePicker defaultValue={moment("17:45", format)} format={format} minuteStep={15}
                            disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23]} suffixIcon={null}
                            style={{width: 80}} showNow={false} placeholder={"fine"}
                            onChange={(time, timeString) => {
                                setEndTimePicker(timeString)
                            }}/>
            </Space>
            <Button type="link" size={"small"} icon={<PlusOutlined/>} onClick={onClickAddRules}
                    style={{marginLeft: 60}}>
                Aggiungi regola</Button>
            <Button type="link" danger size={"small"}
                    onClick={() => setShowAttendancePicker(false)}
            >Annulla</Button>

        </>
    );
    const showAttendancePickerButton = (
        <Button type="link" size={"small"} onClick={() => setShowAttendancePicker(true)} icon={<PlusOutlined/>}>Aggiungi
            una
            nuova regola</Button>
    );

    function renderFooter() {
        if (showAttendancePicker) return attendancePicker;
        else return showAttendancePickerButton;
    }

    return (
        <>
            <Divider orientation="left" orientationMargin="0" style={{marginTop: 7, marginBottom: 7}}>
                {dayName}

            </Divider>
            <Space direction="vertical">

                <List
                    locale={{
                        emptyText: (<Text type="secondary" italic>Nessuna presenza impostata per {dayName}</Text>)
                    }}
                    size={"default"}
                    bordered={false}
                    dataSource={data}
                    renderItem={(item) => (
                        <List.Item key={item.text}>
                            {item["dayName"] + " presente dalle " + item["beginTime"] + " alle " + item["endTime"]}
                            <Button type="link" danger size={"small"} style={{marginLeft: 20}} icon={<MinusOutlined/>}
                                    onClick={() => {
                                        removeAttendanceRule(currentTeacher["id"], item["id"])
                                            .then(() => {
                                                onLoadFetchAttendance();
                                            })
                                    }}>
                                Rimuovi regola
                            </Button>
                        </List.Item>
                    )}
                    footer={renderFooter()}

                />
            </Space>
        </>
    );
}

export default TeacherTimeSlotListByDay;