# SeleniumBuilder
DSL for Selenium. Provide a possibility to write tests in [Kotlin type-safe builders](https://kotlinlang.org/docs/reference/type-safe-builders.html#a-type-safe-builder-example) style

## Sample
```kotlin
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
```

## Total function list

#### Driver builders

`driver(driver: WebDriver) {}` 

`chromeDriver {}`

`chromeDriver(pathToDriver: String) {}`

`firefoxDriver {}`

`firefoxDriver(pathToDriver: String) {}`

#### `WebDriver` and `WebElement` element builders

`element(by: By) {}`

`elementById(id: String) {}`

`elementByClass(className: String) {}`

`elementByName(name: String) {}`

`elementBySelector(selector: String) {}`

`elementByXpath(selector: String) {}`

#### `WebDriver` and `WebElement` element list builders

`elements(by: By) {}`

`elementsById(id: String) {}`

`elementsByClass(className: String) {}`

`elementsByName(name: String) {}`

`elementsBySelector(selector: String) {}`

`elementsByXpath(selector: String) {}`

#### `WebDriver` and `WebElement` element exists functions

`elementExists(by: By): Boolean`

`elementByIdExists(id: String): Boolean`

`elementByClassExists(className: String): Boolean`

`elementByNameExists(name: String): Boolean`

`elementBySelectorExists(selector: String): Boolean`

`elementByXpathExists(selector: String): Boolean`

#### `WebDriver` `WebDriverWait` builder

`wait(timeout: Long) {}`

#### `WebDriverWait` element visibility builders

`elementVisibility(by: By) {}`

`elementVisibilityById(id: String) {}`

`elementVisibilityByClass(className: String) {}`

`elementVisibilityByName(name: String) {}`

`elementVisibilityBySelector(selector: String) {}`

`elementVisibilityByXpath(xpath: String) {}`

#### `WebDriverWait` element invisibility functions

`elementInvisibility(by: By): Boolean`

`elementInvisibilityById(id: String): Boolean`

`elementInvisibilityByClass(className: String): Boolean`

`elementInvisibilityByName(name: String): Boolean`

`elementInvisibilityBySelector(selector: String): Boolean`

`elementInvisibilityByXpath(xpath: String): Boolean`

