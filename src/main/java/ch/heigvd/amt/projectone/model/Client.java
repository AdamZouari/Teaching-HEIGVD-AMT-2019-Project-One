package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class Client {

    private int id;
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Constructeur avec id
     * @param id
     * @param name
     * @param username
     * @param password
     * @param isAdmin
     */
    public Client(int id, String name, String username, String password, boolean isAdmin){
        this.id=id;
        this.name=name;
        this.username=username;
        this.password=password;
        this.isAdmin=isAdmin;

    }

    /**
     * Constructeur sans id
     * @param name
     * @param username
     * @param password
     */
    public Client(String name, String username, String password) {
        Client.builder()
                .id(-1)
                .name(name)
                .username(username)
                .password(password)
                .isAdmin(false);
    }
}


