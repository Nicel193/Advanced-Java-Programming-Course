package kukuiev.advjava.labfourth.firsttask.DB;

public class ScriptsForDB {
    /**
     * Перелік для визначення порядку виведення даних про переписи
     */
    public enum Show { SORTED, UNSORTED };
    // Константи, які містять необхідні SQL-запити:
    public static final String DROP_TABLES = "DROP TABLES IF EXISTS reseptions, psychologists";
    public static final String DROP_DATABASE = "DROP DATABASE IF EXISTS psychologistsDB";
    public static final String CREATE_DATABASE = "CREATE DATABASE psychologistsDB";
    public static final String CREATE_TABLE_PSYCHOLOGISTS = """
            CREATE TABLE psychologistsDB.psychologists (
              PsychologistID INT NOT NULL AUTO_INCREMENT,
              Name VARCHAR(128) NULL,
              Experience INT NULL,
              PRIMARY KEY (PsychologistID));
            """;
    public static final String CREATE_TABLE_RESEPTIONS = """
            CREATE TABLE psychologistsDB.reseptions (
              ReseptionID INT NOT NULL AUTO_INCREMENT,
              Day VARCHAR(30) NULL,
              Visitors INT NULL,
              PsychologistID INT NULL,
              Comment VARCHAR(256) NULL,
              PRIMARY KEY (ReseptionID),
              INDEX PsychologistID_idx (PsychologistID ASC) VISIBLE,
              CONSTRAINT PsychologistID
                FOREIGN KEY (PsychologistID)
                REFERENCES psychologistsDB.psychologists (PsychologistID)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION);                        
          
            """;
    public static final String INSERT_INTO_PSYCHOLOGISTS = """
        INSERT INTO psychologistsDB.psychologists (Name, Experience) VALUES (?, ?);
        """;
    public static final String INSERT_INTO_RESEPTIONS = """
        INSERT INTO psychologistsDB.reseptions (Day, Visitors, PsychologistID, Comment) VALUES (?, ?, ?, ?);
        """;
    public static final String SELECT_BY_NAME = "SELECT * FROM psychologistsDB.psychologists WHERE Name = ?";
    public static final String SELECT_ALL_PSYCHOLOGISTS = "SELECT * FROM psychologistsDB.psychologists";
    public static final String SELECT_FROM_RESEPTIONS = "SELECT * FROM psychologistsDB.reseptions WHERE PsychologistID = ?";
    public static final String SELECT_FROM_RESEPTIONS_ORDER_BY_VISITORS =
            "SELECT * FROM psychologistsDB.reseptions WHERE PsychologistID = ? ORDER BY Visitors";
    public static final String SELECT_FROM_RESEPTIONS_WHERE_WORD = """
             SELECT c.ReseptionID, c.Day, c.Visitors, c.Comment, l.Name FROM psychologistsDB.reseptions c 
             INNER JOIN psychologistsDB.psychologists l ON c.PsychologistID = l.PsychologistID WHERE c.Comment LIKE '%key_word%';
        """;
    public static final String DELETE_BY_YEAR = "DELETE FROM psychologistsDB.reseptions WHERE PsychologistID=? AND Visitors=?";
}
