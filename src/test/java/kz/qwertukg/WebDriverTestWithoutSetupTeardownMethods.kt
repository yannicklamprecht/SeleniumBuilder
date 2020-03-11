package kz.qwertukg

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

                    getLocalStorageValue<StorageData>("abc"){

                    }
                    println(getLocalStorageValue("_c;;i"))
                    getLocalStorageValue("_c;;i") {
                        println(this)
                    }

                    // https://www.google.com
                }
            }
        }.quit()

        assertTrue { result.contains("Kotlin Programming Language") }
    }
}