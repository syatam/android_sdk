package com.adjust.sdk;

public interface IRequestHandler {
    public void init(IPackageHandler packageHandler);

    public void teardown();

    public void sendPackage(ActivityPackage pack);
}
