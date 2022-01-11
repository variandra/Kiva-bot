import edu.duke.Point;
/**
 * A Kiva is a simulated robo to move around and interact with a FloorMap to acomplish tasks such as moving Pods from one area to another using KivaCommand's.
 * 
 * @author Kyle Gunn
 * @version 1.0
 */
public class Kiva {
    
    /*
     * should these be public? why would anything ask for the non this. ver?
     */
    public Point initialLocation;
    public FacingDirection facingDirection = FacingDirection.UP;
    public boolean initialCarringPod = false;
    public boolean carryingPod;
    public boolean successfullyDroppedPod;
    public Point currentLocation;
    public FloorMap map;
    public Point nextLocation;
    public long motorLifetime = 0;
    /**
     * Kiva's have two ways to create them. This first way only requires a FloorMap that already has a Kiva's starting position defined.
     * @param floorMap This is a FloorMap that should include a initial Kiva location, Pod locations and drop zone locations along with walls and obstacles.
     * 
     */
    public Kiva(FloorMap floorMap){
        this.facingDirection = facingDirection;
        this.map = floorMap;
        this.initialLocation = floorMap.getInitialKivaLocation();
        this.currentLocation = initialLocation;
        this.carryingPod = initialCarringPod;
        this.successfullyDroppedPod = false;
        this.motorLifetime = motorLifetime;
    }
    /**
     * Kiva's have two ways to create them. This Second way  requires a FloorMap AND a edu.duke.Point for the Kiva's starting position.
     * @param floorMap This is a FloorMap that should include a Pod locations and drop zone locations along with walls and obstacles.
     * @param initialLocation is a Point that gives the Kiva its starting position.
     * 
     */
    public Kiva(FloorMap floorMap, Point initialLocation){
        this.facingDirection = facingDirection;
        this.map = floorMap;
        this.currentLocation = initialLocation;
        this.carryingPod = initialCarringPod;
        this.successfullyDroppedPod = false;
        this.motorLifetime = motorLifetime;
    }
    
    /**
     * A method to return the kiva's current location as a Point.
     * @return Returns a Point with an X and Y coordinate.
     */
    public Point getCurrentLocation(){
        return this.currentLocation;
    }
    
    /**
     * A method to return the kiva's current facing direction as a FacingDirection enum constant.
     * @return Returns a enum constant in all capital letter as standard in enum constants.
     */
    public FacingDirection getDirectionFacing(){
        return this.facingDirection;
    }
    
    /**
     * A method to determine if the kiva is currently carring a pod
     * @return Returns a boolean on if the kiva is carrying a pod.
     */
    public boolean isCarryingPod(){
        return this.carryingPod;     
    }
    
    /**
     * A method that discribes if the Kiva has made a successfull pod drop.
     * @return Returns a boolean on if the kiva successfully dropped a pod in a drop zone.
     */
    public boolean isSuccessfullyDropped(){
        return this.successfullyDroppedPod;
    }
    
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    
    /**
     * A method that returns how long the motor has ran.
     * @return Returns a long to state in milliseconds the time the motor has been on.
     */
    public long getMotorLifetime(){
        return this.motorLifetime;
    }
    
    /**
     * This is the core movement method for the kiva. once a command has been sent here this function will 
     * @param command takes a KivaCommand enum and does that command. If the command is invalid it throws an error.
     */
    public void move(KivaCommand command){
        if(command == KivaCommand.FORWARD){
            nextLocation = forwardChecker();
            boolean validMove = moveValidator(nextLocation);
            if(validMove){
                this.forward();
                this.motorUpdate();
            }
        }
        else if(command == KivaCommand.TURN_RIGHT){
            this.right();
            this.motorUpdate();
        }
        else if(command == KivaCommand.TURN_LEFT){
            this.left();
            this.motorUpdate();
        }
        else if(command == KivaCommand.TAKE){
            boolean validTake = takeValidator(this.currentLocation);
            if(validTake){
                this.take();
            }
        }
        else if(command == KivaCommand.DROP){
            boolean validDrop = dropValidator(this.currentLocation);
            if(validDrop){
                this.drop();
            }
        }
        else {
            throw new InvalidKivaCommandException(String.format("%s is not a valid KivaCommand",command));
        }
    }
    
