package com.chenlinghong.graduation.recommender.data.model;

import com.chenlinghong.graduation.recommender.data.GraduationDataModel;
import com.chenlinghong.graduation.recommender.data.constant.AttributeNameConstant;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;

import javax.sql.DataSource;

/**
 * @Description MySQLDataModel
 * @Author chenlinghong
 * @Date 2019/4/27 21:24
 * @Version V1.0
 */
public class GraduationMysqlDataModel extends MySQLJDBCDataModel implements GraduationDataModel {

    public GraduationMysqlDataModel() throws TasteException {

    }

    public GraduationMysqlDataModel(String dataSourceName) throws TasteException {
        super(dataSourceName);
    }

    public GraduationMysqlDataModel(DataSource dataSource) {
        this(dataSource,
                AttributeNameConstant.USER_GOODS_PREFERENCE_TABLE,
                AttributeNameConstant.USER_ID_COLUMN,
                AttributeNameConstant.GOODS_ID_COLUMN,
                AttributeNameConstant.USER_GOODS_PREFERENCE_COLUMN,
                AttributeNameConstant.TIMESTAMP_COLUMN);
    }

    public GraduationMysqlDataModel(DataSource dataSource,
                                    String preferenceTable,
                                    String userIDColumn,
                                    String itemIDColumn,
                                    String preferenceColumn,
                                    String timestampColumn) {
        super(dataSource, preferenceTable, userIDColumn, itemIDColumn, preferenceColumn, timestampColumn);
    }

}
