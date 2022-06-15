package com.hotel.config;

/**
 * @author 86183
 */
//@Configuration
//@MapperScan(basePackages = {"com.hotel.mapper"})
public class MybatisPlusConfig {

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
//
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        druidDataSource.setUrl("jdbc:mysql://localhost:3306/hotel?useSSL=true&&characterEncoding=UTF-8&&allowMultiQueries=true");
//        druidDataSource.setUsername("root");
//        druidDataSource.setPassword("HH985682");
//        return druidDataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//        factoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
