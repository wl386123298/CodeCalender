package com.codeCalender.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	private static int iday ;
	private static List<Map<String, Object>> specials = null;
	private static List<Map<String, String>> activities =null;
	private static String[] weeks = {"日","一","二","三","四","五","六"};
	private static String[] directions ={"北方","东北方","东方","东南方","南方","西南方","西方","西北方"};
	private static String[] tools  ={"Eclipse写程序", "MSOffice写文档", "记事本写程序", "Windows8", "Linux",
									"MacOS", "IE", "Android设备", "iOS设备"};
	private static String[] varNames ={"jieguo", "huodong", "pay", "expire", "zhangdan", "every", "free", "i1", "a",
									"virtual", "ad", "spider", "mima", "pass", "ui"};
	private static String[] drinks = {"水","茶","红茶","绿茶","咖啡","奶茶","可乐","牛奶","豆奶","果汁"
									,"果味汽水","苏打水","运动饮料","酸奶","酒"};
	public static void main(String[] args) {
		Calendar calendar   = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		iday = year*10000+month*100+day;
		init();
		pickTodaysLuck();
	}
	
	private static void init(){
		specials = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", 20130221);
		map.put("type", "good");
		map.put("name", "防核演习");
		map.put("description", "万一哪个疯子丢颗核弹过来……");
		specials.add(map);
		
		activities = new ArrayList<Map<String,String>>();
		
		String[] name ={"写单元测试","洗澡","锻炼一下身体","抽烟","白天上线","重构","使用%t",
				"跳槽","招人","面试","提交辞职申请","申请加薪","晚上加班","在妹子面前吹牛",
				"撸管","浏览成人网站","命名变量\"%v\"","写超过%l行的方法","提交代码","代码复审",
				"开会","打dota","晚上上线","修复BUG","设计评审","需求评审","上微博","上AB站"};
		
		String[] good = {"写单元测试将减少出错","你几天没洗澡了？","","抽烟有利于提神，增加思维敏捷",
				"今天白天上线是安全的","代码质量得到提高","你看起来更有品位","该放手时就放手",
				"你遇到千里马的可能性大大增加","面试官今天心情很好","公司找到了一个比你更能干更便宜的家伙，巴不得你赶快滚蛋",
				"老板今天心情很好","晚上是程序员精神最好的时候","改善你矮穷挫的形象","避免缓冲区溢出",
				"重拾对生活的信心","","你的代码组织的很好，长一点没关系","遇到冲突的几率是最低的","发现重要问题的几率大大增加",
				"写代码之余放松一下打个盹，有益健康","你将有如神助","晚上是程序员精神最好的时候","你今天对BUG的嗅觉大大提高",
				"设计评审会议将变成头脑风暴","","今天发生对BUG的嗅觉将变成头脑风暴","","今天发生的事不能错过","还需要理由吗？"};
		String[] bad = {"写单元测试会降低你的开发效率", "会把设计方面的灵感洗掉","能量没消耗多少，吃得却更多","除非你活够了，死得早点没关系",
				"可能导致灾难性后果","你很有可能会陷入泥潭","别人会觉得你在装逼","鉴于当前的经济形势，你的下一份工作未必比现在强","你只会招到一两个混饭吃的外行",
				"面试官不爽，会拿你出气","鉴于当前的经济形势，你的下一份工作未必比现在强","公司正在考虑裁员","晚上是程序员精神最好的时候","会被识破",
				"小撸怡情，大撸伤身，强撸灰飞烟灭","你会心神不宁","","你的代码将混乱不堪，你自己都看不懂","你遇到的一大堆冲突会让你觉得自己是不是时间穿越了",
				"你什么问题都发现不了，白白浪费时间","你会被扣屎盆子背黑锅","你会被虐的很惨","你白天已经筋疲力尽了","新产生的BUG将比修复的更多",
				"人人筋疲力尽，评审就这么过了","","会被老板看到","会被老板看到"};
		
		for (int i = 0; i < name.length; i++) {
			Map<String, String> actMap = new HashMap<String, String>();
			actMap.put("name", name[i]);
			actMap.put("good", good[i]);
			actMap.put("bad", bad[i]);
			activities.add(actMap);
		}
	}
	
	private static int random(int dayseed, int indexseed){
		int n = dayseed %11117;
		for (int i = 0; i < 100 +indexseed; i++) {
			n = n*n;
			n = n%11117;
		}
		return n ;
	}
	
	private static void pickTodaysLuck(){
		int numGod = random(iday, 98)%3 +2;
		int numBad = random(iday,87)%3 +2;
		List<Map<String, String>> eventArr = pickRandomActivity(numGod+numBad);
		int[] specialSize = pickSpecials();
		
		for (int i = 0; i < numGod; i++) {
			System.out.println(eventArr.get(i));
		}
		
		for (int i = 0; i < numBad; i++) {
			System.out.println(eventArr.get(i));
		}
	}
	

	private static List<Map<String,String>> pickRandomActivity(int size) {
		List<Map<String, String>> picked_events  = pickRandom(activities, size);
		for (Map<String, String> pickRes : picked_events) {
			pickRes = parse(pickRes);
		}
		return picked_events;
	}
	
	private static Map<String, String> parse(Map<String, String> pickRes) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", pickRes.get("name"));
		result.put("good", pickRes.get("good"));
		result.put("bad", pickRes.get("bad"));
		String rName = result.get("name");
		if (rName.indexOf("%v")!=-1) {
			rName = rName.replace("%v",varNames[random(iday, 12)%varNames.length]);
		}
		
		if (rName.indexOf("%t")!=-1) {
			rName = rName.replace("%t", tools[random(iday, 11) % tools.length]);
		}
		
		if (rName.indexOf("%l")!=-1) {
			rName = rName.replace("%l",String.valueOf((random(iday, 12)%247+30)));
		}
		
		return result;
	}

	private static List<Map<String, String>> pickRandom(List<Map<String, String>> array,int size){
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

	private static int[] pickSpecials(){
		int[] specialSize ={0,0};
		for (Map<String, Object> special : specials) {
			if (iday == (Integer)special.get("date")) {
				if (special.get("type").equals("good")) {
					specialSize[0]++;
					System.out.println("name:"+special.get("name")+"good:"+special.get("type"));
				}else {
					System.out.println("name:"+special.get("name")+"bad:"+special.get("type"));
				}
			}
		}
		return specialSize;
	}
}
