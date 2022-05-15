fun main() {
    var secondsAgo: Int //время отсутствия (секунд)
    while (true) {
        print("Введите время отсутствия в секундах или -1 для выхода: ")
        secondsAgo = readln().toInt()
        if (secondsAgo != -1)
            println(agoToText(secondsAgo))
        else {
            println("Программа завершена.")
            break
        }
    }
}

fun agoToText(seconds: Int): String {
    val textToOut = "был(а) " + when {
        seconds <= 60 -> "только что"
        seconds > 60 && seconds <= 60 * 60 -> minutesOrHoursText(seconds)
        seconds > 60 * 60 && seconds <= 24 * 60 * 60 -> minutesOrHoursText(seconds, false)
        seconds > 24 * 60 * 60 && seconds <= 48 * 60 * 60 -> "сегодня"
        seconds > 48 * 60 * 60 && seconds <= 72 * 60 * 60 -> "вчера"
        else -> "давно"
    }
    return textToOut
}

fun minutesOrHoursText(seconds: Int, minutes: Boolean = true): String {
    val minutOrHours = if (minutes) (seconds / 60).toInt() else (seconds / (60 * 60)).toInt()
    val compareNumber = minutOrHours % 100
    val text = when {
        (compareNumber >= 5 && compareNumber <= 20) || ((compareNumber % 10 != 1)
                && (compareNumber % 10 == 0 || compareNumber % 10 >= 5)) -> if (minutes) " минут" else " часов"
        (compareNumber % 10 > 1 && compareNumber % 10 <= 4) -> if (minutes) " минуты" else " часа"
        else -> if (minutes) " минуту" else " час"
    }
    return (if (minutes) "" else "в сети ") + minutOrHours.toString() + text + " назад"
}
