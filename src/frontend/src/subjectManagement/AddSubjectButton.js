import {PlusOutlined} from "@ant-design/icons";
import {Button, Input, message, Popover, Space} from "antd";
import React, {useState} from "react";
import {addSubject} from "../client";

const AddSubjectButton = ({subjectsList, fetchSubjects}) => {
    const [open, setOpen] = useState(false);
    const [newSubjectName, setNewSubjectName] = useState("");

    const hide = () => {
        setOpen(false);
    };

    const handleOpenChange = (newOpen) => {
        setOpen(newOpen);
    };

    const onChange = (e) => {
        setNewSubjectName(e.target.value);
    }

    const onClick = () => {
        if (newSubjectName.length === 0) {
            message.warn("Nessun nome della nuova materia inserito");
        } else {
            let subjectNormalized = newSubjectName.charAt(0).toUpperCase() + newSubjectName.slice(1);
            let nameAlreadyUsedFound = false;
            subjectsList.forEach((subject) => {
                if (subject["nameOfTheSubject"] === subjectNormalized) {
                    nameAlreadyUsedFound = true;
                }
            });
            if (nameAlreadyUsedFound) {
                message.warn("La materia " + subjectNormalized + " è già nella lista");
            } else {
                addSubject(subjectNormalized)
                    .then(() => {
                        fetchSubjects();
                        message.success("Nuova materia " + subjectNormalized + " inserita con successo");
                    });
            }
        }
    }

    const content = (
            <>
                <Space direction={"horizontal"}>
                    <Input placeholder="nome materia" onChange={onChange}/>
                    <Button type="primary" onClick={onClick}> Inserisci > </Button>
                </Space>
            </>
        )
    ;

    return (
        <>
            <Popover
                content={content}
                title="Inserici il nome della nuova materia"
                trigger="click"
                open={open}
                onOpenChange={handleOpenChange}
                placement={"bottomLeft"}
            >
                <Button
                    type="primary"
                    shape="round"
                    icon={<PlusOutlined/>}
                    size="small">
                    Aggiungi una nuova materia
                </Button>
            </Popover>
        </>
    );
}

export default AddSubjectButton;