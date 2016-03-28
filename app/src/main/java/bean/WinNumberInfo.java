package bean;

public class WinNumberInfo {
	private int[] winnumber;
	private int[] counts;
	public int[] sortwinnumber;
	private int[] arry;//保存重号信息
	private int color;//判断开奖号背景颜色。1代表组24红色，2代表组12黑色，3代表组4绿色，4代表组6蓝色,5代表双子号紫色
	public WinNumberInfo(int[] winnumber,int[] sortwinnumber, int[] counts, int color) {
		super();
		this.winnumber = winnumber;
		this.counts = counts;
		this.color = color;
		this.sortwinnumber = sortwinnumber;
		arry = new int[9];
		for(int i=0;i<4;i++){
			arry[winnumber[i]]++;
		}
	}
	public int[] getArry() {
		return arry;
	}
	public int[] getWinnumber() {
		return winnumber;
	}
	public void setWinnumber(int[] winnumber) {
		this.winnumber = winnumber;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int[] getCounts() {
		return counts;
	}
	public void setCounts(int[] counts) {
		this.counts = counts;
	}
	public int[] getSortwinnumber() {
		return sortwinnumber;
	}
	public void setSortwinnumber(int[] sortwinnumber) {
		this.sortwinnumber = sortwinnumber;
	}
	public void setColor(){
		int count = 0;
		for(int i=1;i<9;i++){
			if(arry[i] == 2){
				count++;
			}
		}
		if(count == 2){
			this.color = 5;
		}
	}
	
}
