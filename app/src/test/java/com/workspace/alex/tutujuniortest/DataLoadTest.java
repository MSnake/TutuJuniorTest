package com.workspace.alex.tutujuniortest;


import com.workspace.alex.tutujuniortest.data.ArrivelData;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Try to test...=(
 * Created by Alex on 08.09.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataLoadTest {


    private MainActivity mActivity;

    @Before
    public void setUp() throws IOException, JSONException {
        mActivity = Robolectric.setupActivity(MainActivity.class);
    }


    @Test
    public void initDatas() {
        assertTrue(mActivity.getTitle().toString().equals("JuniorTest"));
    }

}
