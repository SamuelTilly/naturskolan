package org.naturskolan.components

import kotlinx.html.*
import java.util.*

private fun FlowContent.datePickerIcon(): Unit =
    div(classes = "absolute top-0 right-0 px-3 py-2 cursor-pointer text-neutral-400 group-hover:text-neutral-500") {
        attributes["x-on:click"] =
            "datePickerOpen=!datePickerOpen; if(datePickerOpen){ \$refs.datePickerInput.focus() }"
        svg(classes = "w-6 h-6") {
            attributes["fill"] = "none"
            attributes["viewBox"] = "0 0 24 24"
            attributes["stroke"] = "currentColor"
            path {
                attributes["stroke-linecap"] = "round"
                attributes["stroke-linejoin"] = "round"
                attributes["stroke-width"] = "2"
                d = "M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
            }
        }
    }

private fun FlowContent.datePickerCalendar(): Unit = div(classes = "grid grid-cols-7 mb-3") {
    template {
        attributes["x-for"] = "blankDay in datePickerBlankDaysInMonth"
        div(classes = "p-1 text-sm text-center border border-transparent")
    }
    template {
        attributes["x-for"] = "(day, dayIndex) in datePickerDaysInMonth"
        attributes["x-bind:key"] = "dayIndex"
        div(classes = "px-0.5 mb-1 aspect-square") {
            div(classes = "flex items-center justify-center text-sm leading-none text-center rounded-full cursor-pointer h-7 w-7") {
                attributes["x-text"] = "day"
                attributes["x-on:click"] = "datePickerDayClicked(day)"
                attributes["x-bind:class"] = """
                    {
                        'bg-neutral-200': datePickerIsToday(day) == true, 
                        'text-gray-600 hover:bg-neutral-200': datePickerIsToday(day) == false && datePickerIsSelectedDate(day) == false,
                        'bg-green-800 text-white hover:bg-opacity-75': datePickerIsSelectedDate(day) == true
                    }
                """.trimIndent()
            }
        }
    }
}

private fun FlowContent.monthButton(block: BUTTON.() -> Unit): Unit =
    button(classes = "inline-flex p-1 transition duration-100 ease-in-out rounded-full cursor-pointer focus:outline-none focus:shadow-outline hover:bg-gray-100") {
        type = ButtonType.button
        block()
    }

fun FlowContent.datePicker(label: String = "Välj datum"): Unit {
    val id = UUID.randomUUID().toString()
    div {
        attributes["x-data"] = "datepicker"
        attributes["x-init"] = """
        currentDate = new Date();
        if (datePickerValue) {
            currentDate = new Date(Date.parse(datePickerValue));
        }
        datePickerMonth = currentDate.getMonth();
        datePickerYear = currentDate.getFullYear();
        datePickerDay = currentDate.getDay();
        datePickerValue = datePickerFormatDate( currentDate );
        datePickerCalculateDays();
    """.trimIndent()
        field(id = id, label = label) {
            fieldInput(type = InputType.text) {
                attributes["id"] = id
                attributes["x-ref"] = "datePickerInput"
                attributes["x-on:click"] = "datePickerOpen=!datePickerOpen"
//                    attributes["x-on:focus"] = "datePickerOpen=true"
                attributes["x-model"] = "datePickerValue"
                attributes["x-on:keydown.escape"] = "datePickerOpen=false"
                placeholder = label
                attributes["readonly"] = ""
            }
            datePickerIcon()
            div(classes = "absolute top-0 left-0 max-w-lg p-4 mt-12 antialiased bg-white border rounded-lg shadow w-[17rem] border-neutral-200/70") {
                attributes["x-show"] = "datePickerOpen"
                attributes["x-transition"] = ""
                attributes["x-on:click.away"] = "datePickerOpen = false"
                div(classes = "flex items-center justify-between mb-2") {
                    div {
                        span(classes = "text-lg font-bold text-gray-800") {
                            attributes["x-text"] = "datePickerMonthNames[datePickerMonth]"
                        }
                        span(classes = "ml-1 text-lg font-normal text-gray-600") {
                            attributes["x-text"] = "datePickerYear"
                        }
                    }
                    div {
                        monthButton {
                            attributes["x-on:click"] = "datePickerPreviousMonth()"
                            svg(classes = "inline-flex w-6 h-6 text-gray-400") {
                                attributes["fill"] = "none"
                                attributes["viewBox"] = "0 0 24 24"
                                attributes["stroke"] = "currentColor"
                                path {
                                    attributes["stroke-linecap"] = "round"
                                    attributes["stroke-linejoin"] = "round"
                                    attributes["stroke-width"] = "2"
                                    d = "M15 19l-7-7 7-7"
                                }
                            }
                        }
                        monthButton {
                            attributes["x-on:click"] = "datePickerNextMonth()"
                            svg(classes = "inline-flex w-6 h-6 text-gray-400") {
                                attributes["fill"] = "none"
                                attributes["viewBox"] = "0 0 24 24"
                                attributes["stroke"] = "currentColor"
                                path {
                                    attributes["stroke-linecap"] = "round"
                                    attributes["stroke-linejoin"] = "round"
                                    attributes["stroke-width"] = "2"
                                    d = "M9 5l7 7-7 7"
                                }
                            }
                        }
                    }
                }
                datePickerCalendar()
            }
        }
    }
}
