package fr.grp404.projetjee.web;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import fr.grp404.projetjee.web.servlet.*;

/**
 * Permet le routage des différents servlet de manière simple
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();

        install(new JpaPersistModule("projet-jee"));
        filter("/*").through(PersistFilter.class);

        //Permet d'associer un servlet avec une url de manière simple
        //Exemple : Mon servlet s'appelle TestServlet et répond au chemin /

        serve("/").with(MainServlet.class);
        serve("/signup").with(RegisterServlet.class);
        serve("/signin").with(ConnectionServlet.class);
        serve("/settings").with(MemberSettingsServlet.class);
        serve("/logout").with(LogOutServlet.class);
        serve("/usersgames").with(UsersGamesListServlet.class);

        // For admins
        serve("/member").with(MemberPageServlet.class);
    }
}
