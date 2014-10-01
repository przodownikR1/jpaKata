package pl.java.scalatech.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author przodownik
 *         Module name : JpaKata
 *         Creating time : 30 maj 2014
 */
@EnableJpaAuditing 
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository")
@EntityScan(basePackages = "pl.java.scalatech.entity")
@PropertySource("classpath:spring-data.properties")
@Profile("dev")
public class JpaConfig {

    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private Boolean hbm2ddlAuto;
    @Value("${boneCp.partition.count}")
    private int partitionCount;
    @Value("${boneCp.partition.minConnectionsPerPartition}")
    private int minConnectionsPerPartition;
    @Value("${boneCp.partition.maxConnectionsPerPartition}")
    private int maxConnectionsPerPartition;
    @Value("${hibernate.show.sql}")
    private Boolean showSql;

    @Bean(destroyMethod = "close")
    public DataSource dataSourceOrginal() {
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(driver);
        boneCPDataSource.setJdbcUrl(url);
        boneCPDataSource.setUsername(username);
        boneCPDataSource.setPassword(password);
        boneCPDataSource.setPartitionCount(partitionCount);
        boneCPDataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
        boneCPDataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        return boneCPDataSource;
    }

    @Bean
    public DataSource dataSource(DataSource orginalDs) {
        Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(orginalDs);
        dataSource.setLogFormatter(logFormater());
        return dataSource;
    }
    
    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        // TODO
       // props.put("hibernate.cache.use_query_cache", "true");
       // props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
       // props.put("hibernate.cache.provider_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
      //  props.put("hibernate.cache.use_second_level_cache", "true");

        return props;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource(dataSourceOrginal()));
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaPropertyMap(jpaProperties());
        lef.setPackagesToScan("pl.java.scalatech.entity"); //eliminate persistence.xml
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(showSql);
        hibernateJpaVendorAdapter.setGenerateDdl(hbm2ddlAuto);
        hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public Log4JdbcCustomFormatter logFormater() {
        Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
        formatter.setLoggingType(LoggingType.SINGLE_LINE);
        formatter.setSqlPrefix("SQL:\r");
        return formatter;
    }
}
