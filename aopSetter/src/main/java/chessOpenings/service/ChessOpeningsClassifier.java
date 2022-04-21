package chessOpenings.service;

import chessOpenings.dto.ChessOpeningDto;

import java.util.Optional;
import java.util.Set;

public interface ChessOpeningsClassifier {

    Set<ChessOpeningDto> getOpenings();

    Optional<ChessOpeningDto> getOpeningByEco(String eco);
}
