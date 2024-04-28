package xyz.alcy.assertcontrollersystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.alcy.assertcontrollersystem.pojo.Asset;

@Mapper
public interface AssetMapper {

    @Insert("INSERT INTO assets (asset_number, asset_name, asset_category, asset_status, asset_brand, asset_model, asset_quantity, asset_specification, asset_value, purchase_date, entry_date, department_used) " +
            "VALUES (#{assetNumber}, #{assetName}, #{assetCategory}, #{assetStatus}, #{assetBrand}, #{assetModel}, #{assetQuantity}, #{assetSpecification}, #{assetValue}, #{purchaseDate}, #{entryDate}, #{departmentUsed})")
    void addAsset(Asset asset);

    @Select("select * from assets where asset_number=#{assetNumber}")
    Asset getAsset(String assetNumber);

    @Update("")
    void updateAsset(String assetNumber, Asset asset);
}
