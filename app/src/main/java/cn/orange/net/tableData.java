package cn.orange.net;

public class tableData {

	private String group_number;
	private String frequency;
	private String current_group_miss;
	private String max_group_miss;
	public tableData(String group_number,String frequency,String current_group_miss,String max_group_miss ) {
		this.group_number=group_number;
		this.frequency=frequency;
		this.current_group_miss=current_group_miss;
		this.max_group_miss=max_group_miss;
	}
	public String getGroup_number() {
		return group_number;
	}
	public void setGroup_number(String group_number) {
		this.group_number = group_number;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getCurrent_group_miss() {
		return current_group_miss;
	}
	public void setCurrent_group_miss(String current_group_miss) {
		this.current_group_miss = current_group_miss;
	}
	public String getMax_group_miss() {
		return max_group_miss;
	}
	public void setMax_group_miss(String max_group_miss) {
		this.max_group_miss = max_group_miss;
	}
	
}
