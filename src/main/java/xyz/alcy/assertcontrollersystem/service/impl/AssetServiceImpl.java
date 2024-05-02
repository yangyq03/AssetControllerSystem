package xyz.alcy.assertcontrollersystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.alcy.assertcontrollersystem.mapper.AssetMapper;
import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.pojo.AssetDTO;
import xyz.alcy.assertcontrollersystem.service.AssetService;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public void addAsset(AssetDTO assetDTO) {
        assetMapper.addAsset(assetDTO);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetMapper.getAllAssets();
    }

    @Override
    public Asset getAsset(Integer assetNumber) {
        return assetMapper.getAsset(assetNumber);
    }

    @Override
    public void updateAsset(AssetDTO assetDTO) {
        assetMapper.updateAsset(assetDTO);
    }

    @Override
    public void deleteAsset(Integer assetNumber) {
        assetMapper.deleteAsset(assetNumber);
    }
}
