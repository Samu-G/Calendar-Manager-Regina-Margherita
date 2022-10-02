import {Button, Modal, Typography} from 'antd';
import {useEffect} from "react";
import TeacherTimeSlotListByDay from "./teacherManagementModal/TeacherTimeSlotListByDay";

const {Text} = Typography;

const SetTeacherTimeSlotsModal = ({isModalVisible, setIsModalVisible, currentTeacher}) => {
    useEffect(() => {
        console.log("SetTeacherTimeSlotsModal mounted");
    }, []);

    const handleClose = () => {
        setIsModalVisible(false);
    };

    function generateTitle() {
        return "Fascie orarie di disponibilità per il docente " + currentTeacher["name"] + " " + currentTeacher["surname"];
    }

    return (
        <>
            <Modal title={generateTitle()} visible={isModalVisible} closable={false}
                   footer={<Button type="primary" onClick={handleClose} block> Salva e chiudi </Button>}
                   width={650} bodyStyle={{paddingTop: 7}}>
                <Text type="secondary">Da questa finestra puoi specificare le fasce orarie di disponibilità del
                    docente {currentTeacher["name"]} {currentTeacher["surname"]}. Le fasce orarie spuntate verranno poi
                    mostrate durante la creazione del calendario, mentre quelle non spuntate renderanno il docente non
                    disponibile. </Text>
                <TeacherTimeSlotListByDay dayName={"Lunedì"} currentTeacher={currentTeacher}/>
                <TeacherTimeSlotListByDay dayName={"Martedì"} currentTeacher={currentTeacher}/>
                <TeacherTimeSlotListByDay dayName={"Mercoledì"} currentTeacher={currentTeacher}/>
                <TeacherTimeSlotListByDay dayName={"Giovedì"} currentTeacher={currentTeacher}/>
                <TeacherTimeSlotListByDay dayName={"Venerdì"} currentTeacher={currentTeacher}/>
            </Modal>
        </>
    );
}

export default SetTeacherTimeSlotsModal;