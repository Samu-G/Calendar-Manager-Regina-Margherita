import {useEffect, useState} from "react";
import {
    addSubjectTeachByTeacher,
    getSubjectByTeacher,
    getSubjectNotTeachByTeacher,
    removeSubjectTeachByTeacher
} from "../../../client";
import {AutoComplete, message, Tag} from "antd";
import {TweenOneGroup} from "rc-tween-one";


const SetTeacherSubjectTag = ({teacher, fetchTeachers}) => {
    const [tags, setTags] = useState([]);
    const [options, setOptions] = useState([]);

    const setupTags = () => {
        getSubjectByTeacher(teacher["id"])
            .then(res => res.json())
            .then(data => {
                setTags(data);
            }).catch(() => {
            message.error("Errore nella comunicazione con il server");
        });
    }

    const setupOptions = () => {
        let subjectNotFollowed = [];
        getSubjectNotTeachByTeacher(teacher["id"])
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
        console.log("SetStudentSubjectFollowedTag mounted.");
    }, [teacher]);

    const handleClose = (removedTag) => {
        console.log(removedTag);
        removeSubjectTeachByTeacher(teacher["id"], removedTag).then(() => {
            fetchTeachers();
            message.success("Materia rimossa");
        })
    };

    const onSelect = (value, option) => {
        addSubjectTeachByTeacher(teacher["id"], value).then(() => {
            fetchTeachers();
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

export default SetTeacherSubjectTag;