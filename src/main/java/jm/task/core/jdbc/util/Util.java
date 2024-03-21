package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/kata_db1";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    public static Session getSession() {
        Session session = null;

        try {
            Map<String, String> settings = new HashMap<>();
            settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
            settings.put("hibernate.connection.url", URL);
            settings.put("hibernate.connection.username", USER);
            settings.put("hibernate.connection.password", PASS);
            settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            settings.put("hibernate.use_sql_comments", "true");
            settings.put("hibernate.current_session_context_class", "thread");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.format_sql", "true");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(settings).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(User.class);
            Metadata metadata = metadataSources.buildMetadata();

            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return session;
    }
}
