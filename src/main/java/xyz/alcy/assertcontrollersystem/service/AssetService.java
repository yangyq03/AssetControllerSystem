package xyz.alcy.assertcontrollersystem.service;

import xyz.alcy.assertcontrollersystem.pojo.Asset;

public interface AssetService {
    void addAsset(Asset asset);

    Asset getAsset(String assetNumber);

    void updateAsset(String assetNumber, Asset asset);
}
