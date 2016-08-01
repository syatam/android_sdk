package com.adjust.sdk;

import android.content.*;

import org.junit.*;
import org.mockito.*;

import base.*;

/**
 * Created by abdullah on 7/24/16.
 */

public class TestAdjustConfig extends UnitTestBase {

    public TestAdjustConfig(Class<UnitTestActivity> activityClass) {
        super(activityClass);
    }

    private static class TestableAdjustConfig extends TestableClass {
        AdjustConfig adjustConfig;

        public TestableAdjustConfig(AdjustConfig adjustConfig) {
            this.adjustConfig = adjustConfig;
        }

        public Context getContext() {
            return getPrivateField(adjustConfig, "context");
        }

        public String getAppToken() {
            return getPrivateField(adjustConfig, "appToken");
        }

        public String getEnvironment() {
            return getPrivateField(adjustConfig, "environment");
        }
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void returnConfig_sandbox() {
        Context context = this.context;
        String environment = "sandbox";
        String appToken = "123456789012";

        TestableAdjustConfig adjustConfig =
                new TestableAdjustConfig(
                        new AdjustConfig(context, appToken, environment));

        Assert.assertNotNull(adjustConfig);
        Assert.assertEquals(adjustConfig.getEnvironment(), "sandbox");
    }

    @Test
    public void returnConfig_production() {
        Context context = this.context;
        String environment = "production";
        String appToken = "123456789012";

        TestableAdjustConfig adjustConfig =
                new TestableAdjustConfig(
                        new AdjustConfig(context, appToken, environment));

        Assert.assertNotNull(adjustConfig);
        Assert.assertEquals(adjustConfig.getEnvironment(), "production");
    }

    @Test
    public void returnConfig_noContext() {
        Context context = null;
        String environment = "production";
        String appToken = "123456789012";

        TestableAdjustConfig adjustConfig =
                new TestableAdjustConfig(
                        new AdjustConfig(context, appToken, environment));

        Assert.assertNull(adjustConfig.getContext());
    }

    @Test
    public void returnConfig_noAppToken() {
        Context context = this.context;
        String environment = "production";
        String appToken = null;

        TestableAdjustConfig adjustConfig =
                new TestableAdjustConfig(
                        new AdjustConfig(context, appToken, environment));

        Assert.assertNull(adjustConfig.getAppToken());
    }

    @Test
    public void returnConfig_noEnvironment() {
        Context context = this.context;
        String environment = null;
        String appToken = "123456789012";

        TestableAdjustConfig adjustConfig =
                new TestableAdjustConfig(
                        new AdjustConfig(context, appToken, environment));

        Assert.assertNull(adjustConfig.getEnvironment());
    }
}
