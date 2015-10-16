package com.charlie.encryptshow;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.charlie.encryptshow.EncryptActivityTest \
 * com.charlie.encryptshow.tests/android.test.InstrumentationTestRunner
 */
public class EncryptActivityTest extends ActivityInstrumentationTestCase2<EncryptActivity> {

    public EncryptActivityTest() {
        super("com.charlie.encryptshow", EncryptActivity.class);
    }

}
