package chessOpenings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChessOpeningDto {

    private String eco; // код дебюта (Encyclopedia of Chess Openings)
    private String name;

}
