package main;

public class MarsRover {


    public static void main(String[] args) {
        /*
    This APP recives three inputs:
    1 - String - Start direction of the rover example: "N".
    2 - String - Start position on the map , example: "3,2".
    3 - String - List of movements, example "FFFLFFBRLL";
    */
        //Size of map x,y
        int x = 6; int y = 6;
        int[][] map = new int[y][x];
        //roadblocks on the map
        map[4][0]=1;
//        map[1][4]=1;
        //Initial position of rover
        int yInitial = 0;
        int xInitial = 0;
        //Initial ubication of rover
        String initialDirection = "N";
        //List of commands
        String commands = "FFFRFLFLRFLFLLLLRlf";


        //*****************
        Rover rover = new Rover(initialDirection, yInitial, xInitial, map);
        if (commands != null && isCorrectCommands(commands)) {
            String commandUpperCase = commands.toUpperCase();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                rover.execute(command);
                if (rover.getRoadblock())
                {
                    System.out.println("FINAL POSITION "+rover.getFinalPosition());
                    System.out.println("Roadblock -- ROVER STOPS");
                    return;
                }
            }

            System.out.println("FINAL POSITION "+rover.getFinalPosition());
        } else {
            System.out.println("Bad command");
        }


    }

    public static boolean isCorrectCommands(String cmd) {

        if (cmd != null) {
            for (int i = 0; i < cmd.length(); i++) {
                String ch = String.valueOf(cmd.charAt(i));
                if (!("FBLRfblr".contains(ch))) {
                    return false;
                }
            }
            return true;
        }
    return false;
   }
}
