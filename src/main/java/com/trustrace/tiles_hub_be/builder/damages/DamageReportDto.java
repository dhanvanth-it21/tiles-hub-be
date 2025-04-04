package com.trustrace.tiles_hub_be.builder.damages;

import com.trustrace.tiles_hub_be.model.collections.damage.DamageLocation;
import com.trustrace.tiles_hub_be.model.collections.damage.DamageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DamageReportDto {
    private String _id;
    private String damageReportId;
    private String skuCode;
    private String reportedBy;
    private String approvedBy;
    private String purchaseId;
    private String orderId;
    private DamageLocation damageLocation;
    private int qty;
    private DamageStatus status;
    private String remark;
}
