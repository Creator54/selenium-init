package dev.creator54;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class Main extends BaseClass{
    public static void main (String[] args) throws MalformedURLException {
        setup ();
        navigate ("https://icanhazdadjoke.com/");
        String joke = stringFromElement(By.cssSelector("p[class='subtitle']"));
        System.out.println (joke);
        quit ();
    }
}
