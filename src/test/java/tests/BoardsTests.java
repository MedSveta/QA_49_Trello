package tests;

import data_provider.DataProviderBoards;
import dto.Board;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyBoardPage;
import utils.TestNGListener;

import java.util.Random;
@Listeners(TestNGListener.class)

public class BoardsTests extends AppManager {

    @BeforeMethod(alwaysRun = true)
    public void login() {
        User user = User.builder()
                .email("sveta1978medved@gmail.com")
                .password("Medqwerty12345!")
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);
    }

    @Test(groups = "smoke")
    public void createNewBoardPositiveTest() {
        int i = new Random().nextInt(1000);
        Board board = Board.builder()
                .boardTitle("qwerty"+i).build();
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();
        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }

    @Test(groups = "smoke")
    public void createNewBoardNegativeTest_EmptyBoardTitle() {
        Board board = Board.builder()
                .boardTitle("").build();
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        Assert.assertTrue(boardsPage.buttonCreateIsNotClickable());
    }

    @Test(dataProvider = "newBoardDataProvider", dataProviderClass = DataProviderBoards.class)
    public void createNewBoardPositiveWithDataProvider(Board board){
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();
        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }

    @Test(dataProvider = "newBoardDataProviderFromFile", dataProviderClass = DataProviderBoards.class)
    public void createNewBoardPositiveWithDataProviderFromFile(Board board){
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();
        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }
}
