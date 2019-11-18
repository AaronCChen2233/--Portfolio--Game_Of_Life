import java.util.Scanner;

public class App {
    private static CommandLine commandLine;
    private static boolean _isRunWindow;
    public static void main(String[] args) throws InterruptedException {
        commandLine = new CommandLine(args);
        _isRunWindow = commandLine.doesArgumentExists("-runwindow");
        if(_isRunWindow){
            GameOfLifeWindows gameOfLifeWindows = new GameOfLifeWindows();
            gameOfLifeWindows.setVisible(true);
        }
        else {
            RunGameOfLifeInTerminal();
        }
    }
    private static void RunGameOfLifeInTerminal() throws InterruptedException {
        /*input grid width*/
        Scanner input = new Scanner(System.in);
        System.out.println("Here will create a grid and random origin cell");
        System.out.print("Please input grid width : ");
        int gridx = input.nextInt();
        System.out.print("Please input grid height : ");
        int gridy = input.nextInt();
        System.out.print("Please input percent which is for create random alive cell : ");
        int percent = input.nextInt();
        System.out.print("Please input max generation times (if you want infinite please type -1): ");
        int maxGenerationTimes = input.nextInt();

        int[][] origin = GameOfLife.CreateRandomCellGrid(gridy, gridx, percent);

        /*for test date*/
//        int origin[][] = {
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 0},
//                {0, 1, 0, 0, 1, 0},
//                {0, 0, 1, 0, 0, 0},
//        };

//        input.close();
        while (true) {
            System.out.println(GameOfLife.getGenerationTimes());
            GameOfLife.OutputCellPlayGrid(origin);
            origin = GameOfLife.NextGeneration(origin);

            if (GameOfLife.getGenerationTimes() == maxGenerationTimes) {
                System.out.println("Ooops is time to stop!");
                break;
            }

            if (!GameOfLife.CheckAllCellAlive(origin)) {
                System.out.println("Ooops All cells are dead");
                break;
            }

            Thread.sleep(350);
        }
    }
}
