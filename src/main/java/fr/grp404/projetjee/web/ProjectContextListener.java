package fr.grp404.projetjee.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import fr.grp404.projetjee.persistence.CoreModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class ProjectContextListener extends GuiceServletContextListener {

    @Override
    protected final Injector getInjector() {
        return Guice.createInjector(new CoreModule(), new WebModule());
    }
}
