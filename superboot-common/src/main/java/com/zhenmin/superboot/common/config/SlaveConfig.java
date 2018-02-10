package com.zhenmin.superboot.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhenmin.superboot.common.constant.ConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源配置
 */
@Configuration
@MapperScan(basePackages = ConfigConstants.SLAVE_DS_PACKAGE, sqlSessionFactoryRef = ConfigConstants.NAME_DS_SSF_SUPERBOOT_SLAVE)
@Slf4j
public class SlaveConfig {
	
	private final Environment env;
	
	@Autowired
	public SlaveConfig(Environment env) {
		this.env = env;
	}
	
	@Bean(name = ConfigConstants.SLAVE_DS_NAME)
	@ConfigurationProperties(prefix = ConfigConstants.PREFIX_DS_SUPERBOOT_SLAVE)
	public DataSource mwcSlaveDataSource() {
		log.info("-------------------- superBoot slave data source init ---------------------");
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(env.getProperty("ds.superBoot.slave.url"));
		ds.setUsername(env.getProperty("ds.superBoot.slave.username"));
		ds.setPassword(env.getProperty("ds.superBoot.slave.password"));
		try {
			ds.setFilters(env.getProperty("ds.filters"));
		} catch (SQLException e) {
			log.warn("Data source set filters ERROR:", e);
		}
		ds.setMaxActive(NumberUtils.toInt(env.getProperty("ds.maxActive"), 90));
		ds.setInitialSize(NumberUtils.toInt(env.getProperty("ds.initialSize"), 10));
		ds.setMaxWait(NumberUtils.toInt(env.getProperty("ds.maxWait"), 60000));
		ds.setMinIdle(NumberUtils.toInt(env.getProperty("ds.minIdle"), 1));
		ds.setTimeBetweenEvictionRunsMillis(NumberUtils.toInt(env.getProperty("ds.timeBetweenEvictionRunsMillis"), 60000));
		ds.setMinEvictableIdleTimeMillis(NumberUtils.toInt(env.getProperty("ds.minEvictableIdleTimeMillis"), 300000));
		ds.setValidationQuery(env.getProperty("ds.validationQuery"));
		ds.setTestWhileIdle(BooleanUtils.toBoolean(env.getProperty("ds.testWhileIdle")));
		ds.setTestOnBorrow(BooleanUtils.toBoolean(env.getProperty("ds.testOnBorrow")));
		ds.setTestOnReturn(BooleanUtils.toBoolean(env.getProperty("ds.testOnReturn")));
		ds.setPoolPreparedStatements(BooleanUtils.toBoolean(env.getProperty("ds.poolPreparedStatements")));
		ds.setMaxOpenPreparedStatements(NumberUtils.toInt(env.getProperty("ds.maxOpenPreparedStatements"), 20));
		return ds;
	}
	
	@Bean(name = ConfigConstants.NAME_DS_TM_SUPERBOOT_SLAVE)
	public DataSourceTransactionManager mwcSlaveTransactionManager() {
		log.info("-------------------- superBoot slave data source transaction manager init ---------------------");
		return new DataSourceTransactionManager(mwcSlaveDataSource());
	}
	
	@Bean(name = ConfigConstants.NAME_DS_SSF_SUPERBOOT_SLAVE)
	public SqlSessionFactory mwcSlaveSqlSessionFactory(@Qualifier(ConfigConstants.SLAVE_DS_NAME) DataSource mwcSlaveDataSource) throws Exception {
		log.info("-------------------- superBoot slave data source sql session factory init ---------------------");
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(mwcSlaveDataSource);
		sessionFactory.setConfigLocation(new ClassPathResource(ConfigConstants.NAME_MYBATIS_CONFIG));
		return sessionFactory.getObject();
	}
}
