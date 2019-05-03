package hossein.gheisary.imdbfilms.utility.extensions

fun String.toIntOrZero() : Int {
    var value = 0
    justTry {
        value = this.toInt()
    }
    return value
}


fun String.toBoolean(): Boolean {
    return this != "" &&
            (this.equals("TRUE", ignoreCase = true)
            || this.equals("Y", ignoreCase = true)
            || this.equals("YES", ignoreCase = true))
}

fun String.convertToCamelCase(): String {
    var titleText = ""
    if (!this.isEmpty()) {
        val words = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        words.filterNot { it.isEmpty() }
                .map { it.substring(0, 1).toUpperCase() + it.substring(1).toLowerCase() }
                .forEach { titleText += it + " " }
    }
    return titleText.trim { it <= ' ' }
}

fun String.IsNotEmpty(): Boolean {
    return  (this!= null && this.isNotEmpty())
}