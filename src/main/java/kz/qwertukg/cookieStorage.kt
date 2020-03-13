package kz.qwertukg

import com.google.gson.Gson
import org.openqa.selenium.Cookie
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.*

inline fun <reified T : Any> Cookie.typedValue(init: T.() -> Unit = {}): T {
    val value = Gson().fromJson(this.value, T::class.java)
    init(value)
    return value
}

fun RemoteWebDriver.getCookieValue(key: String, init: Cookie.() -> Unit = {}): Cookie? {
    val value: Cookie? = this.manage().getCookieNamed(key)
    value?.init()
    return value
}

fun Any.toCookie(
        key: String,
        path: String? = null,
        domain: String? = null,
        expiry: Date? = null,
        isSecure: Boolean = false,
        isHttpOnly: Boolean = false
): Cookie {
    return Cookie(key, Gson().toJson(this), path, domain, expiry, isSecure, isHttpOnly)
}


fun RemoteWebDriver.setCookieValue(cookie: Cookie){
    this.manage().addCookie(cookie)
}

fun RemoteWebDriver.setCookieValue(key: String, value: String, init: Cookie.Builder.() -> Unit = {}): Cookie? {
    val cookieBuilder = Cookie.Builder(key, value)
    cookieBuilder.init()

    val cookie = cookieBuilder.build()
    this.manage().addCookie(cookie)

    return cookie
}

fun RemoteWebDriver.deleteCookie(cookie: Cookie) = deleteCookie(cookie.name)

fun RemoteWebDriver.deleteCookie(key: String) {
    this.manage().deleteCookieNamed(key)
}

fun RemoteWebDriver.deleteCookies() {
    this.manage().deleteAllCookies()
}