import {Divider, Modal, Typography} from 'antd';
import SetTeacherTimeSlotByDay from "./teacherManagementModal/SetTeacherTimeSlotByDay";
import {useEffect} from "react";

const {Text} = Typography;


const SetTeacherTimeSlotsModal = ({isModalVisible, setIsModalVisible, currentTeacher}) => {
    useEffect(() => {
        console.log("SetTeacherTimeSlotsModal mounted");
    }, []);

    const handleOk = () => {
        setIsModalVisible(false);
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    function generateTitle() {
        return "Fascie orarie di disponibilità per il docente " + currentTeacher["name"] + " " + currentTeacher["surname"];
    }

    return (
        <>
            <Modal title={generateTitle()} visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}
                   width={800}>
                <Text type="secondary">Da questa finestra puoi specificare le fasce orarie di disponibilità del
                    docente {currentTeacher["name"]} {currentTeacher["surname"]}. Le fasce orarie spuntate verranno poi
                    mostrate durante la creazione del calendario, mentre quelle non spuntate renderanno il docente non
                    disponibile. </Text>
                <br/>
                <SetTeacherTimeSlotByDay dayName={"Lunedì"} currentTeacher={currentTeacher}/>
                <SetTeacherTimeSlotByDay dayName={"Martedì"} currentTeacher={currentTeacher}/>
                <SetTeacherTimeSlotByDay dayName={"Mercoledì"} currentTeacher={currentTeacher}/>
                <SetTeacherTimeSlotByDay dayName={"Giovedì"} currentTeacher={currentTeacher}/>
                <SetTeacherTimeSlotByDay dayName={"Venerdì"} currentTeacher={currentTeacher}/>
            </Modal>
        </>
    );
}

export default SetTeacherTimeSlotsModal;