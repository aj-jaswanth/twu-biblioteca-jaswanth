package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;

import static com.twu.biblioteca.Messages.*;

public class Authenticator {

    private View view;
    private User currentUser;
    private Map<String, String> idPasswordRegister;
    private Map<String, User> userRegister;
    private String currentUserId;

    public Authenticator(View view) {
        this.view = view;
        this.idPasswordRegister = new HashMap<String, String>();
        this.idPasswordRegister.put("123-123", "pwd");
        this.idPasswordRegister.put("123-321", "dwp");
        this.userRegister = new HashMap<String, User>();
        userRegister.put("123-123", new User("J", "abcd@gmail.com", 1201));
        userRegister.put("123-321", new User("librarian", "lib@gmail.com", 1000));
    }

    public int authenticate() {
        view.display(LOGIN_ID_PROMPT);
        String libraryId = view.readLine();
        view.display(LOGIN_PASSWORD_PROMPT);
        String password = view.readLine();
        if (libraryId != null) {
            String pwd = idPasswordRegister.get(libraryId);
            if (pwd != null && pwd.equals(password)) {
                currentUserId = libraryId;
                currentUser = userRegister.get(libraryId);
                if (libraryId.equals("123-321"))
                    return 2;
                return 1;
            }
        }
        view.display(LOGIN_FAILED);
        return 0;
    }

    public User currentUser() {
        return currentUser;
    }

    public String currentUserId() {
        return currentUserId;
    }
}
