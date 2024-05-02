package xyz.alcy.assertcontrollersystem.mapper;

import org.apache.ibatis.annotations.*;
import xyz.alcy.assertcontrollersystem.pojo.Asset;
import xyz.alcy.assertcontrollersystem.pojo.AssetDTO;

import java.util.List;

@Mapper
public interface AssetMapper {

    @Insert("INSERT INTO assets (asset_name, asset_category, asset_status, asset_brand, asset_model, asset_quantity, asset_specification, asset_value, purchase_date, entry_date, department_used) " +
            "VALUES (#{assetName}, #{assetCategory}, #{assetStatus}, #{assetBrand}, #{assetModel}, #{assetQuantity}, #{assetSpecification}, #{assetValue}, #{purchaseDate}, now(), #{departmentUsed})")
    void addAsset(AssetDTO assetDTO);

    @Select("select * from assets")
    List<Asset> getAllAssets();

    @Select("select * from assets where asset_number=#{assetNumber}")
    Asset getAsset(Integer assetNumber);

    @Update("update assets set asset_name = #{assetName}, asset_category = #{assetCategory}, " +
            "asset_status = #{assetStatus}, asset_brand = #{assetBrand}, " +
            "asset_model = #{assetModel}, asset_quantity = #{assetQuantity}, " +
            "asset_specification = #{assetSpecification}, asset_value = #{assetValue}, " +
            "purchase_date = #{purchaseDate}, department_used = #{departmentUsed} " +
            "where asset_number = #{assetNumber}")
    void updateAsset(AssetDTO assetDTO);

    @Delete("delete from assets where asset_number=#{assetNumber}")
    void deleteAsset(Integer assetNumber);
}
