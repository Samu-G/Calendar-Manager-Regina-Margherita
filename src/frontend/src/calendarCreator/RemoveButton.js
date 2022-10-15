import {Button} from "antd";
import React from "react";
import {removeRowByIdAndRefreshRowsDataArray} from "../client";
import {useDispatch, useSelector} from "react-redux";
import {setRowsDataArray} from "../redux/slice/calendarCreator";


export function RemoveButton({record}) {

    let rowsDataArray = useSelector((state) => state.creation.rowsDataArray);
    const dispatch = useDispatch();

    function deleteRow(record) {
        console.log(rowsDataArray);
        removeRowByIdAndRefreshRowsDataArray(record["key"], rowsDataArray)
            .then(res => res.json())
            .then(data => {
                // console.log(data);
                dispatch(setRowsDataArray(data));
            });
    }

    return (
        <Button type="primary" danger
                onClick={() => {
                    deleteRow(record);
                }}
        > Rimuovi </Button>
    );
}