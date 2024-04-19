import React from "react";
import DatePicker from "react-datepicker";

const DatePickerInput = ({ className, value, onChange }) => {
  const handleChange = (date) => {
    onChange && onChange(date.toISOString().split("T")[0]);
  };
  return (
    <>
      <div className="flex flex-col py-2">
        <DatePicker
          className={`${className} mb-2 h-12 w-full rounded-md border-2 border-blue-300 px-2 disabled:border-blue-200`}
          selected={value}
          onChange={handleChange}
          dateFormat="yyyy-MM-dd"
          minDate={new Date()}
        />
      </div>
    </>
  );
};

export default DatePickerInput;
