package kz.qwertukg

import com.google.gson.Gson
import org.openqa.selenium.Cookie
import org.openqa.selenium.remote.RemoteWebDriver

inline fun <reified T : Any> Cookie.typedValue(init: T.() -> Unit = {}): T {
    val value = Gson().fromJson(this.value, T::class.java)
    init(value)
    return value
}

fun RemoteWebDriver.cookieValue(key: String, init: Cookie.() -> Unit = {}): Cookie {
    val value = this.manage().getCookieNamed(key)
    value.init()
    return value
}

fun RemoteWebDriver.deleteCookie(key: String){
    this.manage().deleteCookieNamed(key)
}