import {TweenOneGroup} from 'rc-tween-one';
import {AutoComplete, message, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import {useEffect, useState} from "react";
import {
    addSubjectFollowedByTheStudent,
    getSubjectFollowedByTheStudent,
    getSubjectNotFollowedByTheStudent,
    removeSubjectFollowedByTheStudent
} from "../../../client";


const SetSubjectFollowedTag = ({student, fetchStudents}) => {
    const [tags, setTags] = useState([]);
    const [options, setOptions] = useState([]);

    const setupTags = () => {
        getSubjectFollowedByTheStudent(student["id"])
            .then(res => res.json())
            .then(data => {
                setTags(data);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    const setupOptions = () => {
        let subjectNotFollowed = [];
        getSubjectNotFollowedByTheStudent(student["id"])
            .then(res => res.json())
            .then(data => {
                data.forEach((subject) => {
                    subjectNotFollowed.push({value: subject});
                })
                setOptions(subjectNotFollowed);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    useEffect(() => {
        setupTags();
        setupOptions();
        console.log("SetSubjectFollowedTag mounted.");
    }, [student]);

    const handleClose = (removedTag) => {
        console.log(removedTag);
        removeSubjectFollowedByTheStudent(student["id"], removedTag).then(() => {
            fetchStudents();
            message.success("Materia rimossa");
        })
    };

    const onSelect = (value, option) => {
        addSubjectFollowedByTheStudent(student["id"], value).then(() => {
            fetchStudents();
            message.success("Materia aggiunta");
        }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    const forMap = (tag) => {
        const tagElem = (
            <Tag closable onClose={(e) => {
                e.preventDefault();
                handleClose(tag);
            }} style={{marginBottom: 5, fontSize: 14}}> {tag} </Tag>);
        return (<span key={tag} style={{display: 'inline-block',}}> {tagElem} </span>
        );
    };

    const tagChild = tags.map(forMap);

    return (
        <>
            <div style={{maxWidth: 600, marginBottom: 8}}>
                <TweenOneGroup
                    enter={{scale: 0.8, opacity: 0, type: 'from', duration: 100,}}
                    onEnd={(e) => {
                        if (e.type === 'appear' || e.type === 'enter') {
                            e.target.style = 'display: inline-block';
                        }
                    }}
                    leave={{opacity: 0, width: 0, scale: 0, duration: 200,}}
                    appear={false}
                >
                    {tagChild}
                </TweenOneGroup>
            </div>

            <AutoComplete
                style={{width: 200,}}
                options={options}
                placeholder="aggiungi una materia"
                filterOption={(inputValue, option) =>
                    option.value.toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
                }
                onSelect={onSelect}
            />

        </>
    );
}

export default SetSubjectFollowedTag;