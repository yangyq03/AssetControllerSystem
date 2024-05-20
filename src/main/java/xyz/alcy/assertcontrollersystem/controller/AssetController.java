package xyz.alcy.assertcontrollersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.pojo.AssetDTO;
import xyz.alcy.assertcontrollersystem.pojo.Result;
import xyz.alcy.assertcontrollersystem.service.AssetService;

import java.util.List;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    //添加资产信息
    @PostMapping("/manage")
    public Result assetAdd(@RequestBody AssetDTO assetDTO) {
        assetService.addAsset(assetDTO);
        return Result.success();
    }

    //获取所有资产信息
    @GetMapping
    public Result<List<Asset>> getAllAssets() {
        return Result.success(assetService.getAllAssets());
    }

    //获取指定资产信息
    @GetMapping("/{assetNumber}")
    public Result getAsset(@PathVariable Integer assetNumber) {
        Asset asset = assetService.getAsset(assetNumber);
        if (asset == null) {
            return Result.error("无该资产信息");
        }
        return Result.success(asset);
    }

    //更新资产信息
    @PutMapping("/manage/{assetNumber}")
    private Result assetUpdate(@RequestBody AssetDTO assetDTO, @PathVariable Integer assetNumber) {
        if (assetService.getAsset(assetNumber) == null) {
            return Result.error("无该资产信息");
        }
        assetDTO.setAssetNumber(assetNumber);
        assetService.updateAsset(assetDTO);
        return Result.success();
    }

    //删除指定资产信息
    @DeleteMapping("/manage/{assetNumber}")
    private Result assetDelete(@PathVariable Integer assetNumber) {
        if (assetService.getAsset(assetNumber) == null) {
            return Result.error("无该资产信息");
        }
        assetService.deleteAsset(assetNumber);
        return Result.success();
    }
}
