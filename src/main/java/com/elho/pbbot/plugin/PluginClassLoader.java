package com.elho.pbbot.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhuangyf
 */
public class PluginClassLoader extends URLClassLoader {

    public static PluginClassLoader create(File jarFile) {
        return create(getSystemClassLoader(), jarFile);
    }

    public static PluginClassLoader create(ClassLoader parent, File jarFile) {
        URL[] urls;
        try {
            urls = new URL[]{jarFile.toURI().toURL()};
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return new PluginClassLoader(urls, parent);
    }

    private PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
