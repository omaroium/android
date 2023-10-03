package com.example.omarapp.database;

import android.provider.BaseColumns;

public class TablesString {

    public TablesString() {
    }
    //region Product Table
    public static class ProductTable implements BaseColumns {
        public static final String TABLE_PLACE = "Place";
        public static final String COLUMN_PLACE_NAME = "PlaceName";
        public static final String COLUMN_PLACE_DESCRIPTION = "Description";
        public static final String COLUMN_PLACE_IMAGE = "PlaceImage";

        public static final String COLUMN_MAXVISITS ="MaxVisits";

        public static final String COLUMN_CURRENT_VISITS="Current_visits";



        public static final String TIEOFTOUR="TIMEOFTOUR";

        public static final String COLUMN_DATE="DATE";

        public static final String COLUMN_HOUROFSTART="HOUR_OF_START";

        public static final String COLUMN_TOOLS="TOOLS";

        public static final String COLUMN_PRICE="PRICE";
        public static final String COLUMN_VISITS="VISITS";



    }
   //endregion

    //region Cart Table
    public static class CartTable implements BaseColumns {
        public static final String TABLE_CART = "Cart";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";

    }
    //endregion

    //region Sale Table
    public static class SaleTable implements BaseColumns {
        public static final String TABLE_SALE = "SALE";
        public static final String COLUMN_SALE_PROD_ID = "PID";
        public static final String COLUMN_SALE_USER_ID = "UID";
        public static final String COLUMN_SALE_PRICE = "SalePrice";
        public static final String COLUMN_BUY_PRICE = "BuyPrice";
    }
    //endregion
}
