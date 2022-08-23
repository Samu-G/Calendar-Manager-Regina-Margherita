import {DatePicker} from "antd";
import moment from 'moment';
import {useEffect} from "react";


const DateSelector = ({setDate, setDateSelected}) => {
    const dateFormat = "DD/MM/YYYY";
    const defaultDate = moment().add(1, 'days');

    useEffect(() => {
        console.log("data selector loaded");
    }, []);

    return (
        <DatePicker
            defaultValue={moment()}
            format={dateFormat}
            onChange={(date, dateString) => {
                setDate(dateString);
                setDateSelected(true);
            }}
        />
    );
}

export default DateSelector;