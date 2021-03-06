package kz.qwertukg

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertTrue

class WebDriverKtTest {

    private lateinit var driver: RemoteWebDriver

    @Before
    fun setup(){
        driver = chromeDriverWithOptions {
            setHeadless(true)
            // setBinary("c:/chromedriver.exe") // no need for mac os when installed with brew etc.
        }
    }


    @Test
    fun `test chromeDriver, elementById, wait, elementVisibilityById, elementVisibilityBySelector functions on google`() {
        var result = ""

        driver.use {
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
                }
            }
        }

        assertTrue { result.contains("Kotlin Programming Language") }
    }

    @After
    fun teardown(){
        driver.quit()
    }
}