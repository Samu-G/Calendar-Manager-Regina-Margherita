import {Button, Input, message, Typography} from "antd";
import React, {useEffect, useState} from "react";
import {setEmailAddressToStudent, setFiscalCodeToStudent} from "../../../client";

const {Text} = Typography;

const SetStudentEmailAddressForm = ({student, fetchStudents}) => {
    const[emailAddress, setEmailAddress] = useState(student["emailAddress"]);

    useEffect(() => {
        console.log("SetStudentEmailAddressForm mounted")
    }, [student]);

    const handleChange = event => {
        setEmailAddress(event.target.value);
    }

    const handleClick = () => {
        if (emailAddress.match(/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/)) {
            setEmailAddressToStudent(student["id"], emailAddress)
                .then(() => {
                    fetchStudents();
                    message.success("L'indirizzo email di " + student["name"] + " " + student["surname"] + " è stato aggiornato");
                }).catch(() => {
                message.error("Errore nella comunicazione con il server");
            });
        } else {
            message.warn("L'indirizzo email inserito non è valido");
        }
    }

    return (
        <Input.Group compact>
            <span>
                <Text>Indirizzo email: </Text>
                <Input style={{width: 220, marginLeft: 2}} value={emailAddress} onChange={handleChange}/>
                <Button type="primary" onClick={handleClick}> Modifica > </Button>
            </span>
        </Input.Group>
    );
}

export default SetStudentEmailAddressForm;