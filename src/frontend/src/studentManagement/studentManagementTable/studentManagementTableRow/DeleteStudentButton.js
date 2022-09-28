import {Button, message, Popconfirm, Typography} from "antd";
import {MinusOutlined, WarningOutlined} from "@ant-design/icons";
import {useEffect, useState} from "react";
import {removeStudent} from "../../../client";

const {Text} = Typography;

const DeleteStudentButton = ({student, fetchStudents}) => {
    const [open, setOpen] = useState(false);

    useEffect(() => {
        console.log("DeleteStudentButton mounted.")
    }, []);

    const showPopConfirm = () => {
        setOpen(true);
    };

    const handleOk = () => {
        removeStudent(student["id"])
            .then(() => {
                fetchStudents();
                setOpen(false);
                message.success("Studente " + student["name"] + " " + student["surname"] + " rimosso con successo" )
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        })
    };

    const handleCancel = () => {
        setOpen(false);
    };

    function titleSetup() {
        return (<>
            <p>Sei sicuro di voler eliminare {student["name"]} {student["surname"]}?</p>
            <Text type="secondary"> Verrà cancellato lo studente dalla lista. Le statistiche riguardo <Text>
                <br/>
            </Text> questo studente <Text strong> non </Text> verranno eliminate </Text> </>);
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
                    Rimuovi {student["name"]} {student["surname"]}
                </Button>
            </Popconfirm>
        </>
    );
}

export default DeleteStudentButton;