package kz.qwertukg

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertTrue

class WebDriverWithoutSetupTest {


    @Test
    fun `test chromeDriver, elementById, wait, elementVisibilityById, elementVisibilityBySelector functions on google`() {
        var result = ""

        chromeDriverWithOptions {
            //setHeadless(true)
            // setBinary("c:/chromedriver.exe") // no need for mac os when installed with brew etc.
        }.use {
            get("https://google.com")

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

                    setCookieValue(
                            SampleCookieData(
                                    42,
                                    "someHeavyTestString"
                            ).toCookie("cookieKey")
                    )

                    val cookie = getCookieValue("cookieKey")

                    Assert.assertNotNull(cookie)
                    cookie!!.typedValue<SampleCookieData> {
                        Assert.assertEquals(42, valid)
                        Assert.assertEquals("someHeavyTestString", userName)
                    }

                    deleteCookie("cookieKey")
                    Assert.assertNull(getCookieValue("cookieKey"))
                }
            }
        }.quit()

        assertTrue { result.contains("Kotlin Programming Language") }
    }
}