package miscs;


public class Globals {

//    public static final String DOMAIN="localhost";
        public static final String DOMAIN="AgamRimon.com";
    public static final String DOMAIN_PLUS_PROTOCOL="http://"+Globals.DOMAIN;

    public static final String DATA_DIR="C:\\MySitesData\\AgamRimon\\";
    public static final long PROCESS_ID= Tools.MiscTools.randomizeNumber(0, Long.MAX_VALUE);
    public static final String PASSWORD = "jhjhgfkhjg934usd!!2"; // don't you ever change this!!!


    public static final RunEnvironment RUN_ENVIRONMENT=Tools.MiscTools.getRunningEnvironment();

    public static final long STARTING_TIME=System.currentTimeMillis();


    public static final String VIRTUAL_FOLDER=Globals.DATA_DIR+"VirtualFolder\\";

    public static final String DESKTOP_DIR="C:\\Users\\1\\OneDrive\\שולחן העבודה\\";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36";

    public static final String JXDATA_FOLDER=Globals.DATA_DIR+"JXData\\";

}
