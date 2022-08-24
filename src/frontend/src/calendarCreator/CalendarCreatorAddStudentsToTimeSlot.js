import {AutoComplete, Typography} from "antd";
import React, {useState} from "react";
import CalendarCreatorAddStudentToTimeSlotDynamic from "./CalendarCreatorAddStudentToTimeSlotDynamic";

const CalendarCreatorAddStudentsToTimeSlot = ({studentsWitchFollowSubjectTeached}) => {

    // Inizializzo la lista completa degli studenti per nome e cognome e codice fiscale
    const studentsByValue = [];

    function setStudents() {
        studentsWitchFollowSubjectTeached.forEach((student) => {
            studentsByValue.push({
                value: student["name"] + " " + student["surname"] + " (" + student["fiscalCode"] + ")",
            });
        });
    }

    setStudents();
    // Ok

    // Quando clicco sul nome di uno studente della lista, questo studente si aggiunge ai dati della lista che
    // mostra gli studenti selezionati
    const [studentiSelezionati, setStudentiSelezionati] = useState([]);

    const onChange = (value) => {
        studentiSelezionati.push(value);
        setStudentiSelezionati(studentiSelezionati);
        console.log(studentiSelezionati);
    }

    // Quando clicco sul nome di uno studente della lista, questo studente deve essere rimosso dalla lista degli
    // studenti che posso scegliere, ovvero studentsCanBeScheduledByNameAndSurname
    return (
        <>
            <CalendarCreatorAddStudentToTimeSlotDynamic
                studentsByValue={studentsByValue}
            />
            {/*<AutoComplete style={{width: 500, height: 50,}} options={studentsByValue}*/}
            {/*              placeholder="Cerca studente da inserire..."*/}
            {/*              filterOption={(inputValue, option) =>*/}
            {/*                  option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1*/}
            {/*              }*/}
            {/*              onChange={onChange}*/}
            {/*/>*/}
        </>
    );
}

export default CalendarCreatorAddStudentsToTimeSlot;