public class Command {
  private String query;
  private String slug;
  private String reply;
  private boolean isQuestion;
  private boolean isMainMenu;

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public boolean getIsQuestion() {
    return isQuestion;
  }

  public void setIsQuestion(boolean question) {
    isQuestion = question;
  }

  public boolean getIsMainMenu() {
    return isMainMenu;
  }

  public void setIsMainMenu(boolean mainMenu) {
    isMainMenu = mainMenu;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }
}
