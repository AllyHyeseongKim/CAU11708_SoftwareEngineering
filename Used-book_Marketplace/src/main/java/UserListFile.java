import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UserListFile {

    private UserList userList = new UserList();

    public static String filePath;

    public UserListFile(String filePath) {
        this.filePath = filePath;
    }

    public UserList readJSON() {
        JSONParser parser = new JSONParser();

        try {
            JSONArray users = (JSONArray) parser.parse(new FileReader(filePath));
            if (users != null) {
                for (Object object : users) {
                    JSONObject userObject = (JSONObject) object;

                    User user = parser(userObject);
                    this.userList.addUser(user);
                }
                return this.userList;
            } else {
                System.out.println("Empty json");
                return this.userList;
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
        user.setStatus((String) jsonObject.get("status"));

        user.printUserInformation();

        return user;
    }

    public void writeJSON(UserList userList) {
        this.userList = userList;

        JSONArray users = new JSONArray();

        for (int i = 0; i < this.userList.getNumUsers(); i++) {
            User user = this.userList.getUser(i);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("phoneNumber", user.getPhoneNumber());
            jsonObject.put("emailAddress", user.getEmailAddress());
            jsonObject.put("status", user.getStatus());

            users.add(jsonObject);
        }

        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(users.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
