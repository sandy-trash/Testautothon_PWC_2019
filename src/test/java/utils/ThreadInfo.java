package utils;

import configPackage.Config;
import driver.MyChromeDriver;
import driver.MyFirefoxDriver;
import driver.MyAndroidDriver;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ThreadInfo {

    static int countmobiledrivers = 0;
    static int countapis = 0;
    ArrayList<Thread> threadlist = new ArrayList<>();
    ArrayList<WebDriver> driverlist = new ArrayList<>();
    List<String> countrylist = new ArrayList<>();
    ArrayList<Do> Do = new ArrayList<>();
    Method[] methods;
    Object classObject;
    String wikiScreenShot = null;

    public ThreadInfo(List<String> countryList) {
        this.countrylist = countryList;
        countmobiledrivers = new Config().getMobileThreadCount();
        countapis = new Config().getHTTPThreadCount();
    }

    public void startThreads() throws Exception {
        for (int i = 0; i < countrylist.size(); i++) {
            getThread(countrylist.get(i)).start();
        }
    }


    void createThread(String countryname, WebDriver driver, boolean isMobile, boolean isHTTP, boolean isWeb) throws Exception {

        Do dos = new Do(driver, countryname);
        dos.doMethods(isWeb, isHTTP, isMobile, classObject, methods);
        threadlist.add(new Thread(dos));
        driverlist.add(driver);
        //countrylist.add(countryname);
        Do.add(dos);
        dos.threadID = getThread(countryname).getId();
    }

    void reinitThread(String countryname, WebDriver driver, Do dos) {
        dos.doMethods(classObject, methods);
        threadlist.add(new Thread(dos));
    }


    public Thread getThread(String countryname) throws Exception {

        for (int i = 0; i < countrylist.size(); i++) {
            if (countrylist.get(i).equals(countryname)) {
                return threadlist.get(i);
            }
        }
        System.out.println("Thread not found for movie : " + countryname);
        throw new Exception("Thread not found for movie : " + countryname);
    }

    public Do getDo(String countryname) throws Exception {

        for (int i = 0; i < countrylist.size(); i++) {
            if (countrylist.get(i).equals(countryname)) {
                return Do.get(i);
            }
        }
        System.out.println("Do not found for country : " + countryname);
        throw new Exception("Do not found for country : " + countryname);
    }

    public ArrayList<Do> getDo() {
        return Do;
    }


    public ThreadInfo doMethods(Object classObject, Method... methods) throws Exception {

        this.methods = methods;
        this.classObject = classObject;
        List<String> countries = new ArrayList();
        countries.addAll(countrylist);

        if(Config.runmode.equals("http"))
        {
            for (int i = 0; i < countries.size(); i++) {
                createThread(countries.get(i), null, false, true, false);
            }
            return this;
        }
        else if(Config.runmode.equals("both"))
        {
            for (int i = 0; i < countries.size(); i++) {
              /*  if (countmobiledrivers > 0) {
                    createThread(countries.get(i), new MyAndroidDriver().newMobileDriver(), true, false, false);
                    countmobiledrivers--;
                }
                else*/ if (countapis > 0) {
                    createThread(countries.get(i), null, false, true, false);
                    countapis--;
                }
                else {
                    createThread(countries.get(i), new MyFirefoxDriver().newDriver(), false, false, true);
                }
            }
            return this;
        }
        else
        {
            for (int i = 0; i < countries.size(); i++) {
                if (countmobiledrivers > 0) {
                    createThread(countries.get(i), new MyAndroidDriver().newMobileDriver(), true, false, false);
                    countmobiledrivers--;
                } else {
                    createThread(countries.get(i), new MyFirefoxDriver().newDriver(), false, false, true);
                }
            }
            return this;
        }
    }

    public WebDriver getDriver(String countryname) throws Exception {
        for (int i = 0; i < countrylist.size(); i++) {
            if (countrylist.get(i).equals(countryname)) {
                return Do.get(i).getDriver();
            }
        }
        System.out.println("Driver not found for country : " + countryname);
        throw new Exception("Driver not found for country : " + countryname);
    }

    public ThreadInfo setNewMethods(Object classObject, Method... methods) throws Exception {
        threadlist = new ArrayList<>();
        this.methods = methods;
        this.classObject = classObject;
        ArrayList<String> countries = new ArrayList();
        countries.addAll(countrylist);
        for (int i = 0; i < countries.size(); i++) {
            reinitThread(countries.get(i), getDriver(countries.get(i)), getDo(countries.get(i)));
        }
        return this;
    }

    public void waitForThreadsToComplete() {
        int count = threadlist.size();
        int finished = 0;
        do {
            finished = 0;
            for (int i = 0; i < threadlist.size(); i++) {
                if (!threadlist.get(i).isAlive()) {
                    finished = finished + 1;
                }
            }
        } while (count != finished);
    }

    public void quitAllDrivers() {
        for (int i = 0; i < driverlist.size(); i++) {
            if(driverlist.get(i) != null)
            {
                driverlist.get(i).quit();
            }
        }
    }


}
