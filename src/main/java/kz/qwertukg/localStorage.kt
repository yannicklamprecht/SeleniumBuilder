package kz.qwertukg

import com.google.gson.Gson
import org.openqa.selenium.html5.WebStorage
import org.openqa.selenium.remote.RemoteWebDriver

inline fun <reified T : Any> RemoteWebDriver.localStorageValue(key: String, init: T.() -> Unit = {}): T? {
    val rawValue: String? = this.localStorageValue(key)
    rawValue?.let {
        val value = Gson().fromJson(rawValue, T::class.java)
        init(value)
        return value
    }
    return null
}

fun RemoteWebDriver.localStorageValue(key: String, init: String.() -> Unit = {}): String? {
    val value = when (this) {
        is WebStorage -> this.localStorage.getItem(key)
        else -> this.executeScript(String.format("return window.localStorage.getItem('%s');", key)) as String?
    }
    value?.init()
    return value
}