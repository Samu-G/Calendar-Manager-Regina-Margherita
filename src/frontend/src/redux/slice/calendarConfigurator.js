import {createSlice} from '@reduxjs/toolkit'

export const calendarConfigurator = createSlice({
    name: 'configuration',
    initialState: {
        date: "",
        dayName: "",
        beginTime: "08:00",
        endTime: "17:00",
        timeSlotDimension: 30,
    },
    reducers: {
        setDate: (state, action) => {
            state.date = action.payload
        },
        setDayName: (state, action) => {
            state.dayName = action.payload;
        },
        setBeginTime: (state, action) => {
            state.beginTime = action.payload;
        },
        setEndTime: (state, action) => {
            state.endTime = action.payload;
        },
        setTimeSlotDimension: (state, action) => {
            state.timeSlotDimension = action.payload;
        }
    }
});

export const {setDate, setDayName, setBeginTime, setEndTime, setTimeSlotDimension} = calendarConfigurator.actions;

export default calendarConfigurator.reducer;