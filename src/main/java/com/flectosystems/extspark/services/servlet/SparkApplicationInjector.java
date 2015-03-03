package com.flectosystems.extspark.services.servlet;

import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.lang.reflect.Method;

/**
 * The propose of this class is override the behaviour of the {@link spark.servlet.SparkFilter#getApplication(javax.servlet.FilterConfig)}
 * method, being able to instantiate an {@link SparkApplication} that implements the <code>Singleton Pattern</code>, in that way,
 * it's possible to apply <code>Dependency Injection</code> since a dependency could be injected to the same instance.
 * <p>
 *
 * @author Ernesto Mancebo T
 * @see spark.servlet.SparkFilter
 */
public class SparkApplicationInjector extends SparkFilter {
    public static final String IS_SINGLETON = "singleton";
    private static final String GET_INSTANCE_METHOD = "getInstance";

    /**
     * {@inheritDoc}
     * <p>
     * This overrided method checks if the {@link spark.servlet.SparkApplication} implements the <code>Singleton Pattern</code>,
     * this specified in the {@link javax.servlet.FilterConfig} by the <code>singleton</code> param; otherwise, instantiate the class
     * in the same way that does in the parent class.
     *
     * @param filterConfig
     * @return
     */
    @Override
    public SparkApplication getApplication(FilterConfig filterConfig) throws ServletException {
        try {
            String applicationClassName = filterConfig.getInitParameter(APPLICATION_CLASS_PARAM);
            String isSingleton = filterConfig.getInitParameter(IS_SINGLETON);
            Class<?> applicationClass = Class.forName(applicationClassName);

            // Checks if the parameter "singleton" was provided, if it was and is "true" then calls getInstance(),
            // otherwise, instantiate the class via the default constructor
            if (null != isSingleton && Boolean.valueOf(isSingleton)) {
                Method method = applicationClass.getMethod(GET_INSTANCE_METHOD, new Class[0]);
                return (SparkApplication) method.invoke(applicationClass, new Object[0]);
            } else {
                return (SparkApplication) applicationClass.newInstance();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
