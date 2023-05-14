package com.fesc.apipartidos.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//Nos creara el contexto de nuestors bean
public class AppContexto implements ApplicationContextAware {

    private static ApplicationContext contexto;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       
        contexto = applicationContext;
    }

    public static Object getBean(String nombre){
        return contexto.getBean(nombre);
    }
    
}
