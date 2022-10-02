import {Button, Input, message, Typography} from "antd";
import React, {useEffect, useState} from "react";
import {setFiscalCodeToStudent} from "../client";

const {Text} = Typography;

const SetStudentFiscalCodeForm = ({student, fetchStudents}) => {
    const [fiscalCode, setFiscalCode] = useState(student["fiscalCode"]);

    useEffect(() => {
        console.log("SetStudentFiscalCodeForm mounted")
    }, [student]);

    const handleChange = event => {
        setFiscalCode(event.target.value);
    }

    const handleClick = () => {
        if (fiscalCode.match(/^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/)) {
            setFiscalCodeToStudent(student["id"], fiscalCode)
                .then(() => {
                    fetchStudents();
                    message.success("Il codice fiscale di " + student["name"] + " " + student["surname"] + " è stato aggiornato");
                }).catch(() => {
                message.error("Errore nella comunicazione con il server");
            });
        } else {
            message.error("Il codice fiscale inserito non è valido");
        }
    }

    return (
        <Input.Group compact>
            <span>
                <Text>Codice fiscale: </Text>
                <Input style={{width: 220, marginLeft: 2}} value={fiscalCode} onChange={handleChange}/>
                <Button type="primary" onClick={handleClick}> Modifica > </Button>
            </span>
        </Input.Group>
    );
}

export default SetStudentFiscalCodeForm;