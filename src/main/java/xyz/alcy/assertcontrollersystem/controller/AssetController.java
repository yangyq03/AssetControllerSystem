package xyz.alcy.assertcontrollersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.pojo.Result;
import xyz.alcy.assertcontrollersystem.service.AssetService;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // 添加资产信息
    @PostMapping("/add")
    public Result assetAdd(@RequestBody Asset asset) {
        assetService.addAsset(asset);
        return Result.success();
    }

    // 获取资产信息
    @GetMapping
    public Result<Asset> getAsset(@RequestParam String assetNumber) {
        Asset asset = assetService.getAsset(assetNumber);
        return Result.success(asset);
    }

    // 更新资产信息
    @PutMapping("/manage/")
    private Result assetUpdate(@RequestBody Asset asset) {
        assetService.updateAsset(asset.getAssetNumber(), asset);
        return Result.success();
    }
}
