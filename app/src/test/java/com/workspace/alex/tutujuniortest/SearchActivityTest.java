package com.workspace.alex.tutujuniortest;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 13.09.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SearchActivityTest {
    private SearchActivity sActivity;

    @Before
    public void setUp() throws IOException, JSONException {
        sActivity = Robolectric.setupActivity(SearchActivity.class);
    }


    @Test
    public void initDatas() {
        assertTrue(sActivity.getTitle().toString().equals("JuniorTest"));
    }
}
