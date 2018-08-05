package com.utils.json;

import junit.framework.AssertionFailedError;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import java.io.*;

public class CompareJson {

    public static void execute(String actualResponse, String expectedResponse,
                               boolean append) {
        boolean success = true;
        PrintStream writeToLog = null;
        try {
            JSONObject actualResponseJson = new JSONObject(actualResponse);
            JSONObject expectedResponseJson = new JSONObject(expectedResponse);
            ReflectionAssert.assertReflectionEquals(expectedResponseJson, actualResponseJson, ReflectionComparatorMode.LENIENT_DATES);
        } catch (JSONException e) {
            try {
                writeToLog = new PrintStream(new FileOutputStream("json_exceptions.log", append));
                writeToLog.append("\n-----------------");
                writeToLog.append("\nDifferences START");
                writeToLog.append("\n-----------------");
                e.printStackTrace(writeToLog);
                writeToLog.append("\n-----------------");
                writeToLog.append("\nDifferences   END");
                writeToLog.append("\n-----------------");
                writeToLog.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            finally {
                success = false;
            }
        } catch (AssertionFailedError e) {
            try {
                 writeToLog = new PrintStream(
                        new FileOutputStream("assertion_exceptions.log", append));
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                writeToLog.append("\n-----------------");
                writeToLog.append("\nDifferences START");
                writeToLog.append("\n-----------------");
                String title = StringUtils.substringBetween(sw.toString(), "--- Found following differences ---","--- Difference detail");
                writeToLog.append(title);
                writeToLog.append("-----------------");
                writeToLog.append("\nDifferences   END");
                writeToLog.append("\n-----------------\n\n");
                writeToLog.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            finally {
                success = false;
            }
        }


        if (success == true)
            System.out.println("PASSED, no differences in JSON found!");
        else
            System.err.println("FAILED, there seem to be some JSON differences. Differences have been logged in your " +
                    "system" +
                    "!");
    }
}
