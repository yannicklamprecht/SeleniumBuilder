package kz.qwertukg

import com.google.gson.Gson
import org.openqa.selenium.remote.RemoteWebDriver

inline fun <reified T : Any> RemoteWebDriver.localStorageValue(key: String, init: T.() -> Unit = {}): T {
    val value = Gson().fromJson(this.localStorageValue(key), T::class.java)
    init(value)
    return value
}

fun RemoteWebDriver.localStorageValue(key: String, init: String.() -> Unit = {}): String {
    val value = this.executeScript(String.format(
            "return window.localStorage.getItem('%s');", key)) as String
    value.init()
    return value
}