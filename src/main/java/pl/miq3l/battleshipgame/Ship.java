package pl.miq3l.battleshipgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Coordinates {
    private int row;
    private int col;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    private List<Coordinates> coordinates = new ArrayList<>();
}
