/*
 * Copyright (c) 2019 Simon Marquis
 */

import java.io.File

internal object Resources {

    fun resourceAsList(fileName: String): List<String> =
        File(Resources.javaClass.classLoader.getResource(fileName)!!.toURI())
            .readLines()

}