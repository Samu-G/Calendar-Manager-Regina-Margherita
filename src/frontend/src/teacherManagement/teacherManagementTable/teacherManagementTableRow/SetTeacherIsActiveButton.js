import React, {useEffect} from "react";
import {Button, message, Space, Typography} from "antd";
import {setActiveToTeacher} from "../../../client";

const {Text} = Typography;

const SetTeacherIsActiveButton = ({teacher, fetchTeachers}) => {

    useEffect(() => {
        console.log("SetTeacherIsActiveButton mounted.")
    }, [teacher]);

    function setActiveVariableToTeacher(isActive) {
        setActiveToTeacher(teacher["id"], isActive)
            .then(() => {
                    fetchTeachers();
                    if (isActive)
                        message.success("Il docente " + teacher["name"] + " " + teacher["surname"] + " è stato attivato");
                    else
                        message.success("Il docente " + teacher["name"] + " " + teacher["surname"] + " è stato disattivato");
                }
            ).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    }


    if (teacher["active"]) {
        return <>
            <Space>
                <Text>Attivo in calendario: </Text>Si
                <Button onClick={() => setActiveVariableToTeacher(false)}>Disattiva</Button>
                <Text type="secondary">Il docente è selezionabile durante la creazione del calendario</Text>
            </Space>
        </>
    } else {
        return <>
            <Space>
                <Text>Attivo in calendario: </Text>No
                <Button type="primary" onClick={() => setActiveVariableToTeacher(true)}>Attiva</Button>
                <Text type="secondary">Il docente non è selezionabile durante la creazione del calendario</Text>
            </Space>
        </>
    }
}

export default SetTeacherIsActiveButton;