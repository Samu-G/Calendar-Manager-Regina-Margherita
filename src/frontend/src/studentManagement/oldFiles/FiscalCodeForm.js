import {Button, Input, message} from "antd";
import React, {useState} from "react";
import {setFiscalCodeToStudent} from "../../client";


function FiscalCodeForm({student, fetchStudents}) {

    const [fiscalCode, setFiscalCode] = useState(student.fiscalCode)

    const handleChange = event => {
        setFiscalCode(event.target.value);
    }

    const handleClick = () => {
        setFiscalCodeToStudent(
            student.id,
            fiscalCode
        ).then(() => {
            fetchStudents();
            message.success("Il codice fiscale di " + student.name + " " + student.surname + " è stato aggiornato")
        })
    }

    return (
        <Input.Group compact>
            <span>Codice fiscale:
                <Input
                    style={{
                        width: 320,
                        marginLeft: 20
                    }}
                    value={fiscalCode}
                    onChange={handleChange}
                />
                <Button type="primary" onClick={handleClick}>Modifica</Button>
            </span>
        </Input.Group>
    );
}

export default FiscalCodeForm;