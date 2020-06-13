import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UserListFile {

    private UserList userList = new UserList();

    public static String filePath;

    public UserList readJSON(String filePath) {
        this.filePath = filePath;

        JSONParser parser = new JSONParser();
        User user = null;

        try {
            JSONArray users = (JSONArray) parser.parse(new FileReader(filePath));
            if (users != null) {
                for (Object object : users) {
                    JSONObject userObject = (JSONObject) object;

                    user = parser(userObject);
                    userList.addUser(user);
                }
            } else {
                System.out.println("Empty json");
                return userList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User parser(JSONObject jsonObject) {
        User user = new User();

        user.setId((String) jsonObject.get("id"));
        user.setName((String) jsonObject.get("name"));
        user.setPassword((String) jsonObject.get("password"));
        user.setPhoneNumber((String) jsonObject.get("phoneNumber"));
        user.setEmailAddress((String) jsonObject.get("emailAddress"));

        user.printUserInformation();

        return user;
    }
}
