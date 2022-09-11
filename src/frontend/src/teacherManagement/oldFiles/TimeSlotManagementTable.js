import {Table, Switch, Card, Row, Col} from "antd";
import { CheckOutlined, CloseOutlined } from '@ant-design/icons';
import {setTimeSlotToTeacherId} from "../../client";


function TimeSlotManagementTable({teacher}) {

    function setDefaultDayOfPresenceList() {
        let list = [];
        if (teacher.mondayIsPresent) list.push({name: "Lunedi", key: 1})
        if (teacher.tuesdayIsPresent) list.push({name: "Martedi", key: 2})
        if (teacher.wednesdayIsPresent) list.push({name: "Mercoledi", key: 3})
        if (teacher.thursdayIsPresent) list.push({name: "Giovedi", key: 4})
        if (teacher.fridayIsPresent) list.push({name: "Venerdi", key: 5})
        return list;
    }

    let columns = [
        {
            title: "Espandi una riga per modificare le fasce orarie di disponibilità",
            dataIndex: "name",
            key: "name",
            responsive: ["lg"],
        },
    ];

    const dataSource = setDefaultDayOfPresenceList();


    function setTimeSlotToTeacher(dayName, timeSlot, checked) {
        console.log(teacher.id);
        console.log(dayName);
        console.log(timeSlot);
        console.log(checked);
        setTimeSlotToTeacherId(teacher.id, dayName, timeSlot, checked);
    }

    return (

        <Table
            size="small"
            style={{minWidth: 1000}}
            dataSource={dataSource}
            columns={columns}
            bordered
            pagination={false}
            expandable={{
                expandedRowRender: (dayName) => (
                    <div>
                        <p>{dayName.name}</p>
                        <Row>

                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="8:30 - 9:00" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "08:30-09:00", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="9:00 - 9:30" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "09:00-09:30", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="9:30 - 10:00" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "09:30-10:00", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="10:00 - 10:30" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "10:00-10:30", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="10:30 - 11:00" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "10:30-11:00", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="11:00 - 11:30" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "11:00-11:30", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                            <Col span={3} style={{margin: 5, textAlign: "center"}}>
                                <Card title="11:30 - 12:00" size="small">
                                    <Switch
                                        checkedChildren={<CheckOutlined/>}
                                        unCheckedChildren={<CloseOutlined/>}
                                        defaultChecked
                                        onChange={(checked) => {
                                            setTimeSlotToTeacher(dayName.name, "11:30-12:00", checked);
                                        }}
                                    />
                                </Card>
                            </Col>
                        </Row>

                    </div>
                )
            }}
        />

    );
}

export default TimeSlotManagementTable;