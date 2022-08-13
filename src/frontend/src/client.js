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

export const getAllSubjects = () =>
    fetch("api/admin/getAllSubjects")
        .then(checkStatus);

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

export const addSubjectToStudent = (studentId, subjectName) =>
    fetch("api/admin/addSubjectToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: studentId, subjectName: subjectName})
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

export const setDayOfPresentToStudent = (studentId, day) =>
    fetch("api/admin/setDayOfPresentToStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({id: studentId, day: day})
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


export const addNewStudent = student =>
    fetch("api/admin/addStudent", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(student)
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
