package chessOpenings.service.impl;

import chessOpenings.dto.ChessOpeningDto;
import chessOpenings.service.ChessOpeningsClassifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChessOpeningsClassifierImpl implements ChessOpeningsClassifier {

    private static final Set<ChessOpeningDto> OPENING_DTO_SET = Set.of(
            new ChessOpeningDto("A47", "Queen's Indian Defence"),
            new ChessOpeningDto("A60", "Benoni Defence"),
            new ChessOpeningDto("A80", "Dutch Defence"),
            new ChessOpeningDto("B10", "Caro-Kann Defence"),
            new ChessOpeningDto("B20", "Sicilian Defence"),
            new ChessOpeningDto("C00", "French Defence"),
            new ChessOpeningDto("C30", "Philidor Defence"),
            new ChessOpeningDto("C60", "Ruy Lopez"),
            new ChessOpeningDto("D20", "Queen's Gambit Accepted"),
            new ChessOpeningDto("D30", "Grunfeld Defence"),
            new ChessOpeningDto("E60", "King's INdian Defence")
            );

    private static final Map<String, ChessOpeningDto> OPENING_MAP = OPENING_DTO_SET.stream()
            .collect(Collectors.toMap(ChessOpeningDto::getEco, Function.identity()));
    @Override
    public Set<ChessOpeningDto> getOpenings() {
        return OPENING_DTO_SET;
    }

    @Override
    public Optional<ChessOpeningDto> getOpeningByEco(String eco) {
        return Optional.ofNullable(OPENING_MAP.get(eco));
    }
}
