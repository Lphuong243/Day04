package murach.data;

import java.io.*;
import murach.business.User;

public class UserIO {

    public static void add(User user, String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            out.println(user.getEmail() + "|" + user.getFirstName() + "|" + user.getLastName());
        }
    }

    public static User getUser(String emailAddress, String filepath) {
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            return null;
        }
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String email = parts[0].trim();
                    if (email.equalsIgnoreCase(emailAddress.trim())) {
                        return new User(parts[1].trim(), parts[2].trim(), email);
                    }
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
