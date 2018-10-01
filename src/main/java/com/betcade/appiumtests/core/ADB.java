package com.betcade.appiumtests.core;

import com.betcade.appiumtests.core.managers.ServerManager;
import java.util.ArrayList;

public class ADB {

    private String ID;

    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String command(String command) {
        //MyLogger.log.debug("Formatting ADB Command: " + command);
        if (command.startsWith("adb"))
            command = command.replace("adb ", ServerManager.getAndroidHome() + "/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run ADB commands only!");
        //MyLogger.log.debug("Formatted ADB Command: " + command);
        String output = ServerManager.runCommand(command);
        //MyLogger.log.debug("Output of the ADB Command: " + output);
        if (output == null) return "";
        else return output.trim();
    }

    /**
     * Kill server.
     */
    public static void killServer() {
        command("adb kill-server");
    }

    /**
     * Start server.
     */
    public static void startServer() {
        command("adb start-server");
    }

    /**
     * Gets connected devices.
     *
     * @return the connected devices
     */
    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    /**
     * Gets foreground activity.
     *
     * @return the foreground activity
     */
    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    /**
     * Gets android version as string.
     *
     * @return the android version as string
     */
    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3) output += ".0";
        return output;
    }

    /**
     * Gets android version.
     *
     * @return the android version
     */
    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList getInstalledPackages(){
        ArrayList packages = new ArrayList();
        String[] output = command("adb -s "+ID+" shell pm list packages").split("\n");
        for(String packageID : output) packages.add(packageID.replace("package:","").trim());
        return packages;
    }

    /**
     * Open apps activity.
     *
     * @param packageID  the package id
     * @param activityID the activity id
     */
    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    /**
     * Clear apps data.
     *
     * @param packageID the package id
     */
    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    /**
     * Force stop app.
     *
     * @param packageID the package id
     */
    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    /**
     * Install app.
     *
     * @param apkPath the apk path
     */
    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    /**
     * Uninstall app.
     *
     * @param packageID the package id
     */
    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    /**
     * Push file.
     *
     * @param source the source
     * @param target the target
     */
    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    /**
     * Pull file.
     *
     * @param source the source
     * @param target the target
     */
    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    /**
     * Delete file.
     *
     * @param target the target
     */
    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    /**
     * Move file.
     *
     * @param source the source
     * @param target the target
     */
    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    /**
     * Take screenshot.
     *
     * @param target the target
     */
    public void takeScreenshot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    /**
     * Reboot device.
     */
    public void rebootDevice() {
        command("adb -s " + ID + "reboot");
    }

    /**
     * Gets device model.
     *
     * @return the device model
     */
    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    /**
     * Gets device serial number.
     *
     * @return the device serial number
     */
    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    /**
     * Gets device carrier.
     *
     * @return the device carrier
     */
    public String getDeviceCarrier() {
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }

    /**
     * Navigate back string.
     *
     * @return the string
     */
    public String navigateBack() {
        return command("adb shell input keyevent 4" );
    }

    /**
     * Home button string.
     *
     * @return the string
     */
    public String homeButton() {
        return command("adb shell input keyevent 3" );
    }

    public String wifiStatus(){

        String result = command("adb shell dumpsys wifi");
        String[] line1 = result.split("\n");
        return line1[0];
    }

    public String wifiTurnOn(){
        return command("adb shell svc wifi enable");
    }

    public String wifiTurnOn1(){
        return command("adb shell su -c svc wifi enable");
    }

    public String wifiTurnOff(){
        return command("adb shell svc wifi disable");
    }


}
