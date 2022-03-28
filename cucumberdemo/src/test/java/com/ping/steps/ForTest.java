package com.ping.steps;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.concurrent.TransferQueue;

@Log
public class ForTest {
    public static void main(String[] args) throws InterruptedException {
        SeleniumOperation seleniumOperation=new SeleniumOperation();
        seleniumOperation.openByUrl();
    }
}
