package kz.qwertukg

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver

fun chromeDriverWithOptions(init: ChromeOptions.() -> Unit): RemoteWebDriver {
    val options = ChromeOptions()
    init(options)
    return ChromeDriver(options)
}

fun firefoxDriverWithOptions(init: FirefoxOptions.() -> Unit): RemoteWebDriver {
    val options = FirefoxOptions()
    init(options)
    return FirefoxDriver(options)
}


fun RemoteWebDriver.use(init: RemoteWebDriver.() -> Unit): RemoteWebDriver {
    this.init()
    return this
}