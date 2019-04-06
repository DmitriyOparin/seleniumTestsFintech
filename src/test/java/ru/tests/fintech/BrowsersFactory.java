package ru.tests.fintech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;
import java.util.HashMap;


public enum BrowsersFactory {
    chrome {
        public WebDriver create() {
            String separator = File.separator;
            String commonPath = "src" + separator + "test" + separator + "resources" + separator;
            File file = new File(commonPath);
            String downloadFilepath = file.getAbsolutePath();

            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
            chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
            chromeOptionsMap.put("download.default_directory", downloadFilepath);
            options.setExperimentalOption("prefs", chromeOptionsMap);
            options.addArguments();

            return new ChromeDriver(options);
        }
    },
    firefox {
        public WebDriver create() {
            return new FirefoxDriver();
        }
    },
    opera {
        public WebDriver create() {
            return new OperaDriver();
        }
    };

    public WebDriver create() {
        return null;
    }
}