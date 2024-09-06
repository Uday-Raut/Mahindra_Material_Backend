//package com.ats.mahindrabattery.security;
//
//import java.util.List;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableCaching
//public class CacheConfig {
//    
//	 @Bean
//	    public ConcurrentMapCacheManager cacheManager() {
//	        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
//	        
//	        cacheManager.setCacheNames(List.of("allMasterUserDetailsCache", "masterUserCache",
//	        		"allMasterProductVariantDetailsCache","masterProductVariantDetailsCache",
//	        		"allMasterReasonDetailsCache","masterReasonDetailsCache",
//	        		"allCurrentPalletStockDetailsCache","CurrentPalletStockDetailsCache","loadDatetimeCache",
//	        		"infeedMissionRuntimeCache","allInfeedMissionRuntimeDetailsCache"));
//	        return cacheManager;
//	    }
//}
