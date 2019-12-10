
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Player {
    private Coordinates position;
    private String name;
    private boolean isBot;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.position);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.isBot != other.isBot) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.position, other.position);
    }

    public Player(Coordinates position, String name, boolean isBot) {
        this.position = position;
        this.name = name;
        this.isBot = isBot;
    }

    @Override
    public String toString() {
        return "Player{" + "position=" + position + ", name=" + name + ", isBot=" + isBot + '}';
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
    }

    public Coordinates getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public boolean isIsBot() {
        return isBot;
    }

    public Player(Coordinates position, String name) {
        this.position = position;
        this.name = name;
    }
}
