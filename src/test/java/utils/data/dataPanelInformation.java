package utils.data;

public enum dataPanelInformation {

	
	PANELLIST("panelList"),
	RACKID("rackId"),
	TOTALPANELS("totalPanels"),
	PANELS("panels"),
	PANELNUMBER("panelNumber"),
	RUHEIGHT("ruHeight"),	
	ROWCOUNT("rowCount"),
	TOTALKITS("totalKits"),
	KITS("kits"),
	
	//Inside "kits" key
	ID("id"),
	KITS_TYPE("type"),
	HARDWAREVERSION("hardwareVersion"),
	STATUS("status"),
	FIRMWARETIMESTAMP("firmwareTimestamp"),
	UPDATABLE("updatable"),
	PORTOFFSET("portOffset"),
	MODULES("modules"),
	
	//Inside "module" key
	POSTION("position"),	
	MODULE_TYPE("type"),
	LOWESTPORTNUMBER("lowestPortNumber"),
	SENSORPERPORTNUMBERS("sensorPerPortNumbers"),
	SENSORCOUNT("sensorCount"),
	OCCUPIEDSENSORMASK("occupiedSensorMask");
		
	private String value;
	
	dataPanelInformation(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
