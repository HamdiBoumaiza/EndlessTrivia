package com.hb.endlesstrivia.data.network

/**
 * https://github.com/khaledymoh/Covid-19/blob/master/data/src/main/java/io/covid19/data/network/ResultException.kt
 */
sealed class DataSourceException(
    val messageResource: Int,
    message: String = ""
) : RuntimeException(message) {

    class Connection(messageResource: Int) : DataSourceException(messageResource)

    class Unexpected(messageResource: Int) : DataSourceException(messageResource)

    class Timeout(messageResource: Int) : DataSourceException(messageResource)

    class Client(messageResource: Int) : DataSourceException(messageResource)

    class Server(messageResource: Int) : DataSourceException(messageResource)
}