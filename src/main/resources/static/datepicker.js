"use strict";

document.addEventListener('alpine:init', () => {
    Alpine.data('datepicker', () => ({
        datePickerOpen: false,
        datePickerValue: '',
        datePickerMonth: 0,
        datePickerYear: '',
        datePickerDay: '',
        datePickerDaysInMonth: [],
        datePickerBlankDaysInMonth: [],
        datePickerMonthNames: ['januari', 'februari', 'mars', 'april', 'maj', 'juni', 'juli', 'augusti', 'september', 'oktober', 'november', 'december'],
        datePickerDays: ['sö', 'må', 'ti', 'on', 'to', 'fr', 'lö'],
        datePickerDayClicked(day) {
            let selectedDate = new Date(this.datePickerYear, this.datePickerMonth, day);
            this.datePickerDay = day;
            this.datePickerValue = this.datePickerFormatDate(selectedDate);
            this.datePickerIsSelectedDate(day);
            this.datePickerOpen = false;
        },
        datePickerPreviousMonth() {
            if (this.datePickerMonth === 0) {
                this.datePickerYear--;
                this.datePickerMonth = 12;
            }
            this.datePickerMonth--;
            this.datePickerCalculateDays();
        },
        datePickerNextMonth() {
            if (this.datePickerMonth === 11) {
                this.datePickerMonth = 0;
                this.datePickerYear++;
            } else {
                this.datePickerMonth++;
            }
            this.datePickerCalculateDays();
        },
        datePickerIsSelectedDate(day) {
            const d = new Date(this.datePickerYear, this.datePickerMonth, day);
            return this.datePickerValue === this.datePickerFormatDate(d);
        },
        datePickerIsToday(day) {
            const today = new Date();
            const d = new Date(this.datePickerYear, this.datePickerMonth, day);
            return today.toDateString() === d.toDateString();
        },
        datePickerCalculateDays() {
            let daysInMonth = new Date(this.datePickerYear, this.datePickerMonth + 1, 0).getDate();
            let dayOfWeek = new Date(this.datePickerYear, this.datePickerMonth).getDay();
            this.datePickerBlankDaysInMonth = Array.from({length: dayOfWeek}, (_, i) => i + 1);
            this.datePickerDaysInMonth = Array.from({length: daysInMonth}, (_, i) => i + 1);
        },
        datePickerFormatDate(date) {
            let formattedDate = ('0' + date.getDate()).slice(-2); // appends 0 (zero) in single digit date
            let formattedMonthInNumber = ('0' + (parseInt(date.getMonth()) + 1)).slice(-2);
            let formattedYear = date.getFullYear();
            return `${formattedYear}-${formattedMonthInNumber}-${formattedDate}`;
        },
    }))
})
