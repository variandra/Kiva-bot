/**
 * An enum class that stores the commands the kiva can use to move and interact with the FloorMap.
 * 
 * @author Kyle Gunn
 * @version 1.0
 */
public enum KivaCommand{
    FORWARD('f'),TURN_RIGHT('r'),TURN_LEFT('l'),TAKE('t'),DROP('d');
    
    private char key;
    
    /**
     * a constructor that sets the keyboard shortcut for each command
     * @param shortcut This sets the Keyboard shortcut for each command.
     */
    private KivaCommand(char shortcut){
     this.key = shortcut;   
    }
    /**
     * getDirectionKey() allows the user to see the keyboard representation of the commands the kiva knows.
     * @param none
     * @return char
     */
    public char getDirectionKey(){
     
        return key;
    }
    
    /*public KivaCommand getCommandByKey(char Imput){
        
         * 
         KivaCommand command;
        for(int i = 0 ; i < KivaCommand.values().length ; i += 1){
        
        }
        return command;
        
       
    }*/
    public static KivaCommand getCommandByKey(char imput){
       for(KivaCommand command : values()){
           if( command.key == imput){
               return command;
           }
       }
       throw new InvalidKivaCommandException("");
    }
}
