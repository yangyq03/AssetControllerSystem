package xyz.alcy.assertcontrollersystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.alcy.assertcontrollersystem.mapper.AssetMapper;
import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.service.AssetService;

import java.time.LocalDateTime;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public void addAsset(Asset asset) {
        // 设置入库日期为当前时间
        asset.setEntryDate(LocalDateTime.now());
        assetMapper.addAsset(asset);
    }

    @Override
    public Asset getAsset(String assetNumber) {
        return assetMapper.getAsset(assetNumber);
    }

    @Override
    public void updateAsset(Integer assetNumber, Asset asset) {
        assetMapper.updateAsset(assetNumber, asset);
    }
}
