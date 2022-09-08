import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    // convert non-2xx HTTP responses into errors:
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}

export const getAllStudents = () =>
    fetch("/api/admin/getAllStudent")
        .then(checkStatus);

export const getAllStudentsPresentOnMonday = () =>
    fetch("api/admin/getAllStudentsPresentOnMonday")
        .then(checkStatus)

export const getAllStudentsPresentOnTuesday = () =>
    fetch("api/admin/getAllStudentsPresentOnTuesday")
        .then(checkStatus)

export const getAllStudentsPresentOnWednesday = () =>
    fetch("api/admin/getAllStudentsPresentOnWednesday")
        .then(checkStatus)

export const getAllStudentsPresentOnThursday = () =>
    fetch("api/admin/getAllStudentsPresentOnThursday")
        .then(checkStatus)

export const getAllStudentsPresentOnFriday = () =>
    fetch("api/admin/getAllStudentsPresentOnFriday")
        .then(checkStatus)


export const getAllSubjects = () =>
    fetch("/api/admin/getAllSubjects")
        .then(checkStatus);

export const getAllTeachers = () =>
    fetch("/api/admin/getAllTeachers")
        .then(checkStatus);

export const getAllTeachersPresentOnMonday = () =>
    fetch("api/admin/getAllTeachersPresentOnMonday")
        .then(checkStatus)

export const getAllTeachersPresentOnTuesday = () =>
    fetch("api/admin/getAllTeachersPresentOnTuesday")
        .then(checkStatus)

export const getAllTeachersPresentOnWednesday = () =>
    fetch("api/admin/getAllTeachersPresentOnWednesday")
        .then(checkStatus)

export const getAllTeachersPresentOnThursday = () =>
    fetch("api/admin/getAllTeachersPresentOnThursday")
        .then(checkStatus)

export const getAllTeachersPresentOnFriday = () =>
    fetch("api/admin/getAllTeachersPresentOnFriday")
        .then(checkStatus)

export const saveSubjectByName = subjectName =>
    fetch("api/admin/saveSubjectByName", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(subjectName)
        }
    );

export const deleteSubjectById = subjectId =>
    fetch("api/admin/deleteSubjectById", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(subjectId)
        }
    );

export const deleteSubjectFromTheTeacher = (teacherId, subjectName) =>
    fetch("api/admin/deleteSubjectFromTheTeacher", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: teacherId, subjectName: subjectName})
        }
    );



export const addSubjectToTeacher = (teacherId, subjectName) =>
    fetch("api/admin/addSubjectToTeacher", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: teacherId, subjectName: subjectName})
        }
    );

export const setDayOfAttendanceToTeacherId = (teacherId, presenceDaysList) =>
    fetch("api/admin/setDayOfAttendanceToTeacher", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: teacherId, presenceDaysList: presenceDaysList})
        }
    );

export const setTimeSlotToTeacherId = (teacherId, dayName, timeSlot, checked) =>
    fetch("api/admin/setTimeSlotToTeacher", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: teacherId, dayName: dayName, timeSlot: timeSlot, checked: checked})
        }
    );

export const flipIsPresent = student =>
    fetch("api/admin/flipPresentToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(student)
        }
    );


export const setCurrentYearToStudent = (studentId, currentYear) =>
    fetch("api/admin/setCurrentYearToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: studentId, currentYear: currentYear})
        }
    );

/*New Function*/

export const addNewStudent = student =>
    fetch("api/admin/addStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(student)
        }
    ).then(checkStatus);

export const setFiscalCodeToStudent = (studentId, newFiscalCode) =>
    fetch("api/admin/setFiscalCodeToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, newFiscalCode: newFiscalCode})
        }
    ).then(checkStatus);

export const setEmailAddressToStudent = (studentId, newEmailAddress) =>
    fetch("api/admin/setEmailAddressToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, newEmailAddress: newEmailAddress})
        }
    ).then(checkStatus);

export const setPresenceToStudent = (studentId, isPresent) =>
    fetch("api/admin/setPresenceToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, isPresent: isPresent})
        }
    ).then(checkStatus);

export const getNameOfTheDaysOfPresenceFromStudent = (studentId) =>
    fetch("api/admin/getNameOfTheDaysOfPresenceFromStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId})
        }
    ).then(checkStatus);

export const setDaysOfPresenceToStudent = (studentId, daysList) =>
    fetch("api/admin/setDaysOfPresenceToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, daysList: daysList})
        }
    ).then(checkStatus);

export const removeStudent = (studentId) =>
    fetch("api/admin/removeStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId})
        }
    ).then(checkStatus);

export const getSubjectFollowedByTheStudent = (studentId) =>
    fetch("api/admin/getSubjectFollowedByTheStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId})
        }
    ).then(checkStatus);

export const getSubjectNotFollowedByTheStudent = (studentId) =>
    fetch("api/admin/getSubjectNotFollowedByTheStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId})
        }
    ).then(checkStatus);


export const removeSubjectFollowedByTheStudent = (studentId, subjectName) =>
    fetch("api/admin/removeSubjectFollowedByTheStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, subjectName: subjectName})
        }
    ).then(checkStatus);

export const addSubjectFollowedByTheStudent = (studentId, subjectName) =>
    fetch("api/admin/addSubjectFollowedByTheStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({studentId: studentId, subjectName: subjectName})
        }
    ).then(checkStatus);



/************/

export const addSubjectToStudent = (studentId, subjectName) =>
    fetch("api/admin/addSubjectToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: studentId, subjectName: subjectName})
        }
    );

export const addNewStudentAccount = account =>
    fetch("api/account/save", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(account)
        }
    );

export const loginAccount = login =>
    fetch("api/account/login", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(login)
        }
    );
