package hw1;

/**
 * 
 * @author anoopboyal
 *
 */

public class CameraBattery {
	
	// CONSTANTS \\
	
	/**
	 * Number of external charger settings
	 */
	public static final int NUM_CHARGER_SETTINGS = 4; 

	/*
	 * constant used in calculating the charge rate of the external charger
	 */
	public static final double CHARGE_RATE = 2.0; 

	/*
	 * Default power consumption of the camera at the start of simulation
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;

	/*
	 * the user has pressed the button for charger settings
	 */
	private int buttonPress;

	/*
	 * shows how much the battery is charged
	 */
	private double batteryCharge;

	/*
	 * shows how much the camera is charged
	 */
	private double cameraCharge = 0;
	
	/*
	 * how much battery the battery can hold or battery capacity
	 */	
	private double batteryCapacity;

	
	/*
	 * the total drain of battery 
	 */	
	private double TOTAL_BATTERY_DRAIN;

	/*
	 * the amount that has been charged 
	 */	
	private double amountCharged;

	/*
	 * the amount of battery that has been drained
	 */	
	private double batteryDrain;

	/*
	 * the charge rate when camera is in the battery
	 * Then also sets to 0 when camera is removed
	 */	
	private double cameraChargeRate;

	/*
	 * the setting of the charger
	 */	
	private int chargerSetting;

	/*
	 * the power the camera takes
	 */
	private double CameraPowerConsumption;

	

	
	
	// CONSTRUCTER \\
	/**
	 * 
	 *  constructs a new battery simulation,The battery should start disconnected from both the 
	 *	camera and the external charger. The starting battery charge and maximum charge capacity 
	 *	are given. If the starting charge exceeds the batteries capacity, the batteries charge 
	 *	is set to its capacity. The external charger starts at setting 0. 
	 * @param batteryStartingCharge is the charge of the battery to begin with
	 * @param startingBatteryCapacity the max capacity of the battery
	 * 
	 */
	 public CameraBattery(double batteryStartingCharge, double startingBatteryCapacity) {
		 
		 batteryCharge = batteryStartingCharge;
		 
		 batteryCapacity = startingBatteryCapacity;
		 
		 batteryCharge = Math.min(batteryCharge, batteryCapacity);
		 
		 CameraPowerConsumption = 0.0;
	 }

		
	
	// METHODS \\
	 /**
	  * shows the user has pressed the setting button one time on the external charger
	  */
	 public void buttonPress() {
		 buttonPress += 1;
		 chargerSetting = buttonPress % NUM_CHARGER_SETTINGS;
	 }

		
	/**
	 * calculates how much has been charged
	 * @param minutes
	 * @return the amount that has been charged
	 */
	 public double cameraCharge(double minutes) {
		 
		 amountCharged = Math.min(minutes * cameraChargeRate, batteryCapacity - cameraCharge);	
		 
		 batteryCharge = Math.min(batteryCharge + amountCharged, batteryCapacity);
		 
		 cameraCharge = Math.min(cameraCharge + amountCharged, batteryCapacity);	
		 
		 return amountCharged;
	 }

		
	 /**
	  * calculates the amount that has been drained.
	  * Then adds the amount that has been drained to total drain
	  * @param minutes 
	  * @return the batteryDrain for just this time
	  */
	 public double drain(double minutes) {		 
		 
		 batteryDrain = Math.min(minutes * CameraPowerConsumption, batteryCharge); 
		 
		 batteryCharge = Math.max(0,  batteryCharge - batteryDrain);
		 
		 cameraCharge = Math.max(0,  cameraCharge - batteryDrain);
		 
		 TOTAL_BATTERY_DRAIN += batteryDrain;		 
		 
		 return batteryDrain;

	 }

		
	 /**
	  * charges battery from external
	  * calculates how much the battery gets charged 
	  * @param minutes
	  * @return the charge of the battery

	  */
	 public double externalCharge(double minutes) {
		 
		 amountCharged = Math.min(minutes * chargerSetting * CHARGE_RATE, batteryCapacity - batteryCharge);
		 
		 batteryCharge += amountCharged;		 
		 
		 return amountCharged;
	 }
		
	 /**
	  * resets the total battery drain to 0
	  */
	 public void resetBatteryMonitor() {
		 TOTAL_BATTERY_DRAIN = 0;
	 }

		
	 /**
	  * gets the capacity of the battery
	  * @return the battery's capacity
	  */
	 public double getBatteryCapacity() {
		 return batteryCapacity;		 
	 }

		
	 /**
	  * gets the charge pf the battery
	  * @return the charge of the battery
	  */
	 public double getBatteryCharge() {
		 return batteryCharge;
	 }

		
	 /**
	  * gets the charge of camera
	  * @return camera's charge
	  */
	 public double getCameraCharge() {
		 return cameraCharge; 
	 }

		
	 /**
	  * gets how much power the camera uses
	  * @return the cameras power consumption
	  */
	 public double getCameraPowerConsumption() {
		 return DEFAULT_CAMERA_POWER_CONSUMPTION;
		 }

		
	 /**
	  * gets the charger setting
	  * @return the charger setting
	  */
	 public int getChargerSetting() {
		 return chargerSetting;
	 }

		
	 /**
	  * Gets the total drain of the battery 
	  * @return the total drained
	  */
	 public double getTotalDrain() {
		 return TOTAL_BATTERY_DRAIN;	 
	 }

		
	 /**
	  * calls remove battery function
	  * puts battery in external charger
	  */
	 public void moveBatteryExternal() {
		 CameraPowerConsumption = 0.0;		 
	 }

		
	 /**
	  *  moves the battery to the camera
	  *  then shows the camera charge equal the battery
	  */
	 public void moveBatteryCamera() {
		 cameraCharge = batteryCharge;
		 
		 cameraChargeRate = CHARGE_RATE;
		 
		 CameraPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
		 
	 }

		
	 /**
	  * removes battery from both external and camera
	  */
	 public void removeBattery() {
		 cameraChargeRate = 0.0;
		 
		 cameraCharge = 0.0;
		 
		 batteryDrain = 0;
		 
		 TOTAL_BATTERY_DRAIN += 0;
		 
		 CameraPowerConsumption = 0.0;
	 }

		
	 /**
	  * sets the cameras power consumption
	  * @param cameraPowerConsumption
	  */
	 public void setCameraPowerConsumption(double cameraPowerConsumption) {
		 CameraPowerConsumption = cameraPowerConsumption;
		 
	 }
	 
	 
}
