//package com;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class DatabaseConfig {
//	
//	private String USERNAME = "kxz959";
//	private String HOST = "127.0.0.1";
//	private int PORT = 3306;	
//	private String PASSWORD = "kxz959_uob";
//	
//    @Bean
//    public DriverManagerDataSource dataSource() {		
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        
//        // jdbc:mysql://host:port/db
//        ds.setUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + USERNAME );
//        ds.setUsername(USERNAME);
//        ds.setPassword(PASSWORD);
//        return ds;
//    }
//}
