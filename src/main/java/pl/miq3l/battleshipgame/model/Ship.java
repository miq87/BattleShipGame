package pl.miq3l.battleshipgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    private List<Coordinates> coordinates = new ArrayList<>();
    private int size;
    private String type;

    public void reduceShipSize() {
        this.size--;
    }
}
