package browser;

import java.io.File;

public class Constants {
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public final static String VIPA_DIRECTORY = WORKING_DIRECTORY + "/output/vipa_TestResult.html";
    public final static String DOXZILLA_DIRECTORY = WORKING_DIRECTORY + "/output/doxzilla_TestResult.html";
    public final static String CR_LIVE_DIRECTORY = WORKING_DIRECTORY + "/output/crlive_TestResult.html";
    public final static String PROJECT_NAME = "ENVEU";
    public final static String EXTENT_CONFIG_PATH = WORKING_DIRECTORY + "/src/main/resources/extent-config.xml";
    public final static String TEST_DATA_PATH = WORKING_DIRECTORY
            + File.separator + "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "data" + File.separator + "ObjectRepo.json";
}