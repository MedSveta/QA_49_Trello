package data_provider;

import dto.Board;
import org.testng.annotations.DataProvider;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderBoards {
    @DataProvider
    public Board[] newBoardDataProvider() {
        Board board1 = Board.builder().boardTitle("qwe1").build();
        Board board2 = Board.builder().boardTitle("qwe2").build();
        Board board3 = Board.builder().boardTitle("qwe3").build();

        return new Board[]{board1, board2, board3};
    }

    @DataProvider
    public Iterator<Board> newBoardDataProviderFromFile() {
        List<Board> boardList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader
                    ("src/main/resources/BoardsNew.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                boardList.add(Board.builder().boardTitle(line).build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return boardList.listIterator();
    }
}
