package com.cxmax.popup;

import android.app.Dialog;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;
import org.robolectric.shadows.ShadowPopupWindow;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-6-24.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
public class MainActivityTest {

    MainActivity activity;
    Button button;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        button = (Button) activity.findViewById(R.id.popup_btn);
    }

    @Test
    public void testActivityIsNotNull() {
        Assert.assertNotNull(activity);
    }

    @Test
    public void testButtonIsNotNull() {
        Assert.assertNotNull(button);
    }

    @Test
    public void testDialogIsNotNull() {
        button.performClick();
        Dialog latestAlertDialog = ShadowDialog.getLatestDialog();
        assertNotNull(latestAlertDialog);
    }

    @Test
    public void testDialogIsShowing() {
        button.performClick();
        Dialog latestAlertDialog = ShadowDialog.getLatestDialog();
        assertTrue(latestAlertDialog.isShowing());
    }
}