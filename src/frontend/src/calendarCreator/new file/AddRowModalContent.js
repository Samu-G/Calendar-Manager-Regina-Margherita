import {Row, Tag, Typography} from "antd";
import {getTimeSlotFromTeacherById} from "../../client";
import {useEffect, useState} from "react";
import {TimeSlotEditor} from "./TimeSlotEditor";
import {useDispatch} from "react-redux";

const {Text} = Typography;

export function AddRowModalContent({teacher}) {
    const [timeSlot, setTimeSlot] = useState([]);
    const [fetched, setFetched] = useState(false);
    // const dispatch = useDispatch();

    function renderSubjectsTag() {
        let subjectsTag = [];
        teacher["subjectsTeached"].forEach((subject) => {
            subjectsTag.push(<Tag style={{marginLeft: 0}}> {subject["nameOfTheSubject"]} </Tag>);
        });
        return subjectsTag;
    }

    function renderTimeSlotEditor() {
        if (!fetched) {
            getTimeSlotFromTeacherById(teacher["id"])
                .then(res => res.json())
                .then(data => {
                    setTimeSlot(data);
                    setFetched(true);
                });
        }
    }

    function addTeacherInfoToPendingRow() {

    }

    useEffect(() => {
        addTeacherInfoToPendingRow();
        renderTimeSlotEditor();
    }, []);


    if (!fetched) {
        return (
            <>
                <Row>
                    <Text style={{marginRight: 5}}> Materie insegnate: </Text> {renderSubjectsTag()}
                </Row>
            </>
        );
    } else if (fetched) {
        let component = [];
        timeSlot.forEach((ts) => {
            component.push(<TimeSlotEditor teacher={teacher} timeSlot={ts} /> );
        })
        return (
            <>
                <Row>
                    <Text style={{marginRight: 5}}> Materie insegnate: </Text> {renderSubjectsTag()}
                </Row>
                {component}
            </>
        );
    }


}