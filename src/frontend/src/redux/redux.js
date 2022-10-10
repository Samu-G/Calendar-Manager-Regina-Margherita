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


// Importare nello state la lista dei docenti calendarizzabili.
// Cosa ci serve? Il nome del giorno.
// Cosa deve ritornare? Una lista dei docenti presenti quel giorno.
export const fetchSchedulableTeacherByDayName = dayName =>
    fetch("api/admin/fetchSchedulableTeacher", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({dayName: dayName})
    }).then(checkStatus);

export const fetchSchedulableTeacherInCalendar = () =>
    fetch("api/admin/fetchSchedulableTeacherInCalendar", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST'
    }).then(checkStatus);

export const fetchSchedulableStudentByDayName = dayName =>
    fetch("api/admin/fetchSchedulableStudent", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({dayName: dayName})
    }).then(checkStatus);

export const createColumnArrayByBeginTimeEndTimeDuration = (dayName, beginTime, endTime, duration) =>
    fetch("api/admin/createColumnArrayByBeginTimeEndTimeDuration", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({dayName: dayName, beginTime: beginTime, endTime: endTime, duration: duration})
    }).then(checkStatus);

