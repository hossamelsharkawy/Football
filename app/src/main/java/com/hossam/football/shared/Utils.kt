package com.hossam.football.shared

class DataFetchException(message: String?) : Exception(message)
class NetworkException(message: String?) : Exception(message)

fun <T> fakeList(count: Int = 15, action: (Int) -> T): List<T> {
    return arrayListOf<T>().apply {
        repeat(count) {
            add(action(it))
        }
    }

}
