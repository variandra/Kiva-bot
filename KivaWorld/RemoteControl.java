import edu.duke.FileResource;
import java.util.Arrays;

/**
 * This is the class that controls Kiva's actions based on user imput
 *
 */
public class RemoteControl{
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public  RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * 
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        Kiva kiva = new Kiva(floorMap);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        KivaCommand[] convertedDirections = convertDirections(directions);
        String[] directionsString = stringifyDirections(convertedDirections);
        System.out.println("Directions that you typed in:"); 
        for(String string : directionsString){
            System.out.print(string + ' ');
        }
        for(KivaCommand command : convertedDirections){
            kiva.move(command);
        }
        if(kiva.isSuccessfullyDropped()){
            System.out.println(
                "Kiva successfully picked up the pod and dropped it off. Thank you");
        }else {
            System.out.println(
                "I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
        }
        
    }
    private KivaCommand[] convertDirections(String userImput){
        char[] userLetters = userImput.toCharArray();
        KivaCommand[] userCommands = new KivaCommand[userLetters.length];
        for(int i = 0 ; i < userLetters.length ; i += 1){
            KivaCommand currentCommand = KivaCommand.getCommandByKey(userLetters[i]);
            userCommands[i] = currentCommand;
        }
        return userCommands;
    }
    private String[] stringifyDirections(KivaCommand[] directions){
        String[] directionToText = new String[directions.length];
        for(int i = 0 ; i < directions.length ; i += 1){
            String command = directions[i].name();
            directionToText[i] = command;
        }
        return directionToText;
    }
}
