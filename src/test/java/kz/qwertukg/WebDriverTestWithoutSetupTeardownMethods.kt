package kz.qwertukg

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertTrue

class WebDriverTestWithoutSetupTeardownMethods {


    @Test
    fun `test chromeDriver, elementById, wait, elementVisibilityById, elementVisibilityBySelector functions on google`() {
        var result = ""

        chromeDriverWithOptions {
            setHeadless(true)
            // setBinary("c:/chromedriver.exe") // no need for mac os when installed with brew etc.
        }.use {
            get("http://google.com")

            elementByName("q") {
                sendKeys("kotlin")

                wait(10) {
                    elementVisibilityByName("btnK") {
                        click()
                    }

                    elementVisibilityBySelector("a[href = 'https://kotlinlang.org/']") {
                        result = text
                    }

                    localStorageValue<StorageData>("abc") {
                        Assert.assertEquals(42, lastValid)
                        Assert.assertEquals("someHeavyTestString", test)
                    }
                    println(localStorageValue("_c;;i"))
                    localStorageValue("_c;;i") {
                        println(this)
                    }

                    cookieValue("cookieKey") {
                        Assert.assertTrue(isSecure)
                        typedValue<SampleCookieData> {
                            Assert.assertEquals(42, valid)
                            Assert.assertEquals("someHeavyTestString", userName)
                        }
                    }
                    deleteCookie("cookieKey")
                }
            }
        }.quit()

        assertTrue { result.contains("Kotlin Programming Language") }
    }
}