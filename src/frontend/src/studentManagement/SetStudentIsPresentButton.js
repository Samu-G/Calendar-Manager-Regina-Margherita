import {Button, message, Space, Typography} from "antd";
import React, {useEffect} from "react";
import {setPresenceToStudent} from "../client";

const {Text} = Typography;

const SetStudentIsPresentButton = ({student, fetchStudents}) => {

    useEffect(() => {
        console.log("SetStudentIsPresentButton mounted.")
    }, [student]);

    function setPresenceVariableToStudent(isPresent) {
        setPresenceToStudent(student["id"], isPresent)
            .then(() => {
                    fetchStudents();
                    message.success("La presenza di " + student["name"] + " " + student["surname"] + " è stato aggiornata");
                }
            ).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    }

    if (student["present"]) {
        return <>
            <Space>
                <Text>Presente in struttura: </Text>Si
                <Button onClick={() => setPresenceVariableToStudent(false)}>Disattiva presenza</Button>
            </Space>
        </>
    } else {
        return <>
            <Space>
                <Text>Presente in struttura: </Text>No
                <Button type="primary" onClick={() => setPresenceVariableToStudent(true)}>Attiva presenza</Button>
            </Space>
        </>
    }
}

export default SetStudentIsPresentButton;