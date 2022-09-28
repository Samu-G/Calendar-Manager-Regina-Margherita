import {Descriptions, PageHeader} from "antd";
import React, {useEffect} from "react";


const SubjectManagementPageHeader = ({subjectList}) => {
    useEffect(() => {
        console.log("SubjectManagementPageHeader mounted.");
    }, [subjectList]);

    const title = "Gestisci le materie";
    const Descriptions1Label = "Numero totale di materie";
    const DescriptionsStyle = {paddingBottom: 0};

    return (
        <PageHeader ghost={false} title={title}>
            <Descriptions size="small" column={1}>
                <Descriptions.Item label={Descriptions1Label} style={DescriptionsStyle}> {subjectList.length} </Descriptions.Item>
            </Descriptions>
        </PageHeader>
    );
}

export default SubjectManagementPageHeader;