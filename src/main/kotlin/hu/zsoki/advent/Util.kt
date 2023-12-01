package hu.zsoki.advent

import java.io.File

fun loadInput(fileName: String): File {
    return File(ClassLoader.getSystemResource(fileName).file)
}