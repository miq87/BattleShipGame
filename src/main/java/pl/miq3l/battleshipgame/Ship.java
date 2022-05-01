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
    int col;
    int row;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    List<Coordinates> coordinatesList = new ArrayList<>();
}
