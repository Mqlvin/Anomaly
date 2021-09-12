package profile;

public interface CheckProfileInterface {
    public void reloadSettings();

    public Integer interval();
    public Boolean enabled();
    public String getUsername();
    public String getUUID();

    public String getEmail();
    public String getDiscordID();
    public String getKey();
    public String getTimezone();

    public Boolean shouldSendEmail();
    public Boolean shouldSendDiscordMessage();

    public Boolean doLanguageChecks();
    public Double getLanguageSensitivity();
}
