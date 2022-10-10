import {Content} from "antd/es/layout/layout";
import {Button, Divider, Dropdown, Menu, message, Space, TimePicker, Typography} from 'antd';
import React, {useEffect, useState} from 'react';
import {useSelector} from "react-redux";
import moment from "moment";
import {useDispatch} from "react-redux";
import {setDate, setDayName, setBeginTime, setEndTime, setTimeSlotDimension} from "../../redux/slice/calendarConfigurator";
import {DownOutlined} from "@ant-design/icons";
import {CalendarCreator} from "./CalendarCreator";

const {Text, Title} = Typography;

export function CalendarCreatorConfigurator({dateSelected, dayNameSelected}) {
    const date = useSelector((state) => state.configuration.date);
    const dayName = useSelector((state) => state.configuration.dayName);
    const beginTime = useSelector((state) => state.configuration.beginTime);
    const endTime = useSelector((state) => state.configuration.endTime);
    const timeSlotDimension = useSelector((state) => state.configuration.timeSlotDimension);
    const dispatch = useDispatch();

    const [buttonClicked, setButtonClicked] = useState(false);


    useEffect(() => {
        dispatch(setDate(dateSelected));
        dispatch(setDayName(dayNameSelected));
    }, []);

    const beginTimePicker = (<TimePicker defaultValue={moment(beginTime, 'HH:mm')}
                                         minuteStep={15}
                                         format={'HH:mm'}
                                         disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23]}
                                         suffixIcon={null}
                                         style={{width: 80}} showNow={false}
                                         onChange={(t, timeString) => {
                                             dispatch(setBeginTime(timeString));
                                             console.log(beginTime);
                                         }}
        />
    );

    const endTimePicker = (<TimePicker defaultValue={moment(endTime, 'HH:mm')}
                                       minuteStep={15}
                                       format={'HH:mm'}
                                       disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23]}
                                       suffixIcon={null}
                                       style={{width: 80}} showNow={false}
                                       onChange={(t, timeString) => {
                                           dispatch(setEndTime(timeString));
                                           console.log(beginTime);
                                       }}
        />
    );

    const button = (
        <Button type="primary" onClick={() => {
            if (beginTime < endTime) {
                setButtonClicked(true);
            } else {
                message.warn("Attenzione orario d'inizio maggiore dell'orario di fine")
            }
        }}> Continua con la creazione > </Button>
    );

    const onClick = ({key}) => {
        dispatch(setTimeSlotDimension(key));
    };

    const dropdownMenu = (
        <Menu selectable
              onClick={onClick}
              defaultSelectedKeys={['2']}
              items={[
                  {
                      key: '15',
                      label: '15 minuti',
                  },
                  {
                      key: '30',
                      label: '30 minuti',
                  },
                  {
                      key: '45',
                      label: '45 minuti',
                  },
                  {
                      key: '60',
                      label: '60 minuti',
                  },
              ]}
        />
    );

    const dropdown = (
        <Dropdown overlay={dropdownMenu}>
            <Button>
                <Space>
                    {timeSlotDimension} minuti
                    <DownOutlined/>
                </Space>
            </Button>
        </Dropdown>
    );

    if (!buttonClicked) {
        return (
            <>
                <Content style={{paddingLeft: 48, border: "2 solid red"}}>
                    <div style={{lineHeight: 0}}>
                        <Title level={5}>Configura il calendario</Title>
                        <Text type="secondary"> controlla che la configurazione per la creazione del calendario sia
                            corretta</Text>
                    </div>

                    <div style={{marginTop: 15, lineHeight: 3}}>
                        <Space>
                            <Text strong>Data selezionata: {dayName} {date} </Text>
                            <Divider type="vertical"/>
                            <Text>Orario di inizio: {beginTimePicker} </Text>
                            <Text>Orario di fine: {endTimePicker} </Text>
                            <Divider type="vertical"/>
                            <Text>Dimensioni fasce orarie: {dropdown} </Text>
                            <Divider type="vertical"/>
                            {button}
                        </Space>
                    </div>
                </Content>
            </>
        );
    } else {
        return <CalendarCreator/>
    }
}