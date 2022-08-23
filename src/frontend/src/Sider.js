import React from "react";
import {Menu, Layout} from "antd";
import {
    BarChartOutlined,
    CalendarOutlined,
    CarryOutOutlined,
    CommentOutlined, ExperimentOutlined,
    MailOutlined,
    SolutionOutlined,
    TeamOutlined
} from "@ant-design/icons";


export default function Sider(props) {
    const {handleClick} = props;
    return (
        <Layout.Sider>
            <Menu theme="dark"
                  mode="inline"
                /*openKeys={"1"}*/
                  defaultSelectedKeys={['1']}
            >
                <Menu.Item key="1" icon={<CalendarOutlined/>} onClick={handleClick}>
                    Crea calendario
                </Menu.Item>
                <Menu.Item key="2" icon={<CarryOutOutlined/>} onClick={handleClick}>
                    Calendari creati
                </Menu.Item>
                <Menu.Item key="3" icon={<BarChartOutlined/>} onClick={handleClick}>
                    Statistiche
                </Menu.Item>
                <Menu.Item key="4" icon={<TeamOutlined/>} onClick={handleClick}>
                    Studenti
                </Menu.Item>
                <Menu.Item key="5" icon={<SolutionOutlined/>} onClick={handleClick}>
                    Docenti
                </Menu.Item>
                <Menu.Item key="6" icon={<ExperimentOutlined />} onClick={handleClick}>
                    Materie
                </Menu.Item>
            </Menu>
        </Layout.Sider>
    );
}
