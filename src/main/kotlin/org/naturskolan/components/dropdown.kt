package org.naturskolan.components

//<button id="dropdownDefaultButton" data-dropdown-toggle="dropdown" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
//Dropdown button
//<svg class="w-2.5 h-2.5 ms-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
//<path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4"/>
//</svg>
//</button>
//
//fun DIV.dropdown(id: String, placeholder: String, minDate: String? = null): Unit = div {
//    classes = setOf("relative max-w-sm")
//    div(classes = "absolute inset-y-0 start-0 flex items-center ps-3.5 pointer-events-none") {
//    }
//    input(type = InputType.text) {
//        attributes["id"] = id
//        classes = setOf(
//            "bg-gray-50",
//            "border",
//            "border-gray-300",
//            "text-gray-900",
//            "text-sm",
//            "rounded-lg",
//            "focus:ring-blue-500",
//            "focus:border-blue-500",
//            "block",
//            "w-full",
//            "ps-10",
//            "p-2.5",
//            "dark:bg-gray-700",
//            "dark:border-gray-600",
//            "dark:placeholder-gray-400",
//            "dark:text-white",
//            "dark:focus:ring-blue-500",
//            "dark:focus:border-blue-500"
//        )
//        attributes["x-init"] =
//            "new Datepicker(\$el, {language: 'sv', minDate: ${minDate.toAttributeString()}})"
//        attributes["placeholder"] = placeholder
//    }
//    inputField(id = id, type = InputType.text) {
//        attributes["x-init"] =
//            "new Datepicker(\$el, {language: 'sv', minDate: ${minDate.toAttributeString()}})"
//        attributes["placeholder"] = placeholder
//    }
//}