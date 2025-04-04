package com.trustrace.tiles_hub_be.builder.tile;

import com.trustrace.tiles_hub_be.model.collections.tile.TileSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TileTableDto {
    private String _id;
    private String skuCode;
    private TileSize tileSize;
    private String brandName;
    private String modelName;
    private int qty;
    private int piecesPerBox;
    private boolean underLowStock;
}
