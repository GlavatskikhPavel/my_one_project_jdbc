package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qweasdzxc123";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Конфигурация источника данных
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);
                // Конфигурация hibernate
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect"); // к какой БД делает запросы
                properties.put(Environment.SHOW_SQL, true);  // показывает в консоле запрос, который генерирует hibernate
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");       //сессия привязана к thread в котором создали сессию
                sessionFactory = configuration.addProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
                System.out.println("Connection was successful");
            } catch (HibernateException e) {
                System.out.println("Breaking the connection");
            }
        }
        return sessionFactory;
    }
}
