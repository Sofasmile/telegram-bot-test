import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommandService {
  private ObjectMapper objectMapper = new ObjectMapper();
  private String filePath = "/Users/sofiadiakonova/IdeaProjects/telegram_bot_test/src/main/resources/command.json";

  public Map<String, Command> getQueryToCommands() {
    return convertListToMap(readCommandsFromFile());
  }

  public List<Command> readCommandsFromFile() {
    try {
      String allLines = Files.readString(Paths.get(filePath));
      return Arrays.asList(objectMapper.readValue(allLines, Command[].class));
    } catch (IOException e) {
      throw new RuntimeException("Can't parse json file by path: " + filePath, e);
    }
  }

  private Map<String, Command> convertListToMap(List<Command> list) {
    Map<String, Command> commandsAndReplies = new HashMap<>();
    for (Command command : list) {
      commandsAndReplies.put(command.getQuery(), command);
    }
    return commandsAndReplies;
  }
}
