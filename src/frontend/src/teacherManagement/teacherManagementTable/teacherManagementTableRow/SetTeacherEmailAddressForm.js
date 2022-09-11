import React, {useEffect, useState} from "react";
import {setEmailAddressToTeacher} from "../../../client";
import {Button, Input, message, Typography} from "antd";

const {Text} = Typography;

const SetTeacherEmailAddressForm = ({teacher, fetchTeachers}) => {
    const[emailAddress, setEmailAddress] = useState(teacher["emailAddress"]);

    useEffect(() => {
        console.log("SetTeacherEmailAddressForm mounted")
    }, [teacher]);

    const handleChange = event => {
        setEmailAddress(event.target.value);
    }

    const handleClick = () => {
        if (emailAddress.match(/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/)) {
            setEmailAddressToTeacher(teacher["id"], emailAddress)
                .then(() => {
                    fetchTeachers();
                    message.success("L'indirizzo email di " + teacher["name"] + " " + teacher["surname"] + " è stato aggiornato");
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

export default SetTeacherEmailAddressForm;