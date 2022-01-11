import edu.duke.FileResource;
public class RemoteControlTest {
    KeyboardResource keyboardResource;

    public  RemoteControlTest() {
        keyboardResource = new KeyboardResource();
    }

    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        //KivaCommand[] convertedDirections = RemoteControl.convertDirections(directions);
        System.out.println("Directions that you typed in: " + directions); 
    }
}
