package dev.creator54;

import java.net.MalformedURLException;

public class Main extends BaseClass{
    public static void main (String[] args) throws MalformedURLException {
        setup ();
        goTo ("https://google.com");
        close();
    }
}