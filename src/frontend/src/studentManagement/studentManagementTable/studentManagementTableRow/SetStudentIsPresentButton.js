import {Button, message, Typography} from "antd";
import React, {useEffect} from "react";
import {flipIsPresent, setPresenceToStudent} from "../../../client";

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
            <Text>Presente in struttura: </Text>
            Si <Button style={{marginLeft: 20}} onClick={() => setPresenceVariableToStudent(false)}>
            Disattiva presenza
        </Button>
        </>
    } else {
        return <>
            <Text>Presente in struttura: </Text>
            No <Button type="primary" style={{marginLeft: 20}} onClick={() => setPresenceVariableToStudent(true)}>
            Attiva presenza
        </Button>
        </>
    }
}

export default SetStudentIsPresentButton;