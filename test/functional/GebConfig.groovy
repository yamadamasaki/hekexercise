/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/

//import org.openqa.selenium.firefox.FirefoxDriver
//import org.openqa.selenium.firefox.FirefoxProfile
//
//driver = {
//    //set the firefox locale to 'en-us' since the tests expect english
//    //see http://stackoverflow.com/questions/9822717 for more details
//    FirefoxProfile profile = new FirefoxProfile()
//    profile.setPreference("intl.accept_languages", "en-us")
//    def driverInstance = new FirefoxDriver(profile)
//    driverInstance.manage().window().maximize()
//    driverInstance
//}

//import org.openqa.selenium.htmlunit.HtmlUnitDriver
//
//driver = {
//    new HtmlUnitDriver(true)
//}
//
//waiting {
//    timeout = 60
//    retryInterval = 0.5
//}

import org.openqa.selenium.chrome.ChromeDriver

driver = {
    System.setProperty('webdriver.chrome.driver', '/usr/local/bin/chromedriver')
    new ChromeDriver()
}

baseNavigatorWaiting = true
atCheckWaiting = true
