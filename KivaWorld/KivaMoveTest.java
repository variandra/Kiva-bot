import edu.duke.Point;

public class KivaMoveTest {
    // Define the FloorMap we'll use for all the tests
    String defaultLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **       *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    public void testForwardFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space up
        verifyKivaState("testForwardFromUp", 
            kiva, new Point(2, 3), FacingDirection.UP, false, false);
    }
    public void testForwardFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromLeft", 
            kiva, new Point(1, 4), FacingDirection.LEFT, false, false);
    }public void testForwardFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromDOWN", 
            kiva, new Point(2, 5), FacingDirection.DOWN, false, false);
    }
    public void testForwardFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromRight", 
            kiva, new Point(3, 4), FacingDirection.RIGHT, false, false);
    }
    
    
    // For you: create all the other tests and call verifyKivaState() for each
    
    public void testTurnLeftFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromUp", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    public void testTurnLeftFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.LEFT;
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromLeft", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    public void testTurnLeftFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.DOWN;
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromDOWN", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    public void testTurnLeftFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.RIGHT;
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromRight", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    
    
    public void testTurnRightFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromUp", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    public void testTurnRightFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.RIGHT;
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromRight", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    public void testTurnRightFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.DOWN;
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromDown", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    public void testTurnRightFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.facingDirection = FacingDirection.LEFT;
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromLeft", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    
    
    public void testTakeOnPod() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TAKE);
        verifyKivaState("testTakeOnPod", 
            kiva, new Point(8, 1), FacingDirection.RIGHT, true, false);
    }
    
    
    public void testDropOnDropZone() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TAKE);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.DROP);
        verifyKivaState("testDropOnDropZone", 
            kiva, new Point(10, 4), FacingDirection.DOWN, false, true);
    }
    
    
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped) {

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)) {
            System.out.println(
                    String.format("%s: current location SUCCESS", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }
        else {
            System.out.println(
                    String.format("%s: current location FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.println(
                    String.format("%s: facing direction SUCCESS", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }
        else {
            System.out.println(
                    String.format("%s: facing direction FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }
        
        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(
                    String.format("%s: carrying pod SUCCESS", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }
        else {
            System.out.println(
                    String.format("%s: carrying pod FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(
                    String.format("%s: successfully dropped SUCCESS", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
        else {
            System.out.println(
                    String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
    }
    
    /**
     * Exception tests
     */
    
    public void testMoveOutOfBounds() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testMoveOutOfBounds: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);

        // This only runs if no exception was thrown
        System.out.println("testMoveOutOfBounds FAIL!");
        System.out.println("Moved outside the FloorMap!");
    }
    
    public void testMoveIntoObstacle() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("testMoveOutOfBounds: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);

        // This only runs if no exception was thrown
        System.out.println("testMoveIntoObstacle FAIL!");
        System.out.println("Moved into a obstacle!");
    }
    
    public void testForwardWithPodIntoPod() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TAKE);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testForwardWithPodIntoPod: (expect an IllegalMoveException)");
        // This only runs if no exception was thrown
        System.out.println("testMoveIntoObstacle FAIL!");
        System.out.println("Moved into a obstacle!");
    }
    
    public void kivaMotorLifetimeTester(){
        String defaultLayout = ""
                           + "-----\n"
                           + "|K D|\n"
                           + "| P |\n"
                           + "|* *|\n"
                           + "-----\n";

        FloorMap defaultMap = new FloorMap(defaultLayout);
        Kiva kiva = new Kiva(defaultMap);
        
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
        kiva.move(KivaCommand.FORWARD);
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
        kiva.move(KivaCommand.FORWARD);
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
        kiva.move(KivaCommand.TAKE);
        kiva.move(KivaCommand.FORWARD);
        System.out.printf(
            "MotorLifetime is currently %sms or %ss\n",kiva.getMotorLifetime(),kiva.getMotorLifetime()/1000);
    }
    
}

