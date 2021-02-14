package com.kaohe.project.sysconfig.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class ConfigManager {
	
	
	public static void main(String[] args) {
		
		System.out.print(ConfigManager.getPropertis("user"));
	}


    /**之所以定义为静态的是因为静态修饰可直接调用，不需要实例化，直接类名.方法名即可调用
    */
    private static ConfigManager config;
    private static Properties props=new Properties();
    //private static Properties props;
    private ConfigManager(){
        props=new Properties();
    }
    
    public static ConfigManager getInstance(){
        if(config==null){
            config=new ConfigManager();
        }
        return config;
    }
    
    public static String getPropertis(String key){
        try {
        	
        	String rootDir = ConfigManager.class.getClassLoader().getResource( "" ).
        			toURI().getPath();   
//        	String rootDir = System.getProperty("user.dir");
    		System.out.println("rootDir:"+rootDir);
//    		String path1=Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//    		System.out.println("Thread path1:"+path1);
//    		String path= ConfigManager.getInstance().getClass().getClassLoader().getResource("/").getPath();
//    		System.out.println("clz path:"+path);
            FileInputStream fis=new FileInputStream(rootDir+"config/db.properties");
            props.load(fis);
          
     

            	
            return props.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
        	 e.printStackTrace();
             return null;
		}
    }
    
    
    
    
    
}