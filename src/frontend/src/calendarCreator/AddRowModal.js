import React, {useEffect, useState} from "react";
import {Button, Dropdown, Menu, Modal, Space, Typography} from "antd";
import {DownOutlined} from "@ant-design/icons";
import {useDispatch, useSelector} from "react-redux";
import {AddRowModalContent} from "./AddRowModalContent";
import {generateRowForTable, getTeacherById} from "../client";
import {addRowsData, freshPendingRow} from "../redux/slice/calendarCreator";
import {getRemainingTeacherToSchedule} from "../redux/redux";

const {Text} = Typography;

export function AddRowModal({showModal, setShowModal}) {
    const [selected, setSelected] = useState(false);
    // const teacherList = useSelector((state) => state.creation.teacherList);
    const [teacherList, setTeacherList] = useState([]);
    const pendingRow = useSelector((state) => state.creation.pendingRow);
    const [teacher, setTeacher] = useState();
    const dispatch = useDispatch();

    useEffect(() => {
        console.log("AddRowModal a");
        getRemainingTeacherToSchedule()
            .then(res => res.json())
            .then(data => {
                setTeacherList(data);
            });
    }, [showModal]);


    const fetchTeacherById = (teacherId) => {
        getTeacherById(teacherId)
            .then(res => res.json())
            .then(data => {
                setTeacher(data);
                console.log(data);
                setSelected(true);
            });
    }

    const menuItems = () => {
        let items = [];
        teacherList.forEach((teacher) => {
            items.push({
                key: teacher["id"], label: teacher["name"] + " " + teacher["surname"],
            });
        });
        return items;
    }

    const handleOkTeacherSelection = (obj) => {
        console.log("handleOkTeacherSelection clicked");
        console.log(obj["key"]);
        fetchTeacherById(obj["key"]);
        dispatch(freshPendingRow());
    }

    const dropdownMenu = (<Menu selectable
                                onClick={obj => handleOkTeacherSelection(obj)}
                                items={menuItems()}
    />);


    const dropdownButton = (
        <Dropdown overlay={dropdownMenu}>
            <Button>
                <Space>
                    Scegli il docente...
                    <DownOutlined/>
                </Space>
            </Button>
        </Dropdown>
    );


    const handleOk = () => {
        generateRowForTable(teacher, pendingRow)
            .then(res => res.json())
            .then(data => {
                dispatch(addRowsData(data));
                dispatch(freshPendingRow());
            });
        setSelected(false);
        setShowModal(false);
    };

    const handleCancel = () => {
        dispatch(freshPendingRow());
        setSelected(false);
        setShowModal(false);
    }

    function renderTitleWithTeacher() {
        // return ";
        return (<Text strong>Aggiungi riga, docente: {teacher["name"]} {teacher["surname"]}</Text>);
    }

    if (!selected) {
        return (<>
            <Modal title={<Text strong>Aggiungi riga</Text>} visible={showModal}
                   footer={<Button danger onClick={handleCancel}> Annulla operazione </Button>}
            >
                {dropdownButton}
            </Modal>
        </>);
    } else {
        return (<>
            <Modal title={renderTitleWithTeacher()} visible={showModal}
                   footer={<Button type="primary" onClick={handleOk}> Aggiungi la riga al calendario </Button> }
                   onCancel={handleCancel}
                   bodyStyle={{paddingTop: 8}}>
                <AddRowModalContent teacher={teacher}/>
            </Modal>
        </>);
    }

}