    private boolean moveValidator(Point location){
        FloorMapObject object = this.map.getObjectAtLocation(location);
        if (object != FloorMapObject.EMPTY){
            if(object == FloorMapObject.DROP_ZONE){
                return true;            
            }else if (object == FloorMapObject.POD && this.carryingPod == false){
                return true;
            }else if(object == FloorMapObject.POD && this.carryingPod == true){
                throw new IllegalMoveException(String.format(
                    "Can not move to %s becuase a %s is located there. This kiva is carrying a pod",location,object ));
            }
            
            throw new IllegalMoveException(String.format(
                "%s is not empty. %s is in the way",location,object));
        }
        if(location.getX()  < map.getMinColNum() || 
            location.getY() < map.getMinRowNum() || 
            location.getX() > map.getMaxColNum() ||
            location.getY() > map.getMaxRowNum()
            ){
            throw new IllegalMoveException(String.format(
                "Can not MOVE to %s because it is out of bounds",location));
            }
        return true;
    }
    
    private boolean takeValidator(Point currentLocation){
        if(this.map.getObjectAtLocation(currentLocation) != FloorMapObject.POD){
            throw new NoPodException(String.format(
                "Can't take nonexexistent pod from %s! ",currentLocation));
        }else 
        return true;
    }
    
    private boolean dropValidator(Point location){
        if(this.map.getObjectAtLocation(location) != FloorMapObject.DROP_ZONE){
            throw new IllegalDropZoneException(String.format(
                "Can't just drop pods willy-nilly at %s",location));
        }
        return true;
    }
    
    private Point forwardChecker(){
        nextLocation = new Point(
            this.currentLocation.getX() + this.facingDirection.getDelta().getX(),
            this.currentLocation.getY() + this.facingDirection.getDelta().getY());
        return nextLocation;
    }
    
    private void motorUpdate(){
        this.motorLifetime = this.motorLifetime + 1000;
    }
    
    
    
    private void forward(){
        this.currentLocation = nextLocation;
    }
    
    private void right(){
        /*
         * do more research on java.util.array.indexOf(). 
         * javascript version of arrays seem much easier.
         * for (FacingDirection f : FacingDirection.values()){
            if(this.facingDirection == f){
                this.facingDirection = f[i -1];
                
            }
        }
         */
        if(this.facingDirection == FacingDirection.UP){
            this.facingDirection = FacingDirection.RIGHT;
        }
        else if(this.facingDirection == FacingDirection.RIGHT){
            this.facingDirection = FacingDirection.DOWN;
        }
        else if(this.facingDirection == FacingDirection.DOWN){
            this.facingDirection = FacingDirection.LEFT;
        }
        else if(this.facingDirection == FacingDirection.LEFT){
            this.facingDirection = FacingDirection.UP;
        }
        
    
    }
    
    private void left(){
        /*
         * FacingDirection[] f = FacingDirection.values();
         * this.facingDirection = f.asList().indexOf(this.facingDirection);
        */
        if(this.facingDirection == FacingDirection.UP){
        this.facingDirection = FacingDirection.LEFT;
        }else if(this.facingDirection == FacingDirection.LEFT){
        this.facingDirection = FacingDirection.DOWN;
        }else if(this.facingDirection == FacingDirection.DOWN){
        this.facingDirection = FacingDirection.RIGHT;
        }else if(this.facingDirection == FacingDirection.RIGHT){
        this.facingDirection = FacingDirection.UP;
        }
    }
    private void take(){
        if(!this.carryingPod){
            if(sameLocation(this.currentLocation,this.map.getPodLocation())){
                this.carryingPod = true;
            }
        }
    }
    
    private void drop(){
        if(this.carryingPod){
            if(sameLocation(this.currentLocation,this.map.getDropZoneLocation())){
                this.carryingPod = false;
                this.successfullyDroppedPod = true;
            }
        }
    }
}
