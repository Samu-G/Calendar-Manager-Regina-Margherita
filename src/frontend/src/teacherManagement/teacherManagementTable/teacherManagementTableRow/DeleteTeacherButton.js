import {useEffect, useState} from "react";
import {removeTeacher} from "../../../client";
import {Button, message, Popconfirm, Typography} from "antd";
import {MinusOutlined, WarningOutlined} from "@ant-design/icons";

const {Text} = Typography;

const DeleteTeacherButton = ({teacher, fetchTeachers}) => {
    const [open, setOpen] = useState(false);

    useEffect(() => {
        console.log("DeleteStudentButton mounted.")
    }, []);

    const showPopConfirm = () => {
        setOpen(true);
    };

    const handleOk = () => {
        removeTeacher(teacher["id"])
            .then(() => {
                fetchTeachers();
                setOpen(false);
                message.success("Docente " + teacher["name"] + " " + teacher["surname"] + " rimosso con successo")
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    };

    const handleCancel = () => {
        setOpen(false);
    };

    function titleSetup() {
        return (<>
            <p>Sei sicuro di voler eliminare {teacher["name"]} {teacher["surname"]}?</p>
            <Text type="secondary">Verrà cancellato il docente dalla lista. Le statistiche degli studenti </Text>
            <br/>
            <Text type="secondary">riguardo a questo docente <Text strong> non </Text> verranno eliminate </Text> </>);
    }

    return (
        <>
            <Popconfirm
                title={titleSetup()} open={open}
                okText={"Si, cancella"} onConfirm={handleOk}
                cancelText={"Annulla"} onCancel={handleCancel}
                icon={<WarningOutlined style={{color: "red"}}/>}
            >
                <Button type="primary" danger shape={"round"} icon={<MinusOutlined/>}
                        onClick={showPopConfirm}>
                    Rimuovi {teacher["name"]} {teacher["surname"]}
                </Button>
            </Popconfirm>
        </>
    );
}

export default DeleteTeacherButton;