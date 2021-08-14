package com.wblog.bi.config;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 内置数据源
 *
 * @author luyanan
 * @since 2021/8/2
 **/

@Component
public class UreportBuildDatasource implements BuildinDatasource {

    private static final String NAME = "MyDataSource";

    @Autowired
    private DataSource dataSource;

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
