package org.wl.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wl.data.CalenderData;

public class DataUtil {
	private int iday ;
	private List<Map<String, String>> eventArr ;
	private List<Map<String, String>> goodList;
	private List<Map<String, String>> badList;
	private List<Map<String, String>> pickedEvents ;
	private List<Map<String, String>> picked_events;
		
	public DataUtil() {
		init();
	}
	
	/**
	 * 初始化工作
	 */
	private void init(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		iday = year*10000+month*100+day;
		
		goodList = new ArrayList<Map<String,String>>();
		badList = new ArrayList<Map<String,String>>();
		
		pickTodaysLuck();
	}
	
	private  int random(int dayseed, int indexseed){
		int n = dayseed %11117;
		for (int i = 0; i < 100 +indexseed; i++) {
			n = n*n;
			n = n%11117;
		}
		return n ;
	}
	
	private  void pickTodaysLuck(){
		int numGod = random(iday, 98)%3 +2;
		int numBad = random(iday,87)%3 +2;
		eventArr = pickRandomActivity(numGod+numBad);
		//System.out.println(eventArr);
		int[] specialSize = pickSpecials();
		
		//宜事
		for (int i = 0; i < numGod; i++) {
			//System.out.println(eventArr.get(i));
			goodList.add(eventArr.get(i));
		}
		
		//不宜事
		for (int i = 0; i < numBad; i++) {
			//System.out.println(eventArr.get(numGod +i));
			badList.add(eventArr.get(numGod +i));
		}
	}
	

	private  List<Map<String,String>> pickRandomActivity(int size) {
		picked_events  = pickRandom(new CalenderData().getActivities(), size);
		pickedEvents = new ArrayList<Map<String,String>>();
		
		Map<String, String> map  = null;
		
		for (int i = 0; i < picked_events.size(); i++) {
			map= parse(picked_events.get(i));
			pickedEvents.add(map);
		}
		//System.out.println(pickedEvents);
		return pickedEvents;
	}
	
	private  Map<String, String> parse(Map<String, String> pickRes) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", pickRes.get("name"));
		result.put("good", pickRes.get("good"));
		result.put("bad", pickRes.get("bad"));
		String rName = result.get("name");
		if (rName.indexOf("%v")!=-1) {
			String name = rName.replace("%v",CalenderData.varNames[random(iday, 12)%CalenderData.varNames.length]);
			result.put("name",name);
		}
		
		if (rName.indexOf("%t")!=-1) {
			String name = rName.replace("%t", CalenderData.tools[random(iday, 11) % CalenderData.tools.length]);
			result.put("name",name);
		}
		
		if (rName.indexOf("%l")!=-1) {
			String name = rName.replace("%l",String.valueOf((random(iday, 12)%247+30)));
			result.put("name",name);
		}
		return result;
	}

	private  List<Map<String, String>> pickRandom(List<Map<String, String>> array,int size){
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for (Map<String, String> map : array) {
			result.add(map);
		}
		
		for(int j =0;j<array.size()-size;j++){
			int index = random(iday, j)%result.size();
			result.remove(index);
		}
		return result;
	}
	
	private  List<String> pickRandomList(List<String> array,int size){
		List<String> result = new ArrayList<String>();
		for (String str : array) {
			result.add(str);
		}
		
		for(int j =0;j<array.size()-size;j++){
			int index = random(iday, j)%result.size();
			result.remove(index);
		}
		return result;	
	}

	private  int[] pickSpecials(){
		int[] specialSize ={0,0};
		for (Map<String, Object> special : new CalenderData().getSpecials()) {
			if (iday == (Integer)special.get("date")) {
				if (special.get("type").equals("good")) {
					specialSize[0]++;
					//System.out.println("name:"+special.get("name")+"good:"+special.get("type"));
				}else {
					specialSize[1]++;
					//System.out.println("name:"+special.get("name")+"bad:"+special.get("type"));
				}
			}
		}
		return specialSize;
	}
	
	/**
	 * 返回宜事列表
	 * @return
	 */
	public List<Map<String, String>> getGooList(){
		return goodList;
	}
	
	/**
	 * 返回不宜事列表
	 * @return
	 */
	public List<Map<String, String>> getBadList() {
		return badList;
	}
	
	/**
	 * 返回方向
	 * @return
	 */
	public String getDirection() {
		return CalenderData.directions[random(iday, 2) %CalenderData.directions.length];
	}
	
	/**
	 * 返回饮品
	 * @return
	 */
	public String getDrinks() {
		StringBuffer dri = new StringBuffer();
		for (String drink : pickRandomList(new CalenderData().getDrinks(),2)) {
			dri.append(drink+" ");
		}
		return dri.toString();
	}
	
	/**
	 * 
	 * @return 返回女神指数
	 */
	public double getGirlValue() {
		return random(iday, 6) % 50 / 10.0;
	}
}
