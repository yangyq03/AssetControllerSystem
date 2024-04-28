package xyz.alcy.assertcontrollersystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Asset {
    @NonNull
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entryDate;
    private String departmentUsed;
}

