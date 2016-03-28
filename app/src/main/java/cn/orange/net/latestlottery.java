package cn.orange.net;

public class latestlottery {

	private int firstnumber;
	private int secondnnumber;
	private int thirdnnumber;
	private int fourthnnumber;
	private int type;
	private int sum;
	private int step;
	private int issue;
//	private String current_missed;
//	private String max_missed;
	public latestlottery(int issue,int firstnumber,int secondnnumber, int thirdnnumber,int fourthnnumber,int type,int sum,int step ) {
		this.sum=sum;
		this.type=type;
		this.step=step;
		this.issue=issue;
//		this.max_missed=max_missed;
		this.firstnumber=firstnumber;
		this.thirdnnumber=thirdnnumber;
		this.secondnnumber=secondnnumber;
		this.fourthnnumber=fourthnnumber;
//		this.current_missed=current_missed;
	}
	public int getFirstnumber() {
		return firstnumber;
	}
	public void setFirstnumber(int firstnumber) {
		this.firstnumber = firstnumber;
	}
	public int getSecondnnumber() {
		return secondnnumber;
	}
	public void setSecondnnumber(int secondnnumber) {
		this.secondnnumber = secondnnumber;
	}
	public int getThirdnnumber() {
		return thirdnnumber;
	}
	public void setThirdnnumber(int thirdnnumber) {
		this.thirdnnumber = thirdnnumber;
	}
	public int getFourthnnumber() {
		return fourthnnumber;
	}
	public void setFourthnnumber(int fourthnnumber) {
		this.fourthnnumber = fourthnnumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
/*	public String getCurrent_missed() {
		return current_missed;
	}
	public void setCurrent_missed(String current_missed) {
		this.current_missed = current_missed;
	}
	public String getMax_missed() {
		return max_missed;
	}
	public void setMax_missed(String max_missed) {
		this.max_missed = max_missed;
	}*/
	
	
}
