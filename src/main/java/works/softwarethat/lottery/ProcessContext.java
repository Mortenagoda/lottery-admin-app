package works.softwarethat.lottery;

/**
 * Context object for the Java process.
 *
 * @author mortena@gmail.com
 */

public class ProcessContext {
    private static final ProcessContext INSTANCE = new ProcessContext();
    private String mongoIP;
    private int mongoPort;
    private String systemAdminEmail = "mortena@gmail.com";
    private String systemAdminPassword = "password";

    public static ProcessContext getInstance() {
        return INSTANCE;
    }

    public void setMongoIP(String mongoIP) {
        this.mongoIP = mongoIP;
    }

    public String getMongoIP() {
        return mongoIP;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public String getSystemAdminEmail() {
        return systemAdminEmail;
    }

    public void setSystemAdminEmail(String systemAdminEmail) {
        this.systemAdminEmail = systemAdminEmail;
    }

    public String getSystemAdminPassword() {
        return systemAdminPassword;
    }

    public void setSystemAdminPassword(String systemAdminPassword) {
        this.systemAdminPassword = systemAdminPassword;
    }
}
