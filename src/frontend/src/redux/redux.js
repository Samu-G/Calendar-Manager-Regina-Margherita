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

export const createCalendar = (date, dayName, beginTime, endTime, timeSlotDimension) =>
    fetch("api/admin/createCalendar", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            date: date,
            dayName: dayName,
            beginTime: beginTime,
            endTime: endTime,
            timeSlotDimension: timeSlotDimension
        })
    }).then(checkStatus);


export const getRemainingTeacherToSchedule = () =>
    fetch("api/admin/getRemainingTeacherToSchedule", {
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

export const createJsonArrayForColumn = () =>
    fetch("api/admin/createJsonArrayForColumn", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
    }).then(checkStatus);

