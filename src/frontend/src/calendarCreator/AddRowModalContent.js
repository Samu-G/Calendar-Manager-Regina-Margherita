import {Button, Row, Tag, Typography} from "antd";
import {generateAndGetTimeSlotByTeacherId} from "../client";
import {useState} from "react";
import {TimeSlotEditor} from "./TimeSlotEditor";

const {Text} = Typography;

export function AddRowModalContent({teacher}) {
    const [timeSlot, setTimeSlot] = useState([]);
    const [fetched, setFetched] = useState(false);

    function renderSubjectsTag() {
        let subjectsTag = [];
        teacher["subjectsTeached"].forEach((subject) => {
            subjectsTag.push(<Tag style={{marginLeft: 0}}> {subject["nameOfTheSubject"]} </Tag>);
        });
        return subjectsTag;
    }

    function renderTimeSlotEditor() {
        if (!fetched) {
            generateAndGetTimeSlotByTeacherId(teacher["id"])
                .then(res => res.json())
                .then(data => {
                    setTimeSlot(data);
                    setFetched(true);
                });
        }
    }

    if (!fetched) {
        return (
            <>
                <Button onClick={() => {
                    renderTimeSlotEditor();
                }}>renderTimeSlotEditor()</Button>
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