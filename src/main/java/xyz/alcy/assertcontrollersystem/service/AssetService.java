package xyz.alcy.assertcontrollersystem.service;

import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.pojo.AssetDTO;

import java.util.List;

public interface AssetService {
    void addAsset(AssetDTO assetDTO);

    List<Asset> getAllAssets();

    Asset getAsset(Integer assetNumber);

    void updateAsset(AssetDTO assetDTO);

    void deleteAsset(Integer assetNumber);
}
