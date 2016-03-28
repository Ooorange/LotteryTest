package Util;

import java.util.Arrays;
import java.util.HashMap;

import bean.WinNumberInfo;

public class WinNumber {
	HashMap<Integer,String> mp3PahtList=new HashMap<Integer, String>(7);

	public static WinNumberInfo getWinNumberInfo(int[] number){
		int[] winnumber={number[1],number[2],number[3],number[4]};
		int[] sortwinnumber={number[1],number[2],number[3],number[4]};
		int[] counts = new int[9];
		int color=0;
		Arrays.sort(sortwinnumber);
		for(int i=0;i<4;i++){
			counts[sortwinnumber[i]]+=1;
			if(counts[sortwinnumber[i]] > color) color= counts[sortwinnumber[i]];
		}
		return new WinNumberInfo(winnumber, sortwinnumber, counts, color);
	}

}
