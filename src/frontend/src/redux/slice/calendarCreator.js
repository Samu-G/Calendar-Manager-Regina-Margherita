import {createSlice} from '@reduxjs/toolkit'

export const calendarCreator = createSlice({
    name: 'creation',
    initialState: {
        teacherList: [],
        studentList: [],
        columnsArray: [],
        rowsDataArray: [],
        pendingRow: [],

    },
    reducers: {
        setTeacherList: (state, action) => {
            state.teacherList = action.payload;
        },
        setStudentList: (state, action) => {
            state.studentList = action.payload;
        },
        setColumnsArray: (state, action) => {
            state.columnsArray = action.payload;
        },
        addRowsData: (state, action) => {
            state.rowsDataArray.push(action.payload);
        },
        addPendingRow: (state, action) => {
            state.pendingRow.push(action.payload);
        },
        removePendingRow: (state, action) => {
            let indexToRemove = state.pendingRow.indexOf(action);
            state.pendingRow.splice(indexToRemove, 1);
        },
        freshPendingRow: (state, action) => {
            state.pendingRow = [];
        },
        setRowsDataArray: (state, action) => {
            state.rowsDataArray = action.payload;
        }
    }
});

export const {
    setTeacherList,
    setStudentList,
    setColumnsArray,
    addRowsData,
    addPendingRow,
    removePendingRow,
    freshPendingRow,
    setRowsDataArray
} = calendarCreator.actions;

export default calendarCreator.reducer;