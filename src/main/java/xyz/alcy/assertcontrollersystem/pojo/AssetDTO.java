package xyz.alcy.assertcontrollersystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssetDTO {
    private Integer assetNumber;
    private String assetName;
    private String assetCategory;
    private String assetStatus;
    private String assetBrand;
    private String assetModel;
    private int assetQuantity;
    private String assetSpecification;
    private BigDecimal assetValue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseDate;
    private String departmentUsed;
}
