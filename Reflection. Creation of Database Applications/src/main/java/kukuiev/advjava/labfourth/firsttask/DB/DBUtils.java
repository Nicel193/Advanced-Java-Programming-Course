package kukuiev.advjava.labfourth.firsttask.DB;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import kukuiev.advjava.labfourth.firsttask.PsychologistWithStreams;
import kukuiev.advjava.labfourth.firsttask.Reception;
import kukuiev.advjava.labfourth.firsttask.ReceptionsWithDates;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import static kukuiev.advjava.labfourth.firsttask.DB.ScriptsForDB.*;

public class DBUtils {
    private static Connection connection;

    /**
     * Здійснює серіалізацію даних про країни в указаний JSON-файл
     *
     * @param countries країни
     * @param fileName ім'я файлу
     */
    public static void exportToJSON(Psychologists countries, String fileName) {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("psychologists", Psychologists.class);
        xStream.alias("psychologist", PsychologistForDB.class);
        xStream.alias("reseption", ReseptionForDB.class);
        String xml = xStream.toXML(countries);
        try (FileWriter fw = new FileWriter(fileName); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Здійснює серіалізацію даних з бази даних в указаний JSON-файл
     */
    public static void exportToJSON(String fileName) {
        Psychologists psychologist = getPsychologistsFromDB();
        exportToJSON(psychologist, fileName);
    }

    /**
     * Здійснює десеріалізацію даних про країни зі вказаного XML-файлу
     *
     * @param fileName ім'я файлу
     * @return об'єкт, який було створено
     */
    public static Psychologists importFromJSON(String fileName) {
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.addPermission(AnyTypePermission.ANY);
            xStream.alias("psychologists", Psychologists.class);
            xStream.alias("psychologist", PsychologistForDB.class);
            xStream.alias("reseption", ReseptionForDB.class);
            return (Psychologists) xStream.fromXML(new File(fileName));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Створення з'єднання з базою даних
     */
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=root");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Створення бази даних і таблиць з видаленням попередніх
     */
    public static boolean createDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLES);
            statement.executeUpdate(DROP_DATABASE);
            statement.executeUpdate(CREATE_DATABASE);
            statement.executeUpdate(CREATE_TABLE_PSYCHOLOGISTS);
            statement.executeUpdate(CREATE_TABLE_RESEPTIONS);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    /**
     *  Занесення всіх даних з об'єкту до бази даних
     *
     *  @param countries об'єкт з даними про країни
     */
    public static void addAll(Psychologists countries) {
        for (PsychologistForDB c : countries.getList()) {
            addPsychologist(c);
        }
    }

    /**
     * Виводить на консоль всі дані з бази, послідовно для кожної країни
     */
    public static void showAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PSYCHOLOGISTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> names = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                names.add(name);
            }
            resultSet.close();
            for (String name : names) {
                showCountry(name, Show.UNSORTED);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void  showSortedByVisitors(String psychologistName) {
        showCountry(psychologistName, Show.SORTED);
    }

    public static void showCountry(String psychologistName, Show byPopulation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, psychologistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.printf("%s\t  %s\t  %s%n", "ID", "Ім'я", "Стаж");
            resultSet.next();
            System.out.printf("%s\t  %s\t  %s%n", resultSet.getString("PsychologistID"),
                    resultSet.getString("Name"), resultSet.getString("Experience"));
            resultSet.close();
            PreparedStatement anotherStatement;
            if (byPopulation == Show.SORTED) {
                anotherStatement = connection.prepareStatement(SELECT_FROM_RESEPTIONS_ORDER_BY_VISITORS);
            }
            else {
                anotherStatement = connection.prepareStatement(SELECT_FROM_RESEPTIONS);
            }
            anotherStatement.setInt(1, getIdByName(psychologistName));
            ResultSet anotherSet = anotherStatement.executeQuery();
            System.out.printf("%s\t  %s\t  %s \t%s%n", "ID", "Дата", "Кількімть відвідувачів", "Коментарі");
            while (anotherSet.next()) {
                System.out.printf("%s\t  %s\t  %s\t\t%s%n",
                        anotherSet.getString("ReseptionID"), anotherSet.getString("Day"),
                        anotherSet.getString("Visitors"), anotherSet.getString("Comment"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PsychologistForDB getPsychologistFromDB(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("PsychologistID");
        String surname = resultSet.getString("Name");
        int experience = resultSet.getInt("Experience");
        
        PsychologistForDB psychologist = new PsychologistForDB(surname, experience);
        psychologist.setId(id);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_RESEPTIONS);
        preparedStatement.setInt(1, id);
        ResultSet setOfReseption = preparedStatement.executeQuery();
        while (setOfReseption.next()) {
            ReseptionForDB reseption = new ReseptionForDB(setOfReseption.getString("Day"),
                    setOfReseption.getString("Comment") , setOfReseption.getInt("Visitors"));
            reseption.setId(setOfReseption.getInt("ReseptionID"));
            psychologist.add_reception(reseption);
        }

        return psychologist;
    }

    public static PsychologistForDB getPsychologistByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet setOfPsychologist = preparedStatement.executeQuery();
            setOfPsychologist.next();
            return getPsychologistFromDB(setOfPsychologist);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     *  Занесення даних про країну до бази даних
     */
    public static void addPsychologist(PsychologistForDB psychologist) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PSYCHOLOGISTS);
            preparedStatement.setString(1, psychologist.get_surname());
            preparedStatement.setDouble(2, psychologist.get_experience());
            preparedStatement.execute();
            for (int i = 0; i < psychologist.get_receptions_count(); i++) {
                addReception(psychologist.get_surname(), psychologist.get_receptions()[i]);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addReception(String psychologistName, Reception reseption) {
        PsychologistForDB psychologist = getPsychologistByName(psychologistName);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_RESEPTIONS);
            preparedStatement.setString(1, reseption.getDay());
            preparedStatement.setInt(2, reseption.getNumber_visitors());
            preparedStatement.setInt(3, getIdByName(psychologist.get_surname()));
            preparedStatement.setString(4, reseption.get_comment());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getIdByName(String psychologistName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, psychologistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("PsychologistID");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Видаляє вказаний перепис з бази даних
     */
    public static void removeReception(String psychologistName, int visitors) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_YEAR);
            preparedStatement.setInt(1, getIdByName(psychologistName));
            preparedStatement.setInt(2, visitors);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Виводить на консоль дані про всі переписи, в коментарях до яких міститься певне слово
     */
    public static void findWord(String word) {
        try {
            String query = SELECT_FROM_RESEPTIONS_WHERE_WORD.replace("key_word", word);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s\t  %s\t  %s\t  %s\t\t%s%n",
                        resultSet.getString("ReseptionID"), resultSet.getString("Name"),
                        resultSet.getString("Day"), resultSet.getString("Visitors"), resultSet.getString("Comment"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Psychologists getPsychologistsFromDB() {
        try {
            Psychologists countries = new Psychologists();
            Statement statement = connection.createStatement();
            ResultSet setOfCountries = statement.executeQuery(SELECT_ALL_PSYCHOLOGISTS);
            while (setOfCountries.next()) {
                countries.getList().add(getPsychologistFromDB(setOfCountries));
            }
            return countries;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Закриває зв'язок з базою даних
     */
    public static void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
