import React, {useEffect, useState} from "react";
import {Button, Popover, Space} from "antd";
import {deleteSubjectById, isSubjectDeletable} from "../client";
import {MinusOutlined} from "@ant-design/icons";

const DeleteSubjectButton = ({subject, fetchSubjects}) => {

    const [isDeletableSubject, setIsDeletableSubject] = useState(false);

    const isDeletableButton = () => {
        isSubjectDeletable(subject.id)
            .then(res => res.json())
            .then(data => {
                setIsDeletableSubject(data);
            });
    }

    useEffect(() => {
        isDeletableButton();
        console.log(isDeletableSubject);
        console.log("DeleteSubjectButton mounted");
    }, [subject]);


    const deletableSubjectButton = (
        <Space size="middle">
            <Button type="primary" danger onClick={onDeleteClick} shape={"round"} icon={<MinusOutlined/>}> Cancella la materia </Button>
        </Space>
    );

    const notDeletableSubjectButton = (
        <Space size="middle">
            <Popover placement="left"
                     content="Non puoi eliminare la materia perché è insegnata o seguita da qualche utente. Rimuovi prima la materia dai docenti e studenti"
                     trigger="hover">
                <Button danger disabled shape={"round"} icon={<MinusOutlined/>}> Cancella la materia </Button>
            </Popover>
        </Space>
    );


    function onDeleteClick(e) {
        deleteSubjectById(e.id)
            .then(() => {
                fetchSubjects();
            });
    }


    function renderButton() {
        if (isDeletableSubject) {
            console.log("deletableSubjectButton");
            return deletableSubjectButton;
        } else {
            console.log("notDeletableSubjectButton");
            return notDeletableSubjectButton;
        }
    }

    return renderButton();

}

export default DeleteSubjectButton;
