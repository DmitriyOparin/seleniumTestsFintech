package ru.tests.fintech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public enum BrowsersFactory {
    chrome {
        public WebDriver create() {
            return new ChromeDriver();
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