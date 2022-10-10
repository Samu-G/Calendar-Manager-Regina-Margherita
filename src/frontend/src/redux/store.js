import { configureStore } from "@reduxjs/toolkit";
import calendarConfigurator from "./slice/calendarConfigurator";
import calendarCreator from "./slice/calendarCreator";

export default configureStore({
    reducer: {
        configuration: calendarConfigurator,
        creation: calendarCreator,
    },
});